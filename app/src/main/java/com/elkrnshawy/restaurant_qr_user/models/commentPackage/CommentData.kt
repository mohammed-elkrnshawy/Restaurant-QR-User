package com.elkrnshawy.restaurant_qr_user.models.commentPackage

import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CommentData : Serializable {
    @SerializedName("items")
    @Expose
    private var items: List<CommentItem>? = null

    @SerializedName("paginate")
    @Expose
    private var paginate: Paginate? = null

    fun getItems(): List<CommentItem>? {
        return items
    }

    fun setItems(items: List<CommentItem>?) {
        this.items = items
    }

    fun getPaginate(): Paginate? {
        return paginate
    }

    fun setPaginate(paginate: Paginate?) {
        this.paginate = paginate
    }
}