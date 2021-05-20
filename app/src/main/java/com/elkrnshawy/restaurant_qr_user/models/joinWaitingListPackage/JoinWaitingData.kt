package com.elkrnshawy.restaurant_qr_user.models.joinWaitingListPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class JoinWaitingData {
    @SerializedName("watinglistCount")
    @Expose
    private var watinglistCount: Int? = null

    @SerializedName("number")
    @Expose
    private var number: Int? = null

    fun getWatinglistCount(): Int? {
        return watinglistCount
    }

    fun setWatinglistCount(watinglistCount: Int?) {
        this.watinglistCount = watinglistCount
    }

    fun getNumber(): Int? {
        return number
    }

    fun setNumber(number: Int?) {
        this.number = number
    }
}