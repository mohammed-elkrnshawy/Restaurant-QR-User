package com.elkrnshawy.restaurant_qr_user.models.productPackage

import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ProductData : Serializable {
    @SerializedName("items")
    @Expose
    private var items: List<ProductItem?>? = null

    @SerializedName("paginate")
    @Expose
    private var paginate: Paginate? = null

    fun getItems(): List<ProductItem?>? {
        return items
    }

    fun setItems(items: List<ProductItem?>?) {
        this.items = items
    }

    fun getPaginate(): Paginate? {
        return paginate
    }

    fun setPaginate(paginate: Paginate?) {
        this.paginate = paginate
    }
}