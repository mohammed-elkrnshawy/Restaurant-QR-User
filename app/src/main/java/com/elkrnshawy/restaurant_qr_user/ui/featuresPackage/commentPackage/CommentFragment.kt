package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.commentPackage

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.CommentFragmentBinding
import com.elkrnshawy.restaurant_qr_user.databinding.MenuFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.commentPackage.CommentItem
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductItem
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.models.servicePackage.ServiceItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuFragmentArgs
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.MenuViewModel
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage.ProductAdapter
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import java.util.*

class CommentFragment : ParentFragment() {
    private lateinit var commentAdapter: CommentAdapter<CommentItem>
    private lateinit var binding: CommentFragmentBinding
    private lateinit var viewModel: CommentViewModel
    private var restaurantObject : RestaurantItem? =null
    private var commentItem : CommentItem? =null
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
            onComponentsClick()
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

    override fun onComponentsClick() {
        super.onComponentsClick()
        binding.txtComment.setOnClickListener {
            showDialog()
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

        viewModel.getDataAddComment().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    Toast.makeText(requireContext(),dataResponse.data?.getData().toString(),Toast.LENGTH_SHORT).show()
                    commentAdapter.addItem(commentItem!!)
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

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.layout_rate)
        val window: Window? = dialog.window
        window?.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        val btnConfirm: Button = dialog.findViewById(R.id.btnConfirm)
        val btnCancel: Button = dialog.findViewById(R.id.btnCancel)
        val edtComment: EditText = dialog.findViewById(R.id.edtComment)
        val barRating: RatingBar = dialog.findViewById(R.id.barRating)

        Objects.requireNonNull(dialog.window)?.attributes?.windowAnimations = R.style.alert_dialog
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnConfirm.setOnClickListener { view ->
            dialog.dismiss()

            if (edtComment.text.isEmpty()){
                edtComment.clearFocus()
                edtComment.error=resources.getString(R.string.require_field)
                edtComment.requestFocus()
            }else{
                commentItem= CommentItem()
                commentItem?.setComment(edtComment.text.toString())
                commentItem?.setCreated(Calendar.getInstance().time.toString())
                commentItem?.setId(0)
                commentItem?.setRate(barRating.rating.toDouble())
                commentItem?.setUser(SharedPrefManager.getUserData(requireContext())?.getName().toString())

                viewModel.callAddComment(SharedPrefManager.getUserData(requireContext())?.getToken().toString(),restaurantObject?.getId()!!,
                        edtComment.text.toString(),barRating.rating.toDouble())
            }
        }

        btnCancel.setOnClickListener { view -> dialog.dismiss() }
        dialog.show()
    }
}