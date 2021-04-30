package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage

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
import com.elkrnshawy.restaurant_qr_user.databinding.SubcategoryFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.subcategoryPackage.SubcategoryFragmentArgs
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.subcategoryPackage.SubcategoryViewModel
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.categoryPackage.CategoryAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class MenuFragment : ParentFragment() {
    private lateinit var productAdapter: ProductAdapter<ProductItem>
    private lateinit var binding: MenuFragmentBinding
    private lateinit var viewModel: MenuViewModel
    private var categoryItem : CategoryItem? =null
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.menu_fragment,
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
    }

    override fun getIntentData() {
        super.getIntentData()
        categoryItem= MenuFragmentArgs.fromBundle(requireArguments()).subcategoryObject
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        productAdapter= ProductAdapter(arrayListOf()) { view, position ->
            /*val bundle = Bundle()
            bundle.putSerializable("SubcategoryObject",categoryAdapter.getItem(position))
            getNavController()?.navigate(R.id.action_subcategoryFragment_to_menuFragment, bundle)*/
        }
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        binding.adapter=productAdapter
        viewModel.callProduct(categoryItem?.getId()!!,1)
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.products)
        binding.toolbar.imgBack.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }

    private fun observeData(){
        viewModel.getDataProduct().observe(viewLifecycleOwner, Observer { dataResponse ->
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

    private fun onSuccess(items: List<ProductItem?>?, paginate: Paginate?) {
        if (paginate?.current_page==1){
            productAdapter.setItems(items as List<ProductItem>)
        }
    }

}