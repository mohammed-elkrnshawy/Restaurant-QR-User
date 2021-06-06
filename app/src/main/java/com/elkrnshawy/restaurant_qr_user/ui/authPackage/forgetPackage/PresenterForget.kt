package com.elkrnshawy.restaurant_qr_user.ui.authPackage.forgetPackage

import com.elkrnshawy.restaurant_qr_user.models.UserData

class PresenterForget {

    private var foretView: ForgetView? = null
    private var userData: UserData? = null

    constructor(foretView: ForgetView?) {
        this.foretView = foretView
    }


    fun callLoginApi(name: String, password: String) {

        userData= UserData()

        userData?.setEmail("")

        //
        //
        //

        if(true){
            foretView?.onSuccessRetrieve("message")
        }else{
            foretView?.onFailureRetrieve()
        }
    }

}