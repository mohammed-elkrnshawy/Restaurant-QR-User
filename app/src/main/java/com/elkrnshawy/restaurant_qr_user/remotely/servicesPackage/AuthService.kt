package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.authPackage.AuthResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface AuthService {

    @Headers("Accept: application/json")
    @POST("auth/register")
    fun registerUser(
        @Header("X-Localization") localization: String?,
        @Header("Accept") accept: String?,
        @Query("name") name: String?,
        @Query("email") email: String?,
        @Query("phone") phone: String?,
        @Query("password") password: String?,
        @Query("token") token: String?
    ): Call<AuthResponse?>?


    @Headers("Accept: application/json")
    @POST("auth/login")
    fun loginUser(
        @Header("X-Localization") localization: String,
        @Header("Accept") accept: String,
        @Query("phone") phone: String,
        @Query("password") password: String,
        @Query("token") token: String
    ): Call<AuthResponse?>?

}