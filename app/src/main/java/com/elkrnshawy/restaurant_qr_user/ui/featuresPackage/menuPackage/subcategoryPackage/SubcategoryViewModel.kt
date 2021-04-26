package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackage.subcategoryPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.SubcategoryResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubcategoryViewModel : ViewModel() {
    private val dataObserveSubcategory: MutableResultWrapper<SubcategoryResponse> = MutableResultWrapper()

    fun getDataSubcategory(): MutableResultWrapper<SubcategoryResponse> {
        return dataObserveSubcategory
    }

    fun callSubcategory(categoryID: Int, page: Int) {
        dataObserveSubcategory.postLoading()
        ApiUtils.getHomeService()?.subcategory(ConstantsHelper.Localization, ConstantsHelper.ACCEPT,categoryID,page)?.
        enqueue(object : Callback<SubcategoryResponse?> {
            override fun onResponse(call: Call<SubcategoryResponse?>, response: Response<SubcategoryResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveSubcategory.postSuccess(response.body()!!)
                    }else{
                        dataObserveSubcategory.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveSubcategory.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<SubcategoryResponse?>, t: Throwable) {
                dataObserveSubcategory.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }
}