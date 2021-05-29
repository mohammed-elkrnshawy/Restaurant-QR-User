package com.elkrnshawy.restaurant_qr_user.models.restaurantPackage

import android.content.Context
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.commentPackage.CommentItem
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

    @SerializedName("rates")
    @Expose
    private var rates: Float? = null

    @SerializedName("lat")
    @Expose
    private var lat: String? = null

    @SerializedName("lng")
    @Expose
    private var lng: String? = null

    @SerializedName("services")
    @Expose
    private var services: List<ServiceItem?>? = null

    @SerializedName("categories")
    @Expose
    private var categories: List<CategoryItem?>? = null

    @SerializedName("comments")
    @Expose
    private var comments: List<CommentItem?>? = null

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

    fun getRates(): Float? {
        return rates
    }

    fun getLat(): Double? {
        return if (lat?.isEmpty() == true)
            0.0
        else
            lat?.toDouble()
    }

    fun getLng(): Double? {
        return if (lng?.isEmpty() == true)
            0.0
        else
            lng?.toDouble()
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

    fun getComments(): List<CommentItem?>? {
        return comments
    }

    fun setComments(comments: List<CommentItem?>?) {
        this.comments = comments
    }

    fun getCategoriesString(context: Context) : String {
        var string = ""
        for (item:CategoryItem? in categories!!){
            string +=item?.getName()+", "
        }
        string = if (string.isNotEmpty())
            string.substring(0,(string.length-2))
        else
            context.resources.getString(R.string.no_category)

        return string
    }
}