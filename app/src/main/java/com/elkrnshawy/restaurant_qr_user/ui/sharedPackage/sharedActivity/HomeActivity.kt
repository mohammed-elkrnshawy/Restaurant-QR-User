package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers.NavControllerHelper
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import com.infideap.drawerbehavior.AdvanceDrawerLayout

class HomeActivity : AppCompatActivity() {
    private var txtProfile: TextView? =null
    private var txtReservation: TextView? =null

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
    }

    private fun onClickComponents(){

        txtProfile?.setOnClickListener {
            NavControllerHelper.getNavController()?.navigate(R.id.action_global_profileFragment)
            drawer?.close()
        }

        txtReservation?.setOnClickListener {
            NavControllerHelper.getNavController()?.navigate(R.id.action_global_reservationFragment)
            drawer?.close()
        }
    }

    companion object {
        var drawer: AdvanceDrawerLayout? = null

        fun open(){
            drawer?.openDrawer(GravityCompat.START)
        }
    }
}