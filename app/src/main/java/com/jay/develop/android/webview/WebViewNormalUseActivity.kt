package com.jay.develop.android.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jay.develop.R
import kotlinx.android.synthetic.main.activity_web_view_nomal_use.*
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.net.http.SslError
import android.os.Build
import android.webkit.*
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import android.widget.EditText
import android.webkit.WebView
import android.widget.LinearLayout
import android.view.ViewGroup


class WebViewNormalUseActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_nomal_use)
        initWebView()
    }

    private fun initWebView() {
        getWebView()
        setWebView()
        webView.loadUrl("https://www.baidu.com/")
        //WebViewClient类
        //作用：处理各种通知 & 请求事件
        webView.webViewClient = object : WebViewClient() {
            //打开网页时不调用系统浏览器， 而是在本WebView中显示；在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
            //复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.e("web", "shouldOverrideUrlLoading-->url=$url")
                view.loadUrl(url)
                return true
            }

            //作用：开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
//            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
//                super.onPageStarted(view, url, favicon)
//                //设定加载开始的操作
//                Log.e("web", "onPageStarted-->url=$url")
//            }

            //作用：在页面加载结束时调用。我们可以关闭loading 条，切换程序动作。
            override fun onPageFinished(view: WebView, url: String) {
                //设定加载结束的操作
                Log.e("web", "onPageFinished-->url=$url")
            }

            //作用：在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
            override fun onLoadResource(view: WebView, url: String) {
                //设定加载资源的操作
                Log.e("web", "onLoadResource-->url=$url")
            }

            //作用：加载页面的服务器出现错误时（如404）调用。
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Log.e("web", "onReceivedError-->failingUrl=$failingUrl")
                when (errorCode) {
                    ERROR_FILE_NOT_FOUND -> view.loadUrl("file:///android_assets/error_handle.html")
                }
            }

            // 作用：处理https请求 webView默认是不处理https请求的，页面显示空白，需要进行如下设置：
            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                Log.e("web", "onReceivedError-->error=$error")
                handler.proceed()    //表示等待证书响应
                // handler.cancel();      //表示挂起连接，为默认方式
                // handler.handleMessage(null);    //可做其他处理
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

            //作用：获取Web页中的标题
            override fun onReceivedTitle(view: WebView?, title: String?) {
                Log.e("web", "onReceivedTitle-->title=$title")
            }

            //作用：支持javascript的警告框
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                Log.e("web", "onJsAlert-->url=$url")
                AlertDialog.Builder(this@WebViewNormalUseActivity)
                    .setTitle("JsAlert")
                    .setMessage(message)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> result?.confirm() })
                    .setCancelable(false)
                    .show()
                return true
            }

            //作用：支持javascript的确认框
            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                AlertDialog.Builder(this@WebViewNormalUseActivity)
                    .setTitle("JsConfirm")
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog, which -> result?.confirm() }
                    .setNegativeButton("Cancel") { dialog, which -> result?.cancel() }
                    .setCancelable(false)
                    .show()
                return true
            }

            //作用：支持javascript输入框 点击确认返回输入框中的值，点击取消返回 null。
            override fun onJsPrompt(
                view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?
            ): Boolean {
                val et = EditText(this@WebViewNormalUseActivity)
                et.setText(defaultValue)
                AlertDialog.Builder(this@WebViewNormalUseActivity)
                    .setTitle(message)
                    .setView(et)
                    .setPositiveButton("OK") { _, _ ->
                        result?.confirm(et.text.toString())
                    }
                    .setNegativeButton("Cancel") { dialog, which ->
                        result?.cancel()
                    }
                    .setCancelable(false)
                    .show()
                return true
            }
        }
    }

    private fun getWebView() {
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        webView = WebView(applicationContext)
        webView.layoutParams = params
        webViewContainer.addView(webView)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        //声明WebSettings子类
        val webSettings = webView.settings
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        webSettings.javaScriptEnabled = true
        //支持插件 api18 已废弃
        webSettings.pluginState = WebSettings.PluginState.ON
        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(false) //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = false //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false //隐藏原生的缩放控件
        //其他细节操作
        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8"//设置编码格式
        //缓存操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK // //优先使用缓存:
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE  //不使用缓存:
        // 特别注意：5.1以上默认禁止了https和http混用，以下方式是开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }
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
