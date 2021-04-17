package com.elkrnshawy.restaurant_qr_user.remotely

import okhttp3.OkHttpClient

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {
    private var retrofit: Retrofit? = null
    private val baseUrl = "https://restaurant.se01.tech/api/"


    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(setTime())
                .build()
        }
        return retrofit
    }

    fun setRetrofit(retrofit: Retrofit) {
        this.retrofit = retrofit
    }

    private fun setTime(): OkHttpClient? {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.MINUTES)
            .connectTimeout(10, TimeUnit.MINUTES)
            .build()
    }
}