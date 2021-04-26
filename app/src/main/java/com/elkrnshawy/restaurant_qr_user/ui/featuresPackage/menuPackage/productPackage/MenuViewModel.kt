package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.productPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.SubcategoryResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuViewModel : ViewModel() {
    private val dataObserveProduct: MutableResultWrapper<ProductResponse> = MutableResultWrapper()

    fun getDataProduct(): MutableResultWrapper<ProductResponse> {
        return dataObserveProduct
    }

    fun callProduct(subcategoryID: Int, page: Int) {
        dataObserveProduct.postLoading()
        ApiUtils.getHomeService()?.product(ConstantsHelper.Localization, ConstantsHelper.ACCEPT,subcategoryID,page)?.
        enqueue(object : Callback<ProductResponse?> {
            override fun onResponse(call: Call<ProductResponse?>, response: Response<ProductResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveProduct.postSuccess(response.body()!!)
                    }else{
                        dataObserveProduct.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveProduct.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                dataObserveProduct.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}