package com.elkrnshawy.restaurant_qr_user.models.currentOrderPackage

import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.util.*

class CurrentOrderResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("msg")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: CurrentOrderData? = null

    fun getStatus(): Boolean? {
        return status
    }

    fun setStatus(status: Boolean?) {
        this.status = status
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    fun getData(): CurrentOrderData? {
        return data
    }

    fun setData(data: CurrentOrderData?) {
        this.data = data
    }
}