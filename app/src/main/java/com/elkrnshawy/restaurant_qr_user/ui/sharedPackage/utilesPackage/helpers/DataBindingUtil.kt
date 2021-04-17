package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers

import android.net.Uri
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

fun convertString(i: Int): String {
    return i.toString()
}

