package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.waitingListPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elkrnshawy.restaurant_qr_user.R

class WaitingListFragment : Fragment() {

    companion object {
        fun newInstance() = WaitingListFragment()
    }

    private lateinit var viewModel: WaitingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.waiting_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WaitingListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}