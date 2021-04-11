package com.elkrnshawy.restaurant_qr_user.models.generalResponse

data class GenericError(var msg:String?=null, var throwable: Throwable?=null, var responseCode:Int?=null, var isToast:Boolean=true)