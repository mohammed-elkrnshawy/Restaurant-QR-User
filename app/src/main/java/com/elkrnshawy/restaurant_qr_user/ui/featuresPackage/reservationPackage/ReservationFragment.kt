package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.MenuFragmentBinding
import com.elkrnshawy.restaurant_qr_user.databinding.ReservationFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.myReservationPackage.MyReservationData
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuFragmentArgs
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuViewModel
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.ProductAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class ReservationFragment : ParentFragment() {
    private lateinit var reservationAdapter: ReservationAdapter<MyReservationData>
    private lateinit var binding: ReservationFragmentBinding
    private lateinit var viewModel: ReservationViewModel
    private var userObject : UserData? =null
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.reservation_fragment,
                container,
                false
            )
            mainView = binding.root
            getIntentData()
            setupComponents(mainView)
        }
        return mainView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleToolbar()
    }

    override fun getIntentData() {
        super.getIntentData()
        userObject=SharedPrefManager.getUserData(requireContext())
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        reservationAdapter= ReservationAdapter(arrayListOf()) { view, position ->

        }
        viewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)
        binding.adapter=reservationAdapter
        viewModel.callReservation(userObject?.getToken().toString())
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.my_reservation)
        binding.toolbar.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeData(){
        viewModel.getDataReservation().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccess(
                        dataResponse.data?.getData()
                    )
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }

    private fun onSuccess(data: List<MyReservationData?>?) {
        isEmptyList(data)
        reservationAdapter.setItems(data as List<MyReservationData>)
    }
}