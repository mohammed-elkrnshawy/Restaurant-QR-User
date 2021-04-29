package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers

import android.app.Activity
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.elkrnshawy.restaurant_qr_user.R


class Validator {

    /*global field validator for this app*/
    private val EMAIL_VERIFICATION = "^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"
    private val PHONE_VERIFICATION = "^05[\\d]{8}$"
    private val PHONE_EGYPT_VERIFICATION = "^01[\\d]{9}$"

    //this function validates Internet Connection
    /*fun checkConnection(context: Context?): Boolean {
        return if (NetworkUtil.getConnectivityStatusString(context) === NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
            true
        } else {
            false
        }
    }*/

    /*this function validates a array of edit text */
    fun validateInputField(
            array: Array<EditText>,
            context: Activity
    ): Boolean {
        var count = 0
        for (editText in array) {
            if (editText.text.toString().length == 0) {
                if (editText.tag.toString() == context.resources.getString(R.string.require_field)) Toast.makeText(
                        context,
                        editText.tag.toString(),
                        Toast.LENGTH_SHORT
                ).show() else Toast.makeText(
                        context,
                        editText.tag.toString() + " " + context.resources.getString(R.string.require_field),
                        Toast.LENGTH_LONG
                ).show()
                break
            } else {
                if (editText.tag.toString() == context.resources.getString(R.string.email)) {
                    if (editText.text.toString().trim { it <= ' ' }
                                    .matches(EMAIL_VERIFICATION.toRegex())) count++ else {
                        Toast.makeText(
                                context,
                                editText.tag.toString() + " " + context.resources.getString(R.string.require_field),
                                Toast.LENGTH_SHORT
                        ).show()
                        break
                    }
                } else if (editText.tag.toString() == context.resources.getString(R.string.phone)) {
                    if (editText.text.toString().trim { it <= ' ' }
                                    .matches(PHONE_VERIFICATION.toRegex()) || editText.text.toString()
                                    .trim { it <= ' ' }
                                    .matches(PHONE_EGYPT_VERIFICATION.toRegex())
                    ) count++ else {
                        Toast.makeText(
                                context,
                                editText.tag.toString() + " " + context.resources.getString(R.string.phone_not_correct),
                                Toast.LENGTH_SHORT
                        ).show()
                        break
                    }
                } else if (editText.tag.toString() == context.resources.getString(R.string.password)) {
                    var error = ""
                    if (editText.text.toString().length < 6) error =
                            editText.tag.toString() + " " + context.resources.getString(R.string.is_weak) + ", " + context.resources.getString(
                                    R.string.is_weak
                            )
                    if (!error.isEmpty()) {
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                        break
                    } else count++
                } else {
                    if (!TextUtils.isEmpty(
                                    editText.text.toString().trim { it <= ' ' })
                    ) count++ else {
                        Toast.makeText(
                                context,
                                editText.tag.toString() + " " + context.resources.getString(R.string.require_field),
                                Toast.LENGTH_SHORT
                        ).show()
                        break
                    }
                }
            }
        }
        return array.size == count
    }
}