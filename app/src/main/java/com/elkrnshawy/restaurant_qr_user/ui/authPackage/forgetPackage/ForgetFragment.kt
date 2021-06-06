package com.elkrnshawy.restaurant_qr_user.ui.authPackage.forgetPackage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elkrnshawy.restaurant_qr_user.R

class ForgetFragment : Fragment() , ForgetView{
    private var presenterForget: PresenterForget? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        presenterForget= PresenterForget(this)

        return inflater.inflate(R.layout.fragment_forget, container, false)
    }

    override fun checkData() {
        if (true){
            presenterForget?.callLoginApi("gygy","hh")
        }
    }

    override fun onSuccessRetrieve(msg: String) {

        Log.e("huehvue","k9bjri")

    }

    override fun onFailureRetrieve() {
        TODO("Not yet implemented")
    }



}