package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.helpers

import android.view.View
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class NavControllerHelper {
    companion object{

        private var navController: NavController? = null
        private var navigationView: BottomNavigationView? = null


        fun setupNavControl(nav: NavController) {
            navController = nav
        }

        fun getNavController(): NavController? {
            return navController
        }

    }




    fun setupBottomNavigation(navigation: BottomNavigationView) {
        navigationView = navigation
    }

    fun showBottom() {
        navigationView!!.visibility = View.VISIBLE
    }

    fun goneBottom() {
        navigationView!!.visibility = View.GONE
    }
}