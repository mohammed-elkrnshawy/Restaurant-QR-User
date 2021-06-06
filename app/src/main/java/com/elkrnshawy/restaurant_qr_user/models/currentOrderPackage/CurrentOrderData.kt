package com.elkrnshawy.restaurant_qr_user.models.currentOrderPackage

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CurrentOrderData : Serializable {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("user")
    @Expose
    private var user: String? = null

    @SerializedName("restaurant")
    @Expose
    private var restaurant: String? = null

    @SerializedName("service")
    @Expose
    private var service: String? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    @SerializedName("order_created_time")
    @Expose
    private var orderCreatedTime: String? = null

    @SerializedName("waiter")
    @Expose
    private var waiter: String? = null

    @SerializedName("assigned_time_from")
    @Expose
    private var assignedTimeFrom: String? = null

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

    fun getRestaurant(): String? {
        return restaurant
    }

    fun setRestaurant(restaurant: String?) {
        this.restaurant = restaurant
    }

    fun getService(): String? {
        return service
    }

    fun setService(service: String?) {
        this.service = service
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    fun getOrderCreatedTime(): String? {
        return orderCreatedTime
    }

    fun setOrderCreatedTime(orderCreatedTime: String?) {
        this.orderCreatedTime = orderCreatedTime
    }

    fun getWaiter(): String? {
        return waiter
    }

    fun setWaiter(waiter: String?) {
        this.waiter = waiter
    }

    fun getAssignedTimeFrom(): String? {
        return assignedTimeFrom
    }

    fun setAssignedTimeFrom(assignedTimeFrom: String?) {
        this.assignedTimeFrom = assignedTimeFrom
    }
}