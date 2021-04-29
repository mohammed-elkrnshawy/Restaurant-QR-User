package com.elkrnshawy.restaurant_qr_user.models.waitingCountPackage


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WaitingCountResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: WaitingCountData? = null

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

    fun getData(): WaitingCountData? {
        return data
    }

    fun setData(data: WaitingCountData?) {
        this.data = data
    }
}