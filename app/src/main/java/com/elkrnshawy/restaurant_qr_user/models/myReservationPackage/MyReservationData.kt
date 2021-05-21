package com.elkrnshawy.restaurant_qr_user.models.myReservationPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyReservationData : Serializable{
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("restaurant")
    @Expose
    private var restaurant: String? = null

    @SerializedName("restaurant_image")
    @Expose
    private var restaurantImage: String? = null

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

    @SerializedName("date")
    @Expose
    private var date: String? = null

    @SerializedName("time")
    @Expose
    private var time: String? = null

    @SerializedName("user")
    @Expose
    private var user: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("created_at")
    @Expose
    private var createdAt: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getRestaurant(): String? {
        return restaurant
    }

    fun setRestaurant(restaurant: String?) {
        this.restaurant = restaurant
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

    fun getDate(): String? {
        return date
    }

    fun setDate(date: String?) {
        this.date = date
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String?) {
        this.time = time
    }

    fun getUser(): String? {
        return user
    }

    fun setUser(user: String?) {
        this.user = user
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getRestaurantImage(): String? {
        return restaurantImage
    }

    fun setRestaurantImage(restaurantImage: String?) {
        this.restaurantImage = restaurantImage
    }
}