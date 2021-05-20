package com.elkrnshawy.restaurant_qr_user.models.joinWaitingListPackage


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JoinWaitingResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: JoinWaitingData? = null

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

    fun getData(): JoinWaitingData? {
        return data
    }

    fun setData(data: JoinWaitingData?) {
        this.data = data
    }
}