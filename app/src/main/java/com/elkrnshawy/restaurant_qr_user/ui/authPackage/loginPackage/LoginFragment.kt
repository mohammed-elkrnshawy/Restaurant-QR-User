package com.elkrnshawy.restaurant_qr_user.ui.authPackage.loginPackage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentLoginBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.homePackage.HomeViewModel
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity.HomeActivity
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.Validator
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment


class LoginFragment : ParentFragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (mainView==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
            mainView = binding.root;
        }
        return mainView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponents(mainView)
        handleToolbar()
        onComponentsClick()
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observeData()
    }

    override fun onComponentsClick() {
        super.onComponentsClick()

        binding.txtRegister.setOnClickListener {
            getNavController()?.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            checkData()
        }
    }

    override fun handleToolbar() {
        super.handleToolbar()
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    activity?.finishAffinity()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun checkData(){
        binding.edtPhone.tag=resources.getString(R.string.phone)
        binding.edtPassword.tag=resources.getString(R.string.password)

        val editTexts = arrayOf<EditText>(
                binding.edtPhone,
                binding.edtPassword
        )
        if (!Validator().validateInputField(editTexts, requireActivity())) {
            return
        }

        viewModel.getFCM(
                binding.edtPhone.text.toString().trim(),
                binding.edtPassword.text.toString().trim()
        )
    }

    private fun observeData(){
        viewModel.getDataLogin().observe(viewLifecycleOwner, Observer { dataResponse ->
            when (dataResponse!!.status) {
                Status.Loading -> {
                    showSubLoading()
                }
                Status.Failure -> {
                    handleErrorMsg(dataResponse.error)
                }
                Status.Success -> {
                    onSuccess(
                            dataResponse.data?.getData()!!
                    )
                    handleErrorMsg(null)
                }
                Status.ResponseArrived -> {

                }
            }
        })
    }

    private fun onSuccess(userObject: UserData){
        SharedPrefManager.setLoginStatus(true,requireContext())
        SharedPrefManager.setUserData(userObject,requireContext())

        startActivity(Intent(requireContext(), HomeActivity::class.java))
        activity?.finishAffinity()
    }
}