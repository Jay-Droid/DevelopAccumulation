package com.jay.develop.android.webview

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

/**
 * Author：Jay On 2019/6/30 14:53
 *
 *
 * Description:
 */
class AndroidPlatform {
    private var context: Context? = null

    constructor(context: Context) {
        this.context = context.applicationContext
    }

    @JavascriptInterface
    fun toast(message: String): String {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        return "我是Android端的toast方法：js已经调用了完了Android的方法"
    }
}
