package com.elkrnshawy.restaurant_qr_user.models.waitingCountPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WaitingCountData {
    @SerializedName("watinglistCount")
    @Expose
    private var watinglistCount: Int? = null

    fun getWatinglistCount(): Int? {
        return watinglistCount
    }

    fun setWatinglistCount(watinglistCount: Int?) {
        this.watinglistCount = watinglistCount
    }
}