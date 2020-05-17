package com.jay.develop

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.jay.develop.demo.deeplink.LinkHelper
import com.jay.develop.demo.push.UMPushHelper
import com.jay.develop.java.reflection.HockHelper


/**
 * @author wangxuejie
 * @version 1.0
 * @date 2019-10-25 19:23
 */
class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //Activity的启动监控
        HockHelper.hookHandler(base)
        //Activity的创建的监控
        HockHelper.hookInstrumentation()
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        val umPushHelper = UMPushHelper()
        val linkHelper = LinkHelper()
        umPushHelper.init(this)
        linkHelper.init(this)

    }


    companion object {
        private val TAG = App::class.java.simpleName

        /**
         * 获取应用类实例
         *
         * @return BossApplicationLike
         */
        var instance: App? = null
            private set
    }

}
