package com.jay.develop.android.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jay.develop.R
import kotlinx.android.synthetic.main.activity_web_view_of_jsand_java.*
import android.app.AlertDialog
import android.content.Intent
import android.webkit.*
import android.net.Uri
import android.widget.Toast
import kotlin.collections.HashMap as HashMap1


class WebViewOfJSAndJavaActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_of_jsand_java)
        getWebView()
        initWebView()
    }

    fun jSBridgeDemo(view: View) {
        startActivity(Intent(this, JSBridgeTestActivity::class.java))
    }
    /**
     * 对于Android调用JS代码的方法有2种：
     * 1,通过WebView的loadUrl（）
     * 2,通过WebView的evaluateJavascript（）
     */
    /**
     * 对于JS调用Android代码的方法有3种：
     * 1,通过WebView的addJavascriptInterface（）进行对象映射
     * 2,通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url3
     * ,通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调拦截JS对话框alert()、confirm()、prompt（） 消息
     */
    fun javaCallJs(view: View) {
        //第一种方式：通过WebView的loadUrl（）
        // 注意调用的JS方法名要对应上
        webView.loadUrl("javascript:callJS()")
        webView.loadUrl("javascript:callH5('我是Android端，我通过webView.loadUrl（）来调用JS的callH5（）方法了')")

        //第二种方式：通过WebView的evaluateJavascript（）
//        优点：该方法比第一种方法效率更高、使用更简洁。
//        因为该方法的执行不会使页面刷新，而第一种方法（loadUrl ）的执行则会。
//        Android 4.4 后才可使用
        webView.evaluateJavascript("javascript:callJS()") { value -> Log.e("web", "onReceiveValue-->value=$value") }

        webView.evaluateJavascript(
            "javascript:callH5('我是Android端，我通过webView.evaluateJavascript（）来调用JS的callH5（）方法了')"
        ) { value -> Log.e("web", "onReceiveValue-->value=$value") }

    }

    private fun initWebView() {
        setWebView()
        webView.loadUrl("file:///android_asset/javascript.html")
        //WebViewClient类
        //作用：处理各种通知 & 请求事件
        webView.webViewClient = object : WebViewClient() {
            //打开网页时不调用系统浏览器， 而是在本WebView中显示；在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
            //复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.e("web", "shouldOverrideUrlLoading-->url=$url")
                // 步骤2：根据协议的参数，判断是否是所需要的url
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                val uri = Uri.parse(url)
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if (uri.scheme.equals("js")) {
                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {
                        // 执行JS所需要调用的逻辑
                        Toast.makeText(
                            this@WebViewOfJSAndJavaActivity,
                            "我是JS端，我通过callAndroidByUrl()方法来调用Android端的方法了",
                            Toast.LENGTH_LONG
                        ).show()
                        //Android端执行完成，通知JS端
                        webView.loadUrl("javascript:callAndroidByUrlCallBack('我是Android端：js已经调用了完了Android的方法')")
                    }

                    return true
                }
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        //WebChromeClient类
        //作用：辅助 WebView 处理 Javascript 的对话框,网站图标,网站标题等等。
        webView.webChromeClient = object : WebChromeClient() {
            //作用：获得网页的加载进度并显示
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                //设定加载资源的操作
                Log.e("web", "onProgressChanged-->newProgress=$newProgress")
            }

            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                val b = AlertDialog.Builder(this@WebViewOfJSAndJavaActivity)
                b.setTitle("Alert")
                b.setMessage(message)
                b.setPositiveButton(android.R.string.ok) { dialog, which -> result.confirm() }
                b.setCancelable(false)
                b.create().show()
                return true
            }
        }
    }

    private fun getWebView() {
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        webView = WebView(applicationContext)
        webView.layoutParams = params
        webContainer.addView(webView)

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        //声明WebSettings子类
        val webSettings = webView.settings
        // 设置与Js交互的权限
        webSettings.javaScriptEnabled = true
        // 设置允许JS弹窗
        webSettings.javaScriptCanOpenWindowsAutomatically = true

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Java对象名
        //参数2：Javascript对象名
        webView.addJavascriptInterface(AndroidPlatform(this), "JSForAndroid")//AndroidPlatform类对象映射到js的JSForAndroid对象
    }

    override fun onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            webView.clearHistory()
            (webView.parent as ViewGroup).removeView(webView)
            webView.destroy()
        }
        super.onDestroy()
    }

}
