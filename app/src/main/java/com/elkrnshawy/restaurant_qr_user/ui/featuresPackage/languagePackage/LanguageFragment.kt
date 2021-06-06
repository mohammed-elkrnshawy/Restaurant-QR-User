package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.languagePackage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentLanguageBinding
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity.MainActivity
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class LanguageFragment : ParentFragment() {
    private lateinit var binding: FragmentLanguageBinding
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
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_language, container, false)
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
    }

    override fun onComponentsClick() {
        super.onComponentsClick()

        binding.txtArabic.setOnClickListener {
            if (ConstantsHelper.Localization == "en") {
                SharedPrefManager.setLanguage(requireContext(),"ar")
                ConstantsHelper.Localization = "ar"
                startActivity(Intent(context, MainActivity::class.java))
                requireActivity().finishAffinity()
            }
        }

        binding.txtEnglish.setOnClickListener {
            if (ConstantsHelper.Localization == "ar") {
                SharedPrefManager.setLanguage(requireContext(),"en")
                ConstantsHelper.Localization = "en"
                startActivity(Intent(context, MainActivity::class.java))
                requireActivity().finishAffinity()
            }
        }
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.stringTittle=resources.getString(R.string.language)
        binding.toolbar.imgBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}