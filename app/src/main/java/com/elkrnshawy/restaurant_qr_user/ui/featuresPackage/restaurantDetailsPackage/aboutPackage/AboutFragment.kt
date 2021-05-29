package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.aboutPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentAboutBinding
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedUtilsHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class AboutFragment : ParentFragment() {
    private lateinit var binding: FragmentAboutBinding
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
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false)
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

        binding.restaurantObject=restaurantObject
        binding.txtAddress.text=SharedUtilsHelper.getAddress(requireContext(), restaurantObject?.getLat()!!,restaurantObject?.getLng()!!)
    }

    override fun onComponentsClick() {
        super.onComponentsClick()
        binding.txtOpenAddress.setOnClickListener {
            SharedUtilsHelper.openGoogleMap(requireContext(),restaurantObject?.getLat()!!,restaurantObject?.getLng()!!)
        }

        binding.txtReviews.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("restaurantObject",restaurantObject)
            findNavController()?.navigate(R.id.action_global_commentFragment,bundle)
        }
    }
}