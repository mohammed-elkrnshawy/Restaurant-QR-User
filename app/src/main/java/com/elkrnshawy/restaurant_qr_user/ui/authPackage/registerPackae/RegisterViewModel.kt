package com.elkrnshawy.restaurant_qr_user.ui.authPackage.registerPackae

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.authPackage.AuthResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val dataObserveRegister: MutableResultWrapper<AuthResponse> = MutableResultWrapper()

    fun getDataRegister(): MutableResultWrapper<AuthResponse> {
        return dataObserveRegister
    }

    private fun callRegister(username: String, email: String, phone: String, password: String, token: String) {
        dataObserveRegister.postLoading()
        ApiUtils.getUserService()?.registerUser(ConstantsHelper.Localization, ConstantsHelper.ACCEPT,ConstantsHelper.Device,
                username,email, phone,password,"token")?.clone()?.enqueue(object : Callback<AuthResponse?> {
            override fun onResponse(call: Call<AuthResponse?>, response: Response<AuthResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveRegister.postSuccess(response.body()!!)
                    }else{
                        dataObserveRegister.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveRegister.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<AuthResponse?>, t: Throwable) {
                dataObserveRegister.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }

    fun getFCM(username: String, email: String, phone: String, password: String){
        callRegister(username,email,phone,password,"token")
    }
}