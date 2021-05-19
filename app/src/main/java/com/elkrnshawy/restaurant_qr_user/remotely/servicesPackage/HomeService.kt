package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.SubcategoryResponse
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductResponse
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantCodeResponse
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantResponse
import retrofit2.Call
import retrofit2.http.*


interface HomeService {

    @Headers("Accept: application/json")
    @GET("restaurants")
    fun restaurant(
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Query("page") page: Int
    ): Call<RestaurantResponse>?

    @Headers("Accept: application/json")
    @GET("getCode/{code}")
    fun restaurantQR(
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Path("code") code: String
    ): Call<RestaurantCodeResponse>?

    @Headers("Accept: application/json")
    @GET("categories/{categoryID}")
    fun subcategory(
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Path("categoryID") categoryID: Int,
            @Query("page") page: Int
    ): Call<SubcategoryResponse>?

    @Headers("Accept: application/json")
    @GET("products/{subcategoryID}")
    fun product(
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Path("subcategoryID") subcategoryID: Int,
            @Query("page") page: Int
    ): Call<ProductResponse>?
}