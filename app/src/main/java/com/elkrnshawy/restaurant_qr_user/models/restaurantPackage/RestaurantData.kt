package com.elkrnshawy.restaurant_qr_user.models.restaurantPackage

import com.elkrnshawy.restaurant_qr_user.models.Paginate
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class RestaurantData : Serializable {
    @SerializedName("items")
    @Expose
    private var items: List<RestaurantItem?>? = null

    @SerializedName("paginate")
    @Expose
    private var paginate: Paginate? = null

    fun getItems(): List<RestaurantItem?>? {
        return items
    }

    fun setItems(items: List<RestaurantItem?>?) {
        this.items = items
    }

    fun getPaginate(): Paginate? {
        return paginate
    }

    fun setPaginate(paginate: Paginate?) {
        this.paginate = paginate
    }
}