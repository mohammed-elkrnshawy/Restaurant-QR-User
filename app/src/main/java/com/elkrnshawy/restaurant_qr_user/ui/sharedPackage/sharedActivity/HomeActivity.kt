package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.ConstantsHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.NavControllerHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedPrefManager
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.SharedUtilsHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import com.infideap.drawerbehavior.AdvanceDrawerLayout

class HomeActivity : AppCompatActivity() {
    private var txtProfile: TextView? =null
    private var txtReservation: TextView? =null
    private var txtContact: TextView? =null
    private var txtLanguage: TextView? =null
    private var txtLogout: TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupComponents()
        onClickComponents()
    }

    private fun setupComponents(){
        drawer=findViewById(R.id.drawer_layout)
        txtProfile=findViewById(R.id.txtProfile)
        txtReservation=findViewById(R.id.txtReservation)
        txtContact=findViewById(R.id.txtContact)
        txtLanguage=findViewById(R.id.txtLanguage)
        txtLogout=findViewById(R.id.txtLogout)
    }

    private fun onClickComponents(){

        txtProfile?.setOnClickListener {
            if (SharedPrefManager.getLoginStatus(this)!!){
                NavControllerHelper.getNavController()?.navigate(R.id.action_global_profileFragment)
            }else{
                SharedUtilsHelper.loginDialog(this)
            }
            drawer?.close()
        }

        txtReservation?.setOnClickListener {
            if (SharedPrefManager.getLoginStatus(this)!!){
                NavControllerHelper.getNavController()?.navigate(R.id.action_global_reservationFragment)
            }else{
                SharedUtilsHelper.loginDialog(this)
            }
            drawer?.close()
        }

        txtContact?.setOnClickListener {
            NavControllerHelper.getNavController()?.navigate(R.id.action_global_contactFragment)
            drawer?.close()
        }

        txtLanguage?.setOnClickListener {
            NavControllerHelper.getNavController()?.navigate(R.id.action_global_languageFragment)
            drawer?.close()
        }

        if (SharedPrefManager.getLoginStatus(this)!!) txtLogout?.visibility=View.VISIBLE
        txtLogout?.setOnClickListener {
            SharedPrefManager.setLoginStatus(false,this)
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
            drawer?.close()
        }
    }

    companion object {
        var drawer: AdvanceDrawerLayout? = null

        fun ifOpened () : Boolean{
            return drawer?.isDrawerOpen(GravityCompat.START)!!
        }

        fun open(){
            drawer?.openDrawer(GravityCompat.START)
        }

        fun close(){
            drawer?.close()
        }
    }


}