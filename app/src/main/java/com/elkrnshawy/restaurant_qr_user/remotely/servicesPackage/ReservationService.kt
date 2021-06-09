package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.contactResponse.ContactResponse
import com.elkrnshawy.restaurant_qr_user.models.StringResponse
import com.elkrnshawy.restaurant_qr_user.models.joinWaitingListPackage.JoinWaitingResponse
import com.elkrnshawy.restaurant_qr_user.models.myReservationPackage.MyReservationResponse
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
            @Field("table_id") table_id: Int,
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


    @GET("myReservations")
    fun getReservation(
        @Header("Authorization") authorization: String,
        @Header("Content-Language") localization: String,
        @Header("Accept") accept: String
    ): Call<MyReservationResponse>?


    @GET("waitinglist/{id}")
    fun waitingCount(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Path("id") restaurant_id: Int
    ): Call<WaitingCountResponse>?

    @GET("remove_waitinglist/{id}")
    fun removeWaiting(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Path("id") waitingListNumber: Int
    ): Call<StringResponse>?

    @FormUrlEncoded
    @POST("join_waitinglist")
    fun joinWaiting(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Field("restaurant_id") restaurant_id: Int,
            @Field("count") count: Int
    ): Call<JoinWaitingResponse>?

    @FormUrlEncoded
    @POST("contact")
    fun contactRestaurant(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Field("restaurant_id") restaurant_id: Int,
            @Field("name") name: String,
            @Field("phone") phone: String,
            @Field("message") message: String
    ): Call<ContactResponse>?

    @FormUrlEncoded
    @POST("contact")
    fun contactAdmin(
            @Header("Authorization") authorization: String,
            @Header("Content-Language") localization: String,
            @Header("Accept") accept: String,
            @Field("name") name: String,
            @Field("phone") phone: String,
            @Field("message") message: String
    ): Call<ContactResponse>?

}