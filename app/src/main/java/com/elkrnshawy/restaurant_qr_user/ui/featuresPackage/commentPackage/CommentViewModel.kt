package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.commentPackage

import androidx.lifecycle.ViewModel
import com.elkrnshawy.restaurant_qr_user.models.commentPackage.CommentResponse
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.MutableResultWrapper
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductResponse
import com.elkrnshawy.restaurant_qr_user.remotely.ApiUtils
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentViewModel : ViewModel() {

    private val dataObserveComment: MutableResultWrapper<CommentResponse> = MutableResultWrapper()

    fun getDataComment(): MutableResultWrapper<CommentResponse> {
        return dataObserveComment
    }

    fun callComment(token: String, restaurantID: Int, page: Int) {
        dataObserveComment.postLoading()
        ApiUtils.getHomeService()?.getComments(ConstantsHelper.BEARER_TOKEN+token, ConstantsHelper.Localization,
                ConstantsHelper.ACCEPT,restaurantID)?.enqueue(object : Callback<CommentResponse?> {
            override fun onResponse(call: Call<CommentResponse?>, response: Response<CommentResponse?>) {
                if (response.isSuccessful){
                    if (response.body()?.getStatus()!!){
                        dataObserveComment.postSuccess(response.body()!!)
                    }else{
                        dataObserveComment.postError(GenericError(response.body()!!.getMessage(),null,response.code(),true))
                    }
                }else{
                    dataObserveComment.postError(GenericError(response.message(),null,response.code(),true))
                }
            }

            override fun onFailure(call: Call<CommentResponse?>, t: Throwable) {
                dataObserveComment.postError(GenericError(t.localizedMessage,null,call.execute().code(),true))
            }
        })
    }

}