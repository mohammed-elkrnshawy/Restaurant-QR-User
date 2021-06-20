package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.authPackage.AuthResponse
import retrofit2.Call
import retrofit2.http.*


interface AuthService {

    @FormUrlEncoded
    @POST("auth/register")
    fun registerUser(
        @Header("Content-Language") localization: String?,
        @Header("Accept") accept: String?,
        @Field("device") device: String?,
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("phone") phone: String?,
        @Field("password") password: String?,
        @Field("token") token: String?
    ): Call<AuthResponse?>?


    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(
        @Header("Content-Language") localization: String,
        @Header("Accept") accept: String,
        @Field("device") device: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("token") token: String
    ): Call<AuthResponse?>?

}