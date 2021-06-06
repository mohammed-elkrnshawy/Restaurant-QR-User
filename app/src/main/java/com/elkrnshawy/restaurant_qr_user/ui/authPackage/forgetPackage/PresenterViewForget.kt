package com.elkrnshawy.restaurant_qr_user.ui.authPackage.forgetPackage

interface PresenterViewForget {

    fun validateData(name: String , password: String)

    fun callLoginApi()

}