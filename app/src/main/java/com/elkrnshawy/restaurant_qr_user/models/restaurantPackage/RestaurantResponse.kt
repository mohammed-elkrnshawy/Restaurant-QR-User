package com.elkrnshawy.restaurant_qr_user.models.restaurantPackage

import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class RestaurantResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: RestaurantData? = null

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

    fun getData(): RestaurantData? {
        return data
    }

    fun setData(data: RestaurantData?) {
        this.data = data
    }
}