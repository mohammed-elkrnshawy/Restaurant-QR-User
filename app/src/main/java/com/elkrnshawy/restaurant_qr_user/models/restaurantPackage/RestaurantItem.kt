package com.elkrnshawy.restaurant_qr_user.models.restaurantPackage

import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.servicePackage.ServiceItem
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class RestaurantItem : Serializable {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("phone")
    @Expose
    private var phone: String? = null

    @SerializedName("email")
    @Expose
    private var email: String? = null

    @SerializedName("image")
    @Expose
    private var image: String? = null

    @SerializedName("description")
    @Expose
    private var description: String? = null

    @SerializedName("services")
    @Expose
    private var services: List<ServiceItem?>? = null

    @SerializedName("categories")
    @Expose
    private var categories: List<CategoryItem?>? = null

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

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String?) {
        this.phone = phone
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getImage(): String? {
        return image
    }

    fun setImage(image: String?) {
        this.image = image
    }

    fun getServices(): List<ServiceItem?>? {
        return services
    }

    fun setServices(services: List<ServiceItem?>?) {
        this.services = services
    }

    fun getCategories(): List<CategoryItem?>? {
        return categories
    }

    fun setCategories(categories: List<CategoryItem?>?) {
        this.categories = categories
    }
}