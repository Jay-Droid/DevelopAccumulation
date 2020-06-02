package com.jay.develop.android.webview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.develop.R
import com.jay.develop.android.activity.lifecycle.LifecycleActivity
import com.jay.develop.android.broadcastReceiver.BroadcastReceiverActivity
import com.jay.develop.android.fragment.FragmentTestActivity
import com.jay.develop.android.handler.HandlerActivity
import com.jay.develop.android.recycleview.XAdapterActivity
import com.jay.develop.android.service.ServiceActivity
import com.jay.develop.android.view.ViewActivity
import com.jay.develop.main.DemoListAdapter
import kotlinx.android.synthetic.main.activity_web_view.*
import java.util.ArrayList

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = getLinearLayoutManager()
        recyclerview.adapter = getDemoAdapter()
    }

    private fun getDemoData(): List<DemoListAdapter.DemoItem> {
        val demoList = ArrayList<DemoListAdapter.DemoItem>()
        demoList.add(
            DemoListAdapter.DemoItem(
                "WebView的一般使用", "常用api，一些使用技巧",
                WebViewNormalUseActivity::class.java
            )
        )
        demoList.add(
            DemoListAdapter.DemoItem(
                "WebViewJs与Java交互", "JS调用Java代码，Java调用JS代码",
                WebViewOfJSAndJavaActivity::class.java
            )
        )
        return demoList
    }


    private fun getDemoAdapter(): DemoListAdapter {
        return DemoListAdapter(getDemoData(),
            DemoListAdapter.OnItemClickListener { item ->
                startActivity(
                    Intent(this, item.activity)
                )
            })
    }

    private fun getLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(this)
    }
}
