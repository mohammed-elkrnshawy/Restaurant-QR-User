package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.servicesPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentCategoryBinding
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentServicesBinding
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.models.servicePackage.ServiceItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.categoryPackage.CategoryAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class ServicesFragment : ParentFragment() {
    private lateinit var serviceAdapter: ServiceAdapter<ServiceItem>
    private lateinit var binding: FragmentServicesBinding
    private var restaurantObject : RestaurantItem? =null
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_services, container, false)
            mainView = binding.root;
        }
        return mainView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntentData()
        setupComponents(mainView)
        handleToolbar()
        onComponentsClick()
    }

    override fun getIntentData() {
        super.getIntentData()
        if (arguments != null) {
            restaurantObject= arguments?.getSerializable("RestaurantObject") as RestaurantItem?
        }
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)

        serviceAdapter= ServiceAdapter(arrayListOf()) { view, position ->
            /*val bundle = Bundle()
            bundle.putSerializable("RestaurantObject",restaurantAdapter.getItem(position))
            getNavController()?.navigate(R.id.action_homeFragment_to_restaurantDetailsFragment, bundle)*/
        }

        binding.adapter=serviceAdapter
        serviceAdapter.setItems(restaurantObject?.getServices() as List<ServiceItem>)
    }

}