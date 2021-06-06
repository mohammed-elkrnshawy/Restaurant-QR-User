package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.elkrnshawy.restaurant_qr_user.R
import com.squareup.picasso.Picasso



@BindingAdapter("imageUriOffline")
fun imageUriOffline(
        imageView: ImageView,
        imageUriOffline: Uri?) {
    if (imageUriOffline!=null)
    {
        imageView.setImageURI(imageUriOffline)
    }
}

@BindingAdapter("imageResource")
fun loadImage(image: ImageView, url: String?) {
    if (url != null && url != "") {
        Picasso.get().load(url)
            .placeholder(image.context.resources.getDrawable(R.drawable.ic_logo)).into(image)
    }
}

fun convertPrice(context: Context, price: Double): String {
    return price.toString()+" "+context.resources.getString(R.string.SAR)
}

fun idReservation(num: Int): String {
    return "#$num"
}

fun convertStatus(context: Context, status: String): String? {
    Log.e("STATUS_PRINT", status)
    return status.replace(ConstantsHelper.ASSIGN_PENDING, context.resources.getString(R.string.status_assigned))

}

fun convertString(num: Int): String {
    return num.toString()
}

fun convertString(num: Double): String {
    return num.toString()
}

fun convertString(num: Float): String {
    return num.toString()
}

fun commentUser(name: String): String {
    return "_$name***"
}

fun commentDate(date: String): String {
    val s =date.split("T")[0].toString()
    return "($s)"
}

fun arabicBackground(context: Context): Int {
    return if (ConstantsHelper.Localization == "ar") {
        context.resources.getColor(R.color.colorPrimaryDark)
    } else {
        context.resources.getColor(R.color.colorWhite)
    }
}

fun englishBackground(context: Context): Int {
    return if (ConstantsHelper.Localization == "ar") {
        context.resources.getColor(R.color.colorWhite)
    } else {
        context.resources.getColor(R.color.colorPrimaryDark)
    }
}
