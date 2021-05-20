package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.waitingListPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.ReservationNewFragmentBinding
import com.elkrnshawy.restaurant_qr_user.databinding.WaitingListFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.joinWaitingListPackage.JoinWaitingData
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.models.waitingCountPackage.WaitingCountData
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage.ReservationNewFragmentArgs
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage.ReservationNewViewModel
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import java.text.SimpleDateFormat

class WaitingListFragment : ParentFragment() {
    private lateinit var binding: WaitingListFragmentBinding
    private lateinit var viewModel: WaitingListViewModel
    private var mainView: View? =null
    private var restaurantObject: RestaurantItem? = null
    private lateinit var userObject: UserData
    private var joinID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.waiting_list_fragment,
                    container,
                    false
            )
            mainView = binding.root
        }
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntentData()
        setupComponents(view)
        handleToolbar()
        onComponentsClick()
    }

    override fun getIntentData() {
        super.getIntentData()
        restaurantObject=WaitingListFragmentArgs.fromBundle(requireArguments()).restaurantObject
        userObject= SharedPrefManager.getUserData(requireContext())!!
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(WaitingListViewModel::class.java)

        viewModel.callWaitingCount(userObject?.getToken().toString(),restaurantObject?.getId()!!)

        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.waiting_list)
        binding.toolbar.imgBack.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }

    override fun onComponentsClick() {
        super.onComponentsClick()

        binding.btnJoin.setOnClickListener {
            if (binding.btnJoin.text.equals(resources.getString(R.string.join_waiting_list))){
                viewModel.callJoinWaiting(userObject?.getToken().toString(),restaurantObject?.getId()!!)
            }else{
                viewModel.callRemoveWaiting(userObject?.getToken().toString(),joinID)
            }
        }
    }

    private fun observeData(){
        viewModel.getDataWaitingCount().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccessCount(dataResponse.data?.getData())
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })

        viewModel.getDataRemoveWaiting().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccessRemove()
                    Toast.makeText(requireContext(),dataResponse.data?.getData().toString(),Toast.LENGTH_SHORT).show()
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })

        viewModel.getDataJoinWaiting().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccessJoin(dataResponse.data?.getData())
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }

    private fun onSuccessCount(data: WaitingCountData?) {
        binding.vmItem=data
        if (data?.getExist()!!){
            joinID=data?.getWatinglistNumber()!!
            binding.btnJoin.text=resources.getString(R.string.remove_from_waiting_list)
            binding.btnJoin.setTextColor(resources.getColor(R.color.colorRed))
        }
    }

    private fun onSuccessJoin(data: JoinWaitingData?) {
        joinID=data?.getNumber()!!
        binding.txtCount.text=data?.getWatinglistCount().toString()
        binding.btnJoin.text=resources.getString(R.string.remove_from_waiting_list)
        binding.btnJoin.setTextColor(resources.getColor(R.color.colorRed))
    }

    private fun onSuccessRemove() {
        joinID=0
        binding.txtCount.text= ((binding.txtCount.text.toString().toInt())-1).toString()
        binding.btnJoin.text=resources.getString(R.string.join_waiting_list)
        binding.btnJoin.setTextColor(resources.getColor(R.color.colorPrimary))
    }
}