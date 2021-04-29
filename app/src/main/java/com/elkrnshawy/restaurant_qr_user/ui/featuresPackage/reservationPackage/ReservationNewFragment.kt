package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.MenuFragmentBinding
import com.elkrnshawy.restaurant_qr_user.databinding.ReservationNewFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuFragmentArgs
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuViewModel
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.ProductAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class ReservationNewFragment : ParentFragment() {
    private lateinit var binding: ReservationNewFragmentBinding
    private lateinit var viewModel: ReservationNewViewModel
    private var mainView: View? =null
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
                    R.layout.reservation_new_fragment,
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
        setupComponents(view)
        handleToolbar()
    }

    override fun getIntentData() {
        super.getIntentData()
        userObject=SharedPrefManager.getUserData(requireContext())!!
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(ReservationNewViewModel::class.java)
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.reservation)
        binding.toolbar.imgBack.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }

}