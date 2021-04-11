package com.elkrnshawy.restaurant_qr_user.ui.authPackage.registerPackae

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elkrnshawy.restaurant_qr_user.R

class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }
    // viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
}