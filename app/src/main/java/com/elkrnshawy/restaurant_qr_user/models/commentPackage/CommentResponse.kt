package com.elkrnshawy.restaurant_qr_user.models.commentPackage

import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class CommentResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: List<CommentItem>? = null

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

    fun getData(): List<CommentItem>? {
        return data
    }

    fun setData(data: List<CommentItem>?) {
        this.data = data
    }
}