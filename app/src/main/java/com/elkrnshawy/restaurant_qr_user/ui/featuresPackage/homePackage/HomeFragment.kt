package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.homePackage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentHomeBinding
import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantCodeData
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.scanPackage.ScanActivity
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity.HomeActivity
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.NavControllerHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class HomeFragment : ParentFragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var restaurantAdapter: RestaurantAdapter<RestaurantItem>
    private var mainView: View? =null
    private var qrClicked : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
            mainView = binding.root;
            setupComponents(mainView)
        }
        return mainView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackResult()
        handleToolbar()
        onComponentsClick()
        observeDataQR()
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        NavControllerHelper.setupNavControl(findNavController())
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        restaurantAdapter= RestaurantAdapter(arrayListOf()) { view, position ->
            val bundle = Bundle()
            bundle.putSerializable("RestaurantObject", restaurantAdapter.getItem(position))
            findNavController().navigate(R.id.action_homeFragment_to_restaurantDetailsFragment, bundle)
        }

        binding.adapter=restaurantAdapter
        viewModel.callRestaurant(1)
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.imgMenu.setOnClickListener {
            HomeActivity.open()
        }
    }

    override fun onComponentsClick() {
        super.onComponentsClick()

        binding.cardQR.setOnClickListener {
            qrClicked=true
            startActivityForResult(Intent(requireContext(), ScanActivity::class.java), 200)
        }
    }

    private fun observeData(){
        viewModel.getDataRestaurant().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccess(
                            dataResponse.data?.getData()?.getItems(),
                            dataResponse.data?.getData()?.getPaginate()
                    )
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }

    private fun observeDataQR(){
        viewModel.getDataRestaurantQR().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccessQR(dataResponse.data?.getData())
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }

    private fun onSuccessQR(data: RestaurantCodeData?) {
       if (qrClicked){
           val bundle = Bundle()
           bundle.putSerializable("RestaurantObject", data?.getRestaurant())
           bundle.putSerializable("TableNumber", data?.getId())
           findNavController().navigate(R.id.action_homeFragment_to_restaurantDetailsFragment, bundle)
           qrClicked=false
       }
    }

    private fun onSuccess(items: List<RestaurantItem?>?, paginate: Paginate?) {
        if (paginate?.current_page==1){
            isEmptyList(items)
            restaurantAdapter.setItems(items as List<RestaurantItem>)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 200) {
            if (resultCode === Activity.RESULT_OK) {
                val result: String = data?.getStringExtra("result")!!
                viewModel.callRestaurantQR(result)
            }
            if (resultCode === Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private fun onBackResult(){
        val navBackStackEntry = findNavController().currentBackStackEntry
        val observer = LifecycleEventObserver { source, event ->
            if (event == Lifecycle.Event.ON_RESUME && navBackStackEntry!!.savedStateHandle.contains("isScanQR")) {
                val result = navBackStackEntry!!.savedStateHandle.get<Boolean>("isScanQR")!!
                if (result) {
                    /*qrClicked=true
                    startActivityForResult(Intent(requireContext(), ScanActivity::class.java), 200)*/
                    binding.cardQR.callOnClick()
                }
            }
        }
        navBackStackEntry!!.lifecycle.addObserver(observer)

        // As addObserver() does not automatically remove the observer, we
        // call removeObserver() manually when the view lifecycle is destroyed

        // As addObserver() does not automatically remove the observer, we
        // call removeObserver() manually when the view lifecycle is destroyed
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
            if (event == Lifecycle.Event.ON_DESTROY || event == Lifecycle.Event.ON_PAUSE) {
                navBackStackEntry!!.lifecycle.removeObserver(observer)
            }
        })
    }
}