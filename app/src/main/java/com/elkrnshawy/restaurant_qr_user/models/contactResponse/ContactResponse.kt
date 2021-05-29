package com.elkrnshawy.restaurant_qr_user.models.contactResponse

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ContactResponse {
    @SerializedName("status")
    @Expose
    private var status: Boolean? = null

    @SerializedName("message")
    @Expose
    private var message: String? = null

    @SerializedName("data")
    @Expose
    private var data: ContactData? = null

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

    fun getData(): ContactData? {
        return data
    }

    fun setData(data: ContactData?) {
        this.data = data
    }

    class ContactData{
        @SerializedName("id")
        @Expose
        private var id: Int? = null

        @SerializedName("name")
        @Expose
        private var name: String? = null

        @SerializedName("phone")
        @Expose
        private var phone: Int? = null

        @SerializedName("message")
        @Expose
        private var message: String? = null

        fun getId(): Int? {
            return id
        }

        fun setId(id: Int?) {
            this.id = id
        }

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

        fun getMessage(): String? {
            return message
        }

        fun setMessage(message: String?) {
            this.message = message
        }
    }
}