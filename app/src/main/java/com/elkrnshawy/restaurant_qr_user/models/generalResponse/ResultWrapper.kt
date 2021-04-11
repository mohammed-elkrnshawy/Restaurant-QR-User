package com.elkrnshawy.restaurant_qr_user.models.generalResponse

open class ResultWrapper<T> {

    lateinit var status: Status
    lateinit var error: GenericError
    var data: T? = null


    fun loading(): ResultWrapper<T>? {
        this.status = Status.Loading
        this.data = null
        this.error = GenericError("",Throwable(),0,false)
        return this
    }

    fun success(data: T):ResultWrapper<T>? {
        status = Status.Success
        this.data = data
        this.error = GenericError("",Throwable(),0,false)
        return this
    }
    fun error(error: GenericError?): ResultWrapper<T>? {
        status = Status.Failure
        this.error = error!!
        this.data = null
        return this
    }

    fun responseArrived(data: T): ResultWrapper<T>?{
        status = Status.ResponseArrived
        this.data = data
        this.error = GenericError("",Throwable(),0,false)
        return this
    }

}