package com.jay.develop.android.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jay.develop.R
import kotlinx.android.synthetic.main.activity_qrcode.*

class QRCodeActivity : AppCompatActivity() {
    private val ZXING_CAMERA_PERMISSION = 1
    private var mClss: Class<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode)
        tv_gain_qrc.setOnClickListener {
            startActivity(Intent(this@QRCodeActivity, QRCodeGainActivity::class.java))
        }
        tv_scan_qrc.setOnClickListener {
            launchActivity(QRCodeScanActivity::class.java)
        }
    }

    private fun launchActivity(clss: Class<*>) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !== PackageManager.PERMISSION_GRANTED) {
            mClss = clss
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), ZXING_CAMERA_PERMISSION
            )
        } else {
            val intent = Intent(this, clss)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            ZXING_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (mClss != null) {
                        val intent = Intent(this, mClss)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT)
                        .show()
                }
                return
            }
        }
    }

}
