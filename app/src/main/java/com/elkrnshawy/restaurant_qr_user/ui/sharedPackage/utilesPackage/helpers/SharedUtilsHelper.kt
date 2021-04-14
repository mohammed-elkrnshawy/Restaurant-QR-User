package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager.Companion.getLocalization
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager.Companion.setLanguage
import com.facebook.drawee.backends.pipeline.Fresco
import com.stfalcon.frescoimageviewer.ImageViewer
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class SharedUtilsHelper {
    companion object {

        fun handleLanguage(context: Context) {
            if (getLocalization(context).equals("ar")) {
                changeLanguage("ar", context)
            } else {
                changeLanguage("en", context)
            }
        }

        fun changeLanguage(language: String?, context: Context) {
            setLanguage(context, language)
            val config = Configuration()
            config.locale = Locale(language)
            context.resources.updateConfiguration(config, context.resources.displayMetrics)

        }

        fun <T> isEmptyList(list: List<T>?, view: View): Boolean {
            return if (list != null && list.size > 0) {
                view.visibility = View.GONE
                false
            } else {
                view.visibility = View.VISIBLE
                true
            }
        }

        fun getYouTubeId(youTubeUrl: String?): String? {
            val pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
            val compiledPattern: Pattern = Pattern.compile(pattern)
            val matcher: Matcher = compiledPattern.matcher(youTubeUrl)
            return if (matcher.find()) {
                matcher.group()
            } else {
                "error"
            }
        }

        fun <T> isEmptyObject(dataObject: T?, view: View): Boolean {
            return if (dataObject != null) {
                view.visibility = View.GONE
                false
            } else {
                view.visibility = View.VISIBLE
                true
            }
        }

        fun convertTimestampDateToTime(timestamp: Long): String? {
            val tStamp = Timestamp(timestamp)
            val simpleDateFormat: SimpleDateFormat
            simpleDateFormat = SimpleDateFormat("dd MMM, yyyy hh:mm a")
            return simpleDateFormat.format(tStamp)
        }

        fun getCurrentTimeInMilliseconds(): Long {
            return System.currentTimeMillis()
        }

        @SuppressLint("HardwareIds")
        fun getDeviceID(context: Context): String? {
            return Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }

        fun copyToCliboard(context: Context, label: String?, text: String?) {
            val clipboard =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label, text)
            clipboard.setPrimaryClip(clip)
        }


//    @BindingAdapter("imageUrl")
//    fun loadImageFromUrl(
//        imageView: ImageView,
//        imageUrl: String?
//    ) {
//        var imageUrl = imageUrl
//        if (imageUrl == null || imageUrl.length == 0) {
//            imageUrl = "0"
//        }
//        Log.d("msm", "loadImageFromUrl: 87979$imageUrl")
//        Glide.with(imageView.context)
//            .load(imageUrl)
//            .placeholder(R.drawable.image_loading)
//            .error(R.drawable.img_no_image)
//            .into(imageView)
//    }



        fun loadImage(imageView: ImageView, url: String?, imageWidth: Int, imageHeight: Int) {
            var url = url
            if (url == null || url.length == 0) {
                url = "0"
            }
//            Glide.with(imageView.context)
//                .load(url)
//                .apply(RequestOptions().override(imageWidth, imageHeight))
//                .placeholder(R.drawable.image_loading)
//                .error(R.drawable.img_no_image)
//                .into(imageView)
        }

        fun zoomImage(activity: Activity?, imageList: List<String?>?, position: Int) {
            Fresco.initialize(activity)
            ImageViewer.Builder<String>(activity, imageList)
                .setStartPosition(position)
                .hideStatusBar(false)
                .allowZooming(true)
                .allowSwipeToDismiss(true)
                .setBackgroundColorRes(R.color.black)
                .show()
        }

        fun zoomImage(activity: Activity?, imageUrl: String) {
            val imageList: MutableList<String> =
                ArrayList()
            imageList.add(imageUrl)
            Fresco.initialize(activity)
            ImageViewer.Builder<String>(activity, imageList)
                .hideStatusBar(false)
                .allowZooming(true)
                .allowSwipeToDismiss(true)
                .setBackgroundColorRes(R.color.black)
                .show()
        }

        fun datePicker(context: Context, txtCalender: TextView) {
            val newCalendar = Calendar.getInstance()
            val simpleFormat = SimpleDateFormat("yyyy-MM-dd")
            val startTime = DatePickerDialog(context, { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[year, monthOfYear] = dayOfMonth
                txtCalender.text=simpleFormat.format(newDate.time)
            },
                newCalendar[Calendar.YEAR],
                newCalendar[Calendar.MONTH],
                newCalendar[Calendar.DAY_OF_MONTH]
            )
            startTime.datePicker.minDate = System.currentTimeMillis() - 1000
            startTime.show()
        }

    }

}