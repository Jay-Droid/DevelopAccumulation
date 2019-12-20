package com.jay.develop.android.webview


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.lzyzsd.jsbridge.BridgeWebView
import com.jay.develop.R
import kotlinx.android.synthetic.main.activity_jsbridge_test.*


class JSBridgeTestActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"

    private lateinit var webView: BridgeWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jsbridge_test)

        btn_send.setOnClickListener(this)
        btn_callHandler.setOnClickListener(this)

        //初始化 BridgeWebView
        initJSWebView()

        //js 调用 java
        initJSCallJava()


    }

    private fun initJSWebView() {
        webView = findViewById<View>(R.id.webView) as BridgeWebView

        val url = "file:///android_asset/js_bridge_demo.html"
        webView.loadUrl(url)
    }


    //JS 调用 java
    private fun initJSCallJava() {
        //js 调用 functionInJava
        webView.registerHandler("functionInJava") { data, function ->

            info.text = "我是JS端的数据：registerHandler()---$data"
            function.onCallBack("我是来自Java端的回调-functionInJava（）")
        }

        //js端通过send方法
        webView.setDefaultHandler { data, function ->

            info.text = "我是JS端的数据：setDefaultHandler()---$data"
            function?.onCallBack("我是来自Java端的默认回调")
        }
    }


    //java 调用 JS
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_send -> {
                //发送信息给js,此处不需要配置handlerName
                webView.send("通过webView.send(）方法发给JS一条消息") { data ->

                    info.text = "我是JS端的数据：send()--$data"
                }

            }

            R.id.btn_callHandler -> {

                webView.callHandler("functionInJs", "callHandler（），调用functionInJs") {

                    info.text = "我是JS端的回调数据：callHandler()--$it"
                }
            }
        }


    }
}
