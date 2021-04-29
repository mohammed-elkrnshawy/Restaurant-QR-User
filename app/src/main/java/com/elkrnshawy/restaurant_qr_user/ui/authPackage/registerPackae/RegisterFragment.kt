package com.elkrnshawy.restaurant_qr_user.ui.authPackage.registerPackae

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentLoginBinding
import com.elkrnshawy.restaurant_qr_user.databinding.RegisterFragmentBinding
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.Status
import com.elkrnshawy.restaurant_qr_user.ui.authPackage.loginPackage.LoginViewModel
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity.HomeActivity
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.Validator
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class RegisterFragment : ParentFragment() {
    private lateinit var binding: RegisterFragmentBinding
    private lateinit var viewModel: RegisterViewModel
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        if (mainView==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
            mainView = binding.root;
        }
        return mainView;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponents(mainView)
        onComponentsClick()
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        observeData()
    }

    override fun onComponentsClick() {
        super.onComponentsClick()

        binding.btnRegister.setOnClickListener {
            checkData()
        }

        binding.txtLogin.setOnClickListener {
            getNavController()?.navigateUp()
        }
    }

    private fun checkData(){
        binding.edtUsername.tag=resources.getString(R.string.username)
        binding.edtMail.tag=resources.getString(R.string.email)
        binding.edtPhone.tag=resources.getString(R.string.phone)
        binding.edtPassword.tag=resources.getString(R.string.password)
        binding.edtConfirmPassword.tag=resources.getString(R.string.confirm_password)

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
                binding.edtMail,
                binding.edtPhone,
                binding.edtPassword,
                binding.edtConfirmPassword
        )
        if (!Validator().validateInputField(editTexts, requireActivity())) {
            return
        }

        viewModel.getFCM(
                binding.edtUsername.text.toString().trim(),
                binding.edtMail.text.toString().trim(),
                binding.edtPhone.text.toString().trim(),
                binding.edtPassword.text.toString().trim()
        )
    }

    private fun observeData(){
        viewModel.getDataRegister().observe(viewLifecycleOwner, Observer { dataResponse ->
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