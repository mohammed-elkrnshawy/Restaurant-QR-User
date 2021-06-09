package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.waitingListPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.StringResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.joinWaitingListPackage.JoinWaitingResponse
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

    fun callWaitingCount(token : String, restaurantID: Int) {
        dataObserveWaitingCount.postLoading()
        ApiUtils.getReservationService()?.waitingCount(ConstantsHelper.BEARER_TOKEN+token,ConstantsHelper.Localization, ConstantsHelper.ACCEPT,restaurantID)?.clone()?.
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
                dataObserveWaitingCount.postError(GenericError(t.message,null,call.execute().code(),true))
            }
        })
    }

    private val dataObserveJoinWaiting: MutableResultWrapper<JoinWaitingResponse> = MutableResultWrapper()

    fun getDataJoinWaiting(): MutableResultWrapper<JoinWaitingResponse> {
        return dataObserveJoinWaiting
    }

    fun callJoinWaiting(token : String, restaurantID: Int, count: Int) {
        dataObserveJoinWaiting.postLoading()
        ApiUtils.getReservationService()?.joinWaiting(ConstantsHelper.BEARER_TOKEN+token,ConstantsHelper.Localization,
                ConstantsHelper.ACCEPT,restaurantID,count)?.clone()?.enqueue(object : Callback<JoinWaitingResponse?> {
            override fun onResponse(call: Call<JoinWaitingResponse?>, response: Response<JoinWaitingResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveJoinWaiting.postSuccess(response.body()!!)
                    }else{
                        dataObserveJoinWaiting.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveJoinWaiting.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<JoinWaitingResponse?>, t: Throwable) {
                dataObserveJoinWaiting.postError(GenericError(t.message,null,call.execute().code(),true))
            }
        })
    }

    private val dataObserveRemoveWaiting: MutableResultWrapper<StringResponse> = MutableResultWrapper()

    fun getDataRemoveWaiting(): MutableResultWrapper<StringResponse> {
        return dataObserveRemoveWaiting
    }

    fun callRemoveWaiting(token : String, waitingNumber: Int) {
        dataObserveRemoveWaiting.postLoading()
        ApiUtils.getReservationService()?.removeWaiting(ConstantsHelper.BEARER_TOKEN+token,ConstantsHelper.Localization, ConstantsHelper.ACCEPT,waitingNumber)?.clone()?.
        enqueue(object : Callback<StringResponse?> {
            override fun onResponse(call: Call<StringResponse?>, response: Response<StringResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveRemoveWaiting.postSuccess(response.body()!!)
                    }else{
                        dataObserveRemoveWaiting.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveRemoveWaiting.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<StringResponse?>, t: Throwable) {
                dataObserveRemoveWaiting.postError(GenericError(t.message,null,call.execute().code(),true))
            }
        })
    }
}