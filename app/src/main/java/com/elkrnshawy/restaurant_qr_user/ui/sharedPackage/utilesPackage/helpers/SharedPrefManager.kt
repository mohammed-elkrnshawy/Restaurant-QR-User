package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers

import android.content.Context
import android.content.SharedPreferences
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.google.gson.Gson

class SharedPrefManager {
    companion object {
        private const val SHARED_PREF_NAME = "Restaurant_QR_User"
        private const val LOGIN_STATUS = "login_status"
        private const val FIRST_TIME = "shared_first_time"

        fun setLanguage(ctx: Context, language: String?) {
            val sharedPreferences =
                ctx!!.getSharedPreferences(
                    SHARED_PREF_NAME,
                    0
                )
            val editor = sharedPreferences.edit()
            editor.putString("language", language)
            editor.apply()
        }

        fun getLocalization(context: Context): String? {
            return context.getSharedPreferences(
                "SHARED_PREF_NAME",
                Context.MODE_PRIVATE
            ).getString("language", "ar")
        }

        fun setLoginStatus(status: Boolean?, mContext: Context) {
            val sharedPreferences =
                mContext!!.getSharedPreferences(
                    SHARED_PREF_NAME,
                    0
                )
            val editor = sharedPreferences.edit()
            editor.putBoolean(LOGIN_STATUS, status!!)
            editor.apply()
        }

        fun getLoginStatus(mContext: Context): Boolean? {
            val sharedPreferences =
                mContext!!.getSharedPreferences(
                    SHARED_PREF_NAME, 0
                )
            return sharedPreferences.getBoolean(LOGIN_STATUS, false)
        }

        fun isFirstTime(mContext: Context): Boolean? {
            val sharedPreferences: SharedPreferences =
                mContext.getSharedPreferences(
                    SHARED_PREF_NAME, 0
                )
            return sharedPreferences.getBoolean(FIRST_TIME, true)
        }

        fun setFirstTime(status: Boolean?, mContext: Context) {
            val sharedPreferences: SharedPreferences =
                mContext.getSharedPreferences(
                    SHARED_PREF_NAME,
                    0
                )
            val editor = sharedPreferences.edit()
            editor.putBoolean(FIRST_TIME, status!!)
            editor.apply()
        }

        fun Logout(mContext: Context) {
            val sharedPreferences: SharedPreferences =
                mContext.getSharedPreferences(
                    SHARED_PREF_NAME,
                    0
                )
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            setFirstTime(false, mContext)
        }

        fun getUserData(mContext: Context): UserData? {
            val prefs: SharedPreferences = mContext.getSharedPreferences(
                mContext.getPackageName(),
                Context.MODE_PRIVATE
            )
            val gson = Gson()
            val json = prefs.getString("data", "")
            return gson.fromJson(json, UserData::class.java)
        }

        fun setUserData(data: UserData?, mContext: Context) {
            val editor: SharedPreferences.Editor =
                mContext.getSharedPreferences(mContext.getPackageName(), Context.MODE_PRIVATE)
                    .edit()
            val gson = Gson()
            val json = gson.toJson(data)
            editor.putString("data", json)
            editor.apply()
        }

    }
}