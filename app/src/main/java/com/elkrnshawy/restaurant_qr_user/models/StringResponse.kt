package com.elkrnshawy.restaurant_qr_user.models

import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import java.util.*

class StringResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: String? = null

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

    fun getData(): String? {
        return data
    }

    fun setData(data: String?) {
        this.data = data
    }
}