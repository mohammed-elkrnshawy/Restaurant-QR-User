package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper

import android.view.View
import com.elkrnshawy.restaurant_qr_user.models.generalResponse.GenericError

interface setupView {

    fun getIntentData()

    fun initSettings()

    fun setupComponents(view: View?)

    fun onComponentsClick()

    fun onRetryClick()

    fun showMainLoading()

    fun hideMainLoading()

    fun showLoadMoreProgress()

    fun hideLoadMoreProgress()

    fun showSubLoading()

    fun hideSubLoading()

    fun handleErrorMsg(error: GenericError?)
}