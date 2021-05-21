package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.myReservationPackage.MyReservationResponse
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationViewModel : ViewModel() {
    private val dataObserveReservation: MutableResultWrapper<MyReservationResponse> = MutableResultWrapper()

    fun getDataReservation(): MutableResultWrapper<MyReservationResponse> {
        return dataObserveReservation
    }

    fun callReservation(token : String) {
        dataObserveReservation.postLoading()
        ApiUtils.getReservationService()?.getReservation(ConstantsHelper.BEARER_TOKEN+ token,ConstantsHelper.Localization,
            ConstantsHelper.ACCEPT)?.enqueue(object : Callback<MyReservationResponse?> {
            override fun onResponse(call: Call<MyReservationResponse?>, response: Response<MyReservationResponse?>) {
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

            override fun onFailure(call: Call<MyReservationResponse?>, t: Throwable) {
                dataObserveReservation.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}