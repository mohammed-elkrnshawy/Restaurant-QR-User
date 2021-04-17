package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.homePackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.FragmentHomeBinding
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment

class HomeFragment : ParentFragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private var mainView: View? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mainView==null){
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
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
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun handleToolbar() {
        super.handleToolbar()
        binding.toolbar.imgMenu.setOnClickListener {

        }
    }

    override fun onComponentsClick() {
        super.onComponentsClick()
        binding.cardQR.setOnClickListener {
            getNavController()?.navigate(R.id.action_homeFragment_to_scanFragment)
        }
    }
}