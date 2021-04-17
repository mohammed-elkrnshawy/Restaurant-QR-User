package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.homePackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentHomeBinding
import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class HomeFragment : ParentFragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var restaurantAdapter: RestaurantAdapter<RestaurantItem>
    private var mainView: View? =null

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
        }
        return mainView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponents(mainView)
        handleToolbar()
        onComponentsClick()
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        restaurantAdapter= RestaurantAdapter(arrayListOf()) { view, position ->
            val bundle = Bundle()
            bundle.putSerializable("RestaurantObject",restaurantAdapter.getItem(position))
            getNavController()?.navigate(R.id.action_homeFragment_to_restaurantDetailsFragment, bundle)
        }

        binding.adapter=restaurantAdapter
        viewModel.callRestaurant(1)
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.imgMenu.setOnClickListener {

        }
    }

    override fun onComponentsClick() {
        super.onComponentsClick()


        binding.cardQR.setOnClickListener {
            getNavController()?.navigate(R.id.action_homeFragment_to_scanFragment)
        }
    }

    private fun observeData(){
        viewModel.getDataRestaurant().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showSubLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccess(
                        dataResponse.data?.getData()?.getItems(),
                        dataResponse.data?.getData()?.getPaginate()
                    )
                    hideSubLoading()
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }

    private fun onSuccess(items: List<RestaurantItem?>?, paginate: Paginate?) {
        if (paginate?.current_page==1){
            restaurantAdapter.setItems(items as List<RestaurantItem>)
        }
    }
}