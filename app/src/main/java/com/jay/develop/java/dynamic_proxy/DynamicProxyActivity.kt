package com.jay.develop.java.dynamic_proxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jay.develop.R
import kotlinx.android.synthetic.main.activity_dynamic_proxy.*

/**
 * 动态代理组件化路由框架实战
 * URI，Scheme，注解，动态代理
 * 通过该方法打开该页面
 * XLRouter.routerUri().jumpToDynamicProxyPage("我是通过XLRouter路由框架跳转的", "我是描述");
 */
class DynamicProxyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_proxy)
        getDataFromBrowser()
    }

    /**
     * 从deep link中获取数据
     */
    private fun getDataFromBrowser() {
        val data = intent.data
        try {
            tv_info.text =
                "Uri  :" + data!!.toString() + "\n" +
                        "Scheme: " + data!!.scheme + "\n" +
                        "host: " + data!!.host + "\n" +
                        "path: " + data!!.path
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

