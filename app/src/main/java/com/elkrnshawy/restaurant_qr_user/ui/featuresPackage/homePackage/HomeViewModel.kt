package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.homePackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val dataObserveRestaurant: MutableResultWrapper<RestaurantResponse> = MutableResultWrapper()

    fun getDataRestaurant(): MutableResultWrapper<RestaurantResponse> {
        return dataObserveRestaurant
    }

    fun callRestaurant(page: Int) {
        dataObserveRestaurant.postLoading()
        ApiUtils.getHomeService()?.restaurant(ConstantsHelper.Localization,ConstantsHelper.ACCEPT,page)?.
        enqueue(object : Callback<RestaurantResponse?> {
            override fun onResponse(call: Call<RestaurantResponse?>, response: Response<RestaurantResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveRestaurant.postSuccess(response.body()!!)
                    }else{
                        dataObserveRestaurant.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveRestaurant.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<RestaurantResponse?>, t: Throwable) {
                dataObserveRestaurant.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}