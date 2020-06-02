package com.jay.develop.android.camera

import android.os.Handler
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.zxing.Result
import com.jay.develop.R
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QRCodeScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    private var tvInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_scan)
        val contentFrame = findViewById<View>(R.id.content_frame) as ViewGroup
        tvInfo = findViewById(R.id.tv_info)
        mScannerView = ZXingScannerView(this)
        contentFrame.addView(mScannerView)



    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }

    override fun handleResult(rawResult: Result) {
        tvInfo!!.text = "info:$rawResult"
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        val handler = Handler()
        handler.postDelayed({ mScannerView!!.resumeCameraPreview(this@QRCodeScanActivity) }, 2000)
    }
}
