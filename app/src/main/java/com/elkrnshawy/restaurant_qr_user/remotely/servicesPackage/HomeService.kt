package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.SubcategoryResponse
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantResponse
import retrofit2.Call
import retrofit2.http.*


interface HomeService {

    @Headers("Accept: application/json")
    @GET("restaurants")
    fun restaurant(
            @Header("X-Localization") localization: String,
            @Header("Accept") accept: String,
            @Query("page") page: Int
    ): Call<RestaurantResponse>?

    @Headers("Accept: application/json")
    @GET("categories/{categoryID}")
    fun subcategory(
            @Header("X-Localization") localization: String,
            @Header("Accept") accept: String,
            @Path("categoryID") categoryID: Int,
            @Query("page") page: Int
    ): Call<SubcategoryResponse>?
}