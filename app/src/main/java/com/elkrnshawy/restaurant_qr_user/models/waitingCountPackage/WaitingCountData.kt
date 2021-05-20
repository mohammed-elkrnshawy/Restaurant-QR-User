package com.elkrnshawy.restaurant_qr_user.models.waitingCountPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WaitingCountData {
    @SerializedName("watinglistCount")
    @Expose
    private var watinglistCount: Int? = null

    @SerializedName("exist")
    @Expose
    private var exist: Boolean? = null

    @SerializedName("watinglistNumber")
    @Expose
    private var watinglistNumber: Int? = null

    fun getWatinglistCount(): Int? {
        return watinglistCount
    }

    fun setWatinglistCount(watinglistCount: Int?) {
        this.watinglistCount = watinglistCount
    }

    fun getExist(): Boolean? {
        return exist
    }

    fun setExist(exist: Boolean?) {
        this.exist = exist
    }

    fun getWatinglistNumber(): Int? {
        return watinglistNumber
    }

    fun setWatinglistNumber(watinglistNumber: Int?) {
        this.watinglistNumber = watinglistNumber
    }

}