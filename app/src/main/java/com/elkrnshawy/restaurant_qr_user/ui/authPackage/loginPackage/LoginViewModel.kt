package com.elkrnshawy.restaurant_qr_user.ui.authPackage.loginPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.authPackage.AuthResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val dataObserveLogin: MutableResultWrapper<AuthResponse> = MutableResultWrapper()

    fun getDataLogin(): MutableResultWrapper<AuthResponse> {
        return dataObserveLogin
    }

    private fun callLogin(phone: String, password: String, token: String) {
        dataObserveLogin.postLoading()
        ApiUtils.getUserService()?.loginUser(ConstantsHelper.Localization, ConstantsHelper.ACCEPT,phone,password,token)?.
        clone()?.enqueue(object : Callback<AuthResponse?> {
            override fun onResponse(call: Call<AuthResponse?>, response: Response<AuthResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveLogin.postSuccess(response.body()!!)
                    }else{
                        dataObserveLogin.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveLogin.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<AuthResponse?>, t: Throwable) {
                dataObserveLogin.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }

    fun getFCM(phone: String, password: String){
        callLogin(phone,password,"token")
    }
}