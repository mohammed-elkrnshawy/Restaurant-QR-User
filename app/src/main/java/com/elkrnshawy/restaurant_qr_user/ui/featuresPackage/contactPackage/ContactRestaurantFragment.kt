package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.contactPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.ContactRestaurantFragmentBinding
import com.elkrnshawy.restaurant_qr_user.databinding.ReservationNewFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage.ReservationNewFragmentArgs
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage.ReservationNewViewModel
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.Validator
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import java.text.SimpleDateFormat

class ContactRestaurantFragment : ParentFragment() {
    private lateinit var binding: ContactRestaurantFragmentBinding
    private lateinit var viewModel: ContactRestaurantViewModel
    private var restaurantObject: RestaurantItem? = null
    private lateinit var userObject: UserData
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
                R.layout.contact_restaurant_fragment,
                container,
                false
            )
            mainView = binding.root
        }
        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntentData()
        setupComponents(view)
        handleToolbar()
        onComponentsClick()
    }

    override fun getIntentData() {
        super.getIntentData()
        restaurantObject= ReservationNewFragmentArgs.fromBundle(requireArguments()).restaurantObject
        userObject= SharedPrefManager.getUserData(requireContext())!!
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(ContactRestaurantViewModel::class.java)
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.contact_restaurant)
        binding.toolbar.imgBack.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }

    override fun onComponentsClick() {
        super.onComponentsClick()
        binding.btnSend.setOnClickListener {
            checkData()
        }
    }

    private fun checkData(){
        binding.edtUsername.tag=resources.getString(R.string.username)
        binding.edtPhone.tag=resources.getString(R.string.phone)
        binding.edtMessage.tag=resources.getString(R.string.message)


        /*if (Validator().checkConnection(context)){
            Toast.makeText(
                    context,
                    resources.getString(R.string.no_internet_connection),
                    Toast.LENGTH_LONG
            ).show()
            return
        }*/

        val editTexts = arrayOf<EditText>(
            binding.edtUsername,
            binding.edtPhone,
            binding.edtMessage
        )
        if (!Validator().validateInputField(editTexts, requireActivity())) {
            return
        }

        viewModel.callContactRestaurant(userObject?.getToken().toString(),restaurantObject?.getId()!!.toInt(),
        binding.edtUsername.text.toString(),binding.edtPhone.text.toString(),binding.edtMessage.text.toString())
    }

    private fun observeData(){
        viewModel.getDataContactRestaurant().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showSubLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    handleErrorMsg(null)
                    Toast.makeText(requireContext(),resources.getString(R.string.send_success), Toast.LENGTH_SHORT).show()
                    binding.edtUsername.text=null
                    binding.edtMessage.text=null
                    binding.edtPhone.text=null
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }
}