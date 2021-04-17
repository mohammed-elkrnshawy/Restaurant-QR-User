package com.elkrnshawy.restaurant_qr_user.remotely

import com.panorama.resaltelslamacadmy.remote.servicesPackage.AuthService
import com.panorama.resaltelslamacadmy.remote.servicesPackage.HomeService

class ApiUtils {
    companion object {

        fun getUserService(): AuthService? {
            return RetrofitClient().getClient()?.create(AuthService::class.java)
        }

        fun getHomeService(): HomeService? {
            return RetrofitClient().getClient()?.create(HomeService::class.java)
        }
    }
}