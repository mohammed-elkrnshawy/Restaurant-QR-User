package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.ReservationNewFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import java.text.SimpleDateFormat
import java.util.*

class ReservationNewFragment : ParentFragment() {
    private lateinit var binding: ReservationNewFragmentBinding
    private lateinit var viewModel: ReservationNewViewModel
    private var mainView: View? =null
    private var simpleDateFormat: SimpleDateFormat? = null
    private var restaurantObject: RestaurantItem? = null
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
        onComponentsClick()
    }

    override fun getIntentData() {
        super.getIntentData()
        userObject=SharedPrefManager.getUserData(requireContext())!!
        binding.edtUsername.setText(userObject.getName())
        binding.edtPhone.setText(userObject.getPhone())
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(ReservationNewViewModel::class.java)
        observeData()
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=context?.getString(R.string.reservation)
        binding.toolbar.imgBack.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }

    override fun onComponentsClick() {
        super.onComponentsClick()
        binding.txtDate.setOnClickListener {
            datePicker();
        }

        binding.btnConfirm.setOnClickListener {
            checkData()
        }
    }

    private fun checkData(){
        binding.edtUsername.tag=resources.getString(R.string.username)
        binding.edtPhone.tag=resources.getString(R.string.phone)
        binding.edtPeopleNumber.tag=resources.getString(R.string.people_number)
        binding.edtNotes.tag=resources.getString(R.string.notes)

        if (binding.txtDate.text.isEmpty()){
            binding.txtDate.requestFocus()
            binding.txtDate.error=resources.getString(R.string.require_field)
            return
        }

        viewModel.callReservation(1,userObject.getToken().toString(),binding.edtUsername.text.toString(),
        binding.edtPhone.text.toString(),binding.edtNotes.text.toString(),binding.edtPeopleNumber.text.toString().toInt())
    }

    private fun datePicker() {
        val dialogView = View.inflate(context, R.layout.dialog_date_picker, null)
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        val datePicker = dialogView.findViewById<DatePicker>(R.id.date_picker)
        val timePicker = dialogView.findViewById<TimePicker>(R.id.time_picker)
        dialogView.findViewById<View>(R.id.date_time_set).setOnClickListener {
            val newDate = Calendar.getInstance()
            newDate[datePicker.year, datePicker.month, datePicker.dayOfMonth, timePicker.hour] = timePicker.minute
            simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            binding.txtDate.text= simpleDateFormat!!.format(newDate.time)
            alertDialog.dismiss()
        }
        alertDialog.setView(dialogView)
        datePicker.minDate = System.currentTimeMillis()
        alertDialog.show()
    }

    private fun observeData(){
        viewModel.getDataReservation().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showMainLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    Toast.makeText(requireContext(),resources.getString(R.string.reservation_success),Toast.LENGTH_SHORT).show()
                    getNavController()?.navigateUp()
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }
}