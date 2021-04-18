package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.menuPackae.subcategoryPackage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elkrnshawy.restaurant_qr_user.R

class SubcategoryFragment : Fragment() {

    companion object {
        fun newInstance() = SubcategoryFragment()
    }

    private lateinit var viewModel: SubcategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.subcategory_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubcategoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}