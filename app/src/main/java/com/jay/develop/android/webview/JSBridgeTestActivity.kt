package com.jay.develop.android.webview


import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast

import com.github.lzyzsd.jsbridge.BridgeWebView
import com.google.gson.Gson
import com.jay.develop.R

class JSBridgeTestActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "MainActivity"

    private lateinit var webView: BridgeWebView

    private lateinit var button: Button

    private var RESULT_CODE = 0

    internal var mUploadMessage: ValueCallback<Uri>? = null

    internal var mUploadMessageArray: ValueCallback<Array<Uri>>? = null

    internal class Location {
        var address: String? = null
    }

    internal class User {
        var name: String? = null
        var location: Location? = null
        var testStr: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jsbridge_test)

        webView = findViewById<View>(R.id.webView) as BridgeWebView

        button = findViewById<View>(R.id.button) as Button

        button.setOnClickListener(this)

        webView.setDefaultHandler { data, function ->
            Toast.makeText(
                this@JSBridgeTestActivity,
                "我是JS端的数据：$data",
                Toast.LENGTH_LONG
            ).show()
            function?.onCallBack("我是来自Java端的默认回调")
        }

        webView.webChromeClient = object : WebChromeClient() {

            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams): Boolean {
                mUploadMessageArray = filePathCallback
                pickFile()
                return true
            }
        }

        webView.loadUrl("file:///android_asset/js_bridge_demo.html")

        //js 调用 java
        webView.registerHandler("submitFromWeb") { data, function ->
            Toast.makeText(
                this@JSBridgeTestActivity,
                "我是JS端的数据：$data",
                Toast.LENGTH_LONG
            ).show()
            function.onCallBack("我是来自Java端的回调-submitFromWeb")
        }

        val user = User()
        val location = Location()
        location.address = "SDU"
        user.location = location
        user.name = "大头鬼"

        webView.callHandler("functionInJs", Gson().toJson(user)) { }

        webView.send("hello")

    }

    fun pickFile() {
        val chooserIntent = Intent(Intent.ACTION_GET_CONTENT)
        chooserIntent.type = "image/*"
        startActivityForResult(chooserIntent, RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == RESULT_CODE) {
            if (null == mUploadMessage && null == mUploadMessageArray) {
                return
            }
            if (null != mUploadMessage && null == mUploadMessageArray) {
                val result = if (intent == null || resultCode != Activity.RESULT_OK) null else intent.data
                mUploadMessage!!.onReceiveValue(result)
                mUploadMessage = null
            }

            if (null == mUploadMessage && null != mUploadMessageArray) {
                val result = if (intent == null || resultCode != Activity.RESULT_OK) null else intent.data
                mUploadMessageArray!!.onReceiveValue(result?.let { arrayOf<Uri>(it) })
                mUploadMessageArray = null
            }

        }
    }

    override fun onClick(v: View) {
        //java 调用JS
        webView.callHandler("functionInJs", "我是要发给JS端的java端的数据") { data ->
            Toast.makeText(this@JSBridgeTestActivity, "这是来自JS端的数据 $data", Toast.LENGTH_SHORT).show()
        }
    }
}
