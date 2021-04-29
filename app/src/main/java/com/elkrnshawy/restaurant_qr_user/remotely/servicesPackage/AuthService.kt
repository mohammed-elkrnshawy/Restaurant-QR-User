package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.authPackage.AuthResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthService {

    @Headers("Accept: application/json")
    @POST("register/user")
    fun registerUser(
        @Header("X-Localization") localization: String?,
        @Header("Accept") accept: String?,
        @Query("type") device: String?,
        @Query("name") name: String?,
        @Query("phone") phone: String?,
        @Query("email") email: String?,
        @Query("password") password: String?,
        @Query("password_confirmation") password_confirmation: String?,
        @Query("age") age: Int?,
        @Query("gender") gender: String?,
        @Query("nationality_id") nationality_id: Int?,
        @Query("country_id") country_id: Int,
        @Query("facebook_page") facebook_page: String?,
        @Query("how_know_us") how_know_us: String?,
        @Query("islam_date") islam_date: String?,
        @Query("lang_id") lang_id: Int?,
        @Query("job") job: String?,
        @Query("fcm") fcm: String?
    ): Call<AuthResponse?>?


    @Headers("Accept: application/json")
    @POST("auth/login")
    fun loginUser(
        @Header("X-Localization") localization: String,
        @Header("Accept") accept: String,
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("fcm") fcm: String
    ): Call<AuthResponse?>?

}