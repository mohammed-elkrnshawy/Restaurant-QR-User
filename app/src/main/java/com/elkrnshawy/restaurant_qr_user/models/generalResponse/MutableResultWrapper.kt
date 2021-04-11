package com.elkrnshawy.restaurant_qr_user.models.generalResponse

import androidx.lifecycle.MutableLiveData


class MutableResultWrapper<T> : MutableLiveData<ResultWrapper<T>?>() {
    fun postLoading() {
        postValue(ResultWrapper<T>().loading())
    }

    fun postError(error: GenericError?) {
        postValue(ResultWrapper<T>().error(error))
    }

    fun postSuccess(data: T) {
        postValue(ResultWrapper<T>().success(data))
    }

    fun postResponseArrived(data: T) {
        postValue(ResultWrapper<T>().responseArrived(data))
    }
}
