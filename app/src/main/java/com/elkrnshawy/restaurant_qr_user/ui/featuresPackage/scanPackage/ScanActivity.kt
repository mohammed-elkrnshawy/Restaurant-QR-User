package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.scanPackage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elkrnshawy.restaurant_qr_user.R
import com.google.zxing.integration.android.IntentIntegrator


class ScanActivity : AppCompatActivity() {
    private var qrScanIntegrator: IntentIntegrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        setupComponents()
        performAction()
    }

    fun setupComponents() {
        qrScanIntegrator = IntentIntegrator(this)
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

            } else {
                // If QRCode contains data.
               /* setupSettings()*/
                val returnIntent = Intent()
                returnIntent.putExtra("result", result.contents)
                setResult(RESULT_OK, returnIntent)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
        finish()
    }
}