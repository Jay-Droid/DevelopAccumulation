package com.jay.develop.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jay.develop.R
import com.jay.develop.android.activity.lifecycle.LifecycleActivity
import com.jay.develop.android.broadcastReceiver.BroadcastReceiverActivity
import com.jay.develop.android.camera.QRCodeActivity
import com.jay.develop.android.fragment.FragmentTestActivity
import com.jay.develop.android.handler.HandlerActivity
import com.jay.develop.android.recycleview.XAdapterActivity
import com.jay.develop.android.service.ServiceActivity
import com.jay.develop.android.view.ViewActivity
import com.jay.develop.android.webview.WebViewActivity
import com.jay.develop.demo.baiduface.BaiduFaceActivity
import com.jay.develop.main.DemoListAdapter
import com.jay.develop.main.DemoListAdapter.DemoItem
import java.util.*

class AndroidListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {

        val recyclerview =
            findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = demoAdapter
    }

    private val demoData: List<DemoItem>
        private get() {
            val demoList: MutableList<DemoItem> = ArrayList()
            demoList.add(
                DemoItem(
                    "Activity",
                    "Activity生命周期相关",
                    LifecycleActivity::class.java
                )
            )
            demoList.add(
                DemoItem("Fragment", "Fragment相关", FragmentTestActivity::class.java)
            )
            demoList.add(DemoItem("Service", "Service相关", ServiceActivity::class.java))
            demoList.add(
                DemoItem(
                    "BroadcastReceiver",
                    "BroadcastReceiver相关",
                    BroadcastReceiverActivity::class.java
                )
            )
            demoList.add(DemoItem("Handler", "Handler相关", HandlerActivity::class.java))
            demoList.add(DemoItem("View", "View相关", ViewActivity::class.java))
            demoList.add(
                DemoItem("RecyclerView", "RecyclerView相关", XAdapterActivity::class.java)
            )
            demoList.add(
                DemoItem("WebViewActivity", "WebViewActivity", WebViewActivity::class.java)
            )
            demoList.add(DemoItem("QRCodeActivity", "相机相关", QRCodeActivity::class.java))
            demoList.add(
                DemoItem("BaiduFaceActivity", "百度人脸识别", BaiduFaceActivity::class.java)
            )
            return demoList
        }

    private val demoAdapter: DemoListAdapter
        private get() = DemoListAdapter(
            demoData,
            DemoListAdapter.OnItemClickListener { item ->
                startActivity(
                    Intent(
                        this@AndroidListActivity,
                        item.activity
                    )
                )
            })

    private val linearLayoutManager: LinearLayoutManager
        private get() = LinearLayoutManager(this)
}