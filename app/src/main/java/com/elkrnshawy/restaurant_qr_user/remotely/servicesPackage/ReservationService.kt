package com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage

import com.elkrnshawy.restaurant_qr_user.models.reservationPackage.ReservationResponse
import retrofit2.Call
import retrofit2.http.*

interface ReservationService {

    @FormUrlEncoded
    @POST("reservation")
    fun reservation(
            @Header("Authorization") authorization: String,
            @Header("X-Localization") localization: String,
            @Header("Accept") accept: String,
            @Field("restaurant_id") restaurant_id: Int,
            @Field("name") name: String,
            @Field("phone") phone: String,
            @Field("notes") notes: String,
            @Field("count") count: Int
    ): Call<ReservationResponse>?

}