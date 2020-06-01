package com.jay.develop.demo.deeplink

import android.app.Application
import android.util.Log
import com.jay.develop.BuildConfig
import com.microquation.linkedme.android.LinkedME


/**
 * Author：Jay On 2019/6/1 22:13
 * Description:
 */
class LinkHelper {

    companion object {
        val TAG: String = LinkHelper::class.java.simpleName
        const val LinkedME_Key = "226d721dc4f5949f36e08de85cd9eca8"
        const val LinkedME_SecretKey = "22fbddcbd3cfbada3407d3131d79c657"
    }

    fun init(demoApplication: Application) {
        Log.d(TAG, "初始化deeplink")
        initLinkedMe(demoApplication)
    }

    private fun initLinkedMe(demoApplication: Application) {
        // 初始化SDK，为了提高初始化效率，linkedme key不再在AndroidManifest.xml文件中配置
        LinkedME.getInstance(
            demoApplication,
            LinkedME_Key
        )

        if (BuildConfig.DEBUG) {
            //设置debug模式下打印LinkedME日志
            LinkedME.getInstance().setDebug()
        }
        //初始时请设置为false
        LinkedME.getInstance().setImmediate(true)
        //设置处理跳转逻辑的中转页，MiddleActivity详见后续配置
        //        LinkedME.getInstance().setHandleActivity(JavaListActivity::class.java.name)
    }


}
