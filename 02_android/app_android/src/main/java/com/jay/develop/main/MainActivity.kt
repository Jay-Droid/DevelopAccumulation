package com.jay.develop.main

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jay.develop.R
import com.jay.develop.android.AndroidListActivity
import com.jay.develop.android.edittext.InputFilterUtils
import com.jay.develop.java.JavaListActivity
import com.jay.develop.main.DemoListAdapter.DemoItem
import java.util.*

/**
 * @author Jay
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }


    private fun initView() {
        val recyclerview =
                findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.setHasFixedSize(true)
        recyclerview.layoutManager = linearLayoutManager
        recyclerview.adapter = demoAdapter
    }


    private fun initData() {
        val en =
                findViewById<EditText>(R.id.et_en)
        val zh =
                findViewById<EditText>(R.id.et_zh)
        zh.filters = InputFilterUtils.getChineseFilter()
        en.filters = InputFilterUtils.getEnglishFilter()

        var sp = SpannableString("`")
        Log.d("Jay", "sp:${sp.toString()}")
    }

    private val demoData: List<DemoItem>
        private get() {
            val demoList: MutableList<DemoItem> = ArrayList()
            demoList.add(
                    DemoItem(
                            "Android", "Android整理",
                            AndroidListActivity::class.java
                    )
            )
            demoList.add(
                    DemoItem(
                            "Java", "Java整理",
                            JavaListActivity::class.java
                    )
            )
            return demoList
        }

    private val demoAdapter: DemoListAdapter
        private get() = DemoListAdapter(
                demoData,
                DemoListAdapter.OnItemClickListener { item ->
                    startActivity(
                            Intent(
                                    this@MainActivity,
                                    item.activity
                            )
                    )
                })

    private val linearLayoutManager: LinearLayoutManager
        private get() = LinearLayoutManager(this)
}