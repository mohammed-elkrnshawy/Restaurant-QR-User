package com.elkrnshawy.restaurant_qr_user.models.restaurantPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class RestaurantCodeData : Serializable {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("number")
    @Expose
    private var number: Int? = null

    @SerializedName("count")
    @Expose
    private var count: Int? = null

    @SerializedName("restaurant")
    @Expose
    private var restaurant: RestaurantItem? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getNumber(): Int? {
        return number
    }

    fun setNumber(number: Int?) {
        this.number = number
    }

    fun getCount(): Int? {
        return count
    }

    fun setCount(count: Int?) {
        this.count = count
    }

    fun getRestaurant(): RestaurantItem? {
        return restaurant
    }

    fun setRestaurant(restaurant: RestaurantItem?) {
        this.restaurant = restaurant
    }
}