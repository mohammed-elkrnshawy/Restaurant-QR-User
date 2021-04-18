package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.RestaurantDetailsFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.adapterHelper.SlidePagerAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class RestaurantDetailsFragment : ParentFragment() {
    private lateinit var binding: RestaurantDetailsFragmentBinding
    private lateinit var viewModel: RestaurantDetailsViewModel
    private lateinit var pagerAdapter: SlidePagerAdapter
    private lateinit var titles: ArrayList<String>
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
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.restaurant_details_fragment,
                container,
                false
            )
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
        restaurantObject= RestaurantDetailsFragmentArgs.fromBundle(requireArguments()).restaurantObject
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        titles = ArrayList()
        titles.add(getString(R.string.details))
        titles.add(getString(R.string.services))

        pagerAdapter = SlidePagerAdapter(requireActivity(),restaurantObject)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(
            binding.tabs, binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = titles[position]
        }.attach()

        viewModel = ViewModelProvider(this).get(RestaurantDetailsViewModel::class.java)
        binding.restaurantObject=restaurantObject

    }

}