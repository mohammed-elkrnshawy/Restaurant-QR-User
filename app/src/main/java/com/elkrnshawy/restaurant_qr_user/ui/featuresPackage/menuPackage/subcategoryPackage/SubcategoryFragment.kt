package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.subcategoryPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.SubcategoryFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.categoryPackage.CategoryAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class SubcategoryFragment : ParentFragment() {
    private lateinit var categoryAdapter: CategoryAdapter<CategoryItem>
    private lateinit var binding: SubcategoryFragmentBinding
    private lateinit var viewModel: SubcategoryViewModel
    private var categoryItem : CategoryItem? =null
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.subcategory_fragment,
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

    }

    override fun getIntentData() {
        super.getIntentData()
        categoryItem= SubcategoryFragmentArgs.fromBundle(requireArguments()).categoryObject
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        categoryAdapter= CategoryAdapter(arrayListOf()) { view, position ->
            val bundle = Bundle()
            bundle.putSerializable("SubcategoryObject",categoryAdapter.getItem(position))
            getNavController()?.navigate(R.id.action_subcategoryFragment_to_menuFragment, bundle)
        }
        viewModel = ViewModelProvider(this).get(SubcategoryViewModel::class.java)
        binding.adapter=categoryAdapter
        viewModel.callSubcategory(categoryItem?.getId()!!,1)
        observeData()
    }

    private fun observeData(){
        viewModel.getDataSubcategory().observe(viewLifecycleOwner, Observer { dataResponse ->
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

    private fun onSuccess(items: List<CategoryItem>?, paginate: Paginate?) {
        if (paginate?.current_page==1){
            categoryAdapter.setItems(items as List<CategoryItem>)
        }
    }
}