package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.scanPackage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.models.UserData
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.sharedActivity.HomeActivity
import com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.setupHelper.ParentFragment
import com.google.zxing.integration.android.IntentIntegrator
import org.json.JSONException
import org.json.JSONObject

class ScanFragment : ParentFragment() {
    private var qrScanIntegrator: IntentIntegrator? = null
    private lateinit var viewModel: ScanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.scan_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponents(view)
        performAction()
    }

    override fun setupComponents(view: View?) {
        super.setupComponents(view)

        qrScanIntegrator = IntentIntegrator.forSupportFragment(this)
        qrScanIntegrator?.setOrientationLocked(false)

        // Different Customization option...
        qrScanIntegrator?.setCameraId(0)  // Use a specific camera of the device
        qrScanIntegrator?.setBeepEnabled(true)
        qrScanIntegrator?.setPrompt(" ")
        qrScanIntegrator?.setBarcodeImageEnabled(true)
    }

    private fun performAction() {
        qrScanIntegrator?.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            // If QRCode has no data.
            if (result.contents == null) {
                Toast.makeText(activity, R.string.app_name, Toast.LENGTH_LONG).show()
            } else {
                // If QRCode contains data.
                setupSettings()
                Toast.makeText(activity, result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}