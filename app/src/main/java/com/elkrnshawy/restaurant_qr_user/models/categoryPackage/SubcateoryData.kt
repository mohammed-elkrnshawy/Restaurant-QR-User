package com.elkrnshawy.restaurant_qr_user.models.categoryPackage

import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class SubcateoryData : Serializable {
    @SerializedName("items")
    @Expose
    private var items: List<CategoryItem>? = null

    @SerializedName("paginate")
    @Expose
    private var paginate: Paginate? = null

    fun getItems(): List<CategoryItem>? {
        return items
    }

    fun setItems(items: List<CategoryItem>?) {
        this.items = items
    }

    fun getPaginate(): Paginate? {
        return paginate
    }

    fun setPaginate(paginate: Paginate?) {
        this.paginate = paginate
    }
}