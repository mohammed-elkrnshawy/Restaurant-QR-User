package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.servicesPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.StringResponse
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.SubcategoryResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesViewModel : ViewModel() {
    private val dataObserveOrderService: MutableResultWrapper<StringResponse> = MutableResultWrapper()

    fun getDataOrderService(): MutableResultWrapper<StringResponse> {
        return dataObserveOrderService
    }

    fun callOrderService(token: String, restaurantID: Int, userID: Int, serviceID: Int) {
        dataObserveOrderService.postLoading()
        ApiUtils.getReservationService()?.orderService(ConstantsHelper.BEARER_TOKEN+token, ConstantsHelper.Localization,
            ConstantsHelper.ACCEPT,restaurantID,userID,serviceID)?.enqueue(object : Callback<StringResponse?> {
            override fun onResponse(call: Call<StringResponse?>, response: Response<StringResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveOrderService.postSuccess(response.body()!!)
                    }else{
                        dataObserveOrderService.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveOrderService.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<StringResponse?>, t: Throwable) {
                dataObserveOrderService.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}