package com.elkrnshawy.restaurant_qr_user.models.commentPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CommentItem : Serializable{
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("user")
    @Expose
    private var user: String? = null

    @SerializedName("comment")
    @Expose
    private var comment: String? = null

    @SerializedName("rate")
    @Expose
    private var rate: Double? = null

    @SerializedName("created_at")
    @Expose
    private var created_at: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getUser(): String? {
        return user
    }

    fun setUser(user: String?) {
        this.user = user
    }

    fun getComment(): String? {
        return comment
    }

    fun setComment(comment: String?) {
        this.comment = comment
    }

    fun getRate(): Double? {
        return rate
    }

    fun setRate(rate: Double?) {
        this.rate = rate
    }

    fun getCreated(): String? {
        return created_at
    }

    fun setCreated(created_at: String?) {
        this.created_at = created_at
    }
}