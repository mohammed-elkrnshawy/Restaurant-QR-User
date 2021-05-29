package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.commentPackage

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
import com.elkrnshawy.restaurant_qr_user.databinding.CommentFragmentBinding
import com.elkrnshawy.restaurant_qr_user.databinding.MenuFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.commentPackage.CommentItem
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductItem
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuFragmentArgs
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuViewModel
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.ProductAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class CommentFragment : ParentFragment() {
    private lateinit var commentAdapter: CommentAdapter<CommentItem>
    private lateinit var binding: CommentFragmentBinding
    private lateinit var viewModel: CommentViewModel
    private var restaurantObject : RestaurantItem? =null
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.comment_fragment,
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
        restaurantObject= CommentFragmentArgs.fromBundle(requireArguments()).restaurantObject
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        commentAdapter= CommentAdapter(arrayListOf()) { view, position ->

        }
        viewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        binding.restaurantObject=restaurantObject
        binding.adapter=commentAdapter
        viewModel.callComment(SharedPrefManager.getUserData(requireContext())?.getToken().toString(),restaurantObject?.getId()!!,1)
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.rate_and_comment)
        binding.toolbar.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeData(){
        viewModel.getDataComment().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccess(
                            dataResponse.data?.getData())
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }

    private fun onSuccess(list: List<CommentItem>?) {
        isEmptyList(list)
        commentAdapter.setItems(list!!)
    }
}