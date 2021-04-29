package com.elkrnshawy.restaurant_qr_user.remotely

import com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage.AuthService
import com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage.HomeService
import com.elkrnshawy.restaurant_qr_user.remotely.servicesPackage.ReservationService

class ApiUtils {
    companion object {

        fun getUserService(): AuthService? {
            return RetrofitClient().getClient()?.create(AuthService::class.java)
        }

        fun getHomeService(): HomeService? {
            return RetrofitClient().getClient()?.create(HomeService::class.java)
        }

        fun getReservationService(): ReservationService? {
            return RetrofitClient().getClient()?.create(ReservationService::class.java)
        }
    }
}