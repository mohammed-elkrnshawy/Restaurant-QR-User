package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.StringResponse
import com.elkrnshawy.restaurant_qr_user.models.reservationPackage.ReservationResponse
import com.elkrnshawy.restaurant_qr_user.models.waitingCountPackage.WaitingCountResponse
import retrofit2.Call
import retrofit2.http.*

interface ReservationService {

    @FormUrlEncoded
    @POST("order")
    fun orderService(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Field("restaurant_id") restaurant_id: Int,
            @Field("user_id") user_id: Int,
            @Field("service_id") service_id: Int
    ): Call<StringResponse>?

    @FormUrlEncoded
    @POST("reservation")
    fun reservation(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Field("restaurant_id") restaurant_id: Int,
            @Field("name") name: String,
            @Field("phone") phone: String,
            @Field("notes") notes: String,
            @Field("count") count: Int,
            @Field("date") date: String,
            @Field("time") time: String
    ): Call<ReservationResponse>?


    @GET("waitinglist/{id}")
    fun waitingCount(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Path("id") restaurant_id: Int
    ): Call<WaitingCountResponse>?

}