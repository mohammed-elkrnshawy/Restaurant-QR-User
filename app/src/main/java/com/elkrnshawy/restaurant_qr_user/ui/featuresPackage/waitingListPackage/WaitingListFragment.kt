package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.waitingListPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.ReservationNewFragmentBinding
import com.elkrnshawy.restaurant_qr_user.databinding.WaitingListFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
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
        //restaurantObject= ReservationNewFragmentArgs.fromBundle(requireArguments()).restaurantObject
        userObject= SharedPrefManager.getUserData(requireContext())!!
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(WaitingListViewModel::class.java)
        viewModel.callWaitingCount()
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.join_waiting_list)
        binding.toolbar.imgBack.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }

    override fun onComponentsClick() {
        super.onComponentsClick()

        binding.btnJoin.setOnClickListener {

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
                    binding.vmItem=dataResponse.data?.getData()
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }
}