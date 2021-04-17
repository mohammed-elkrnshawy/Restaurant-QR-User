package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elkrnshawy.restaurant_qr_user.R

class RestaurantDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = RestaurantDetailsFragment()
    }

    private lateinit var viewModel: RestaurantDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.restaurant_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestaurantDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}