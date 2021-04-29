package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.reservationPackage.ReservationResponse
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationNewViewModel : ViewModel() {
    private val dataObserveReservation: MutableResultWrapper<ReservationResponse> = MutableResultWrapper()

    fun getDataReservation(): MutableResultWrapper<ReservationResponse> {
        return dataObserveReservation
    }

    fun callReservation(restaurant_id: Int, token: String, username: String, phone: String,notes: String, count: Int) {
        dataObserveReservation.postLoading()
        ApiUtils.getReservationService()?.reservation(ConstantsHelper.BEARER_TOKEN+token,
                ConstantsHelper.Localization, ConstantsHelper.ACCEPT,restaurant_id,username,phone,notes,count)?.clone()?.
        enqueue(object : Callback<ReservationResponse?> {
            override fun onResponse(call: Call<ReservationResponse?>, response: Response<ReservationResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveReservation.postSuccess(response.body()!!)
                    }else{
                        dataObserveReservation.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveReservation.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<ReservationResponse?>, t: Throwable) {
                dataObserveReservation.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}