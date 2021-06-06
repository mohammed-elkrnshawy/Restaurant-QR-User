package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.accountPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentLoginBinding
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentProfileBinding
import com.elkrnshawy.restaurant_qr_user.ui.authPackage.loginPackage.LoginViewModel
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class ProfileFragment : ParentFragment() {
    private lateinit var binding: FragmentProfileBinding
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        if (mainView==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
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
        binding.vmItem=SharedPrefManager.getUserData(requireContext())
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=resources.getString(R.string.profile)
        binding.toolbar.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}