package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.waitingListPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.reservationPackage.ReservationResponse
import com.elkrnshawy.restaurant_qr_user.models.waitingCountPackage.WaitingCountResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaitingListViewModel : ViewModel() {
    private val dataObserveWaitingCount: MutableResultWrapper<WaitingCountResponse> = MutableResultWrapper()

    fun getDataWaitingCount(): MutableResultWrapper<WaitingCountResponse> {
        return dataObserveWaitingCount
    }

    fun callWaitingCount() {
        dataObserveWaitingCount.postLoading()
        ApiUtils.getReservationService()?.waitingCount(ConstantsHelper.Localization, ConstantsHelper.ACCEPT)?.clone()?.
        enqueue(object : Callback<WaitingCountResponse?> {
            override fun onResponse(call: Call<WaitingCountResponse?>, response: Response<WaitingCountResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveWaitingCount.postSuccess(response.body()!!)
                    }else{
                        dataObserveWaitingCount.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveWaitingCount.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<WaitingCountResponse?>, t: Throwable) {
                dataObserveWaitingCount.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}