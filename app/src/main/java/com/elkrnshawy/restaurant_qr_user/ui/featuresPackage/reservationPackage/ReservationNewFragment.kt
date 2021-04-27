package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elkrnshawy.restaurant_qr_user.R

class ReservationNewFragment : Fragment() {

    companion object {
        fun newInstance() = ReservationNewFragment()
    }

    private lateinit var viewModel: ReservationNewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reservation_new_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReservationNewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}