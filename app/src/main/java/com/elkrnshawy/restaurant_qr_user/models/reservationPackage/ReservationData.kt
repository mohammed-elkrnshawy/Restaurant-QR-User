package com.elkrnshawy.restaurant_qr_user.models.reservationPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ReservationData : Serializable {
    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("phone")
    @Expose
    private var phone: Int? = null

    @SerializedName("notes")
    @Expose
    private var notes: String? = null

    @SerializedName("count")
    @Expose
    private var count: Int? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getPhone(): Int? {
        return phone
    }

    fun setPhone(phone: Int?) {
        this.phone = phone
    }

    fun getNotes(): String? {
        return notes
    }

    fun setNotes(notes: String?) {
        this.notes = notes
    }

    fun getCount(): Int? {
        return count
    }

    fun setCount(count: Int?) {
        this.count = count
    }
}