package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.contactPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.contactResponse.ContactResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.reservationPackage.ReservationResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ContactRestaurantViewModel : ViewModel() {
    private val dataObserveContactRestaurant: MutableResultWrapper<ContactResponse> = MutableResultWrapper()

    fun getDataContactRestaurant(): MutableResultWrapper<ContactResponse> {
        return dataObserveContactRestaurant
    }

    fun callContactRestaurant(token: String, restaurant_id: Int, username: String, phone: String, message: String) {
        dataObserveContactRestaurant.postLoading()
        ApiUtils.getReservationService()?.contactRestaurant(
            ConstantsHelper.BEARER_TOKEN+token,
            ConstantsHelper.Localization, ConstantsHelper.ACCEPT,restaurant_id,username,phone,message)?.clone()?.
        enqueue(object : Callback<ContactResponse?> {
            override fun onResponse(call: Call<ContactResponse?>, response: Response<ContactResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveContactRestaurant.postSuccess(response.body()!!)
                    }else{
                        dataObserveContactRestaurant.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveContactRestaurant.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<ContactResponse?>, t: Throwable) {
                dataObserveContactRestaurant.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}