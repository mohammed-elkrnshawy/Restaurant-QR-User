package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.servicesPackage

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentCategoryBinding
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentServicesBinding
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.models.servicePackage.ServiceItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.subcategoryPackage.SubcategoryViewModel
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.categoryPackage.CategoryAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity.MainActivity
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import java.util.*

class ServicesFragment : ParentFragment() {
    private lateinit var serviceAdapter: ServiceAdapter<ServiceItem>
    private lateinit var binding: FragmentServicesBinding
    private lateinit var viewModel: ServicesViewModel
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
        viewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)

        serviceAdapter= ServiceAdapter(arrayListOf()) { view, position ->
           showDialog(serviceAdapter.getItem(position))
        }

        binding.adapter=serviceAdapter
        serviceAdapter.setItems(restaurantObject?.getServices() as List<ServiceItem>)
        observeData()
    }

    private fun showDialog(serviceItem: ServiceItem) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.layout_confirm_order)
        val window: Window? = dialog.window
        window?.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        val btnConfirm: Button = dialog.findViewById(R.id.btnConfirm)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)

        Objects.requireNonNull(dialog.window)?.attributes?.windowAnimations = R.style.alert_dialog
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnConfirm.setOnClickListener { view ->
            dialog.dismiss()
            viewModel.callOrderService(SharedPrefManager.getUserData(requireContext())?.getToken().toString(),
                restaurantObject?.getId()!!,1,serviceItem?.getId()!!)
        }

        btnCancel.setOnClickListener { view -> dialog.dismiss() }
        dialog.show()
    }

    private fun observeData(){

    }
}