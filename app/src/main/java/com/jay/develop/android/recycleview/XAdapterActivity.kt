package com.jay.develop.android.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.jay.develop.R
import kotlinx.android.synthetic.main.activity_xadapter.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.develop.android.recycleview.xadapter.MultiItemTypeAdapter
import com.jay.develop.android.recycleview.xadapter.wrapper.EmptyWrapper
import com.jay.develop.android.recycleview.xadapter.wrapper.HeaderAndFooterWrapper
import com.jay.develop.other.ImagePathLists

class XAdapterActivity : AppCompatActivity() {
    private var imageList: MutableList<String> = ImagePathLists.getImageUrlsByList()

//    private var imageList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xadapter)
        initView()
    }

    private fun initView() {
//        imageList = imageList.subList(0, 10)
        rv_image.setHasFixedSize(true)
        rv_image.layoutManager = getLinearLayoutManager()

//        setUpSingleTypeAdapter()
        setUpMultiTypeAdapter()
    }

    private fun setUpMultiTypeAdapter() {
        val adapter = MultiItemTypeAdapter(this, imageList)
        adapter.addItemViewDelegate(ImageListDelegateOne(this))
        adapter.addItemViewDelegate(ImageListDelegateTwo(this))
        rv_image.adapter = adapter
    }

    private fun setUpSingleTypeAdapter() {
        val adapter = getImageListAdapter()
        val emptyWrapper: EmptyWrapper = setUpEmptyWrapper(adapter)
        rv_image.adapter = emptyWrapper
        val headerAndFooterWrapper = setupHeaderAndFooterWrapper(adapter)
        rv_image.adapter = headerAndFooterWrapper
    }

    private fun setupHeaderAndFooterWrapper(adapter: ImageListAdapter): HeaderAndFooterWrapper<Any> {
        val headerAndFooterWrapper = HeaderAndFooterWrapper<Any>(adapter)
        val headerView = LayoutInflater.from(this).inflate(
            R.layout.layout_header, null, false
        )

        val footerView = LayoutInflater.from(this).inflate(
            R.layout.layout_header, null, false
        )
        headerAndFooterWrapper.addHeaderView(headerView)
        headerAndFooterWrapper.addFootView(footerView)
        return headerAndFooterWrapper
    }

    private fun setUpEmptyWrapper(adapter: ImageListAdapter): EmptyWrapper {
        val emptyWrapper = EmptyWrapper(adapter)
        val emptyView = LayoutInflater.from(this).inflate(R.layout.layout_empty, null, false)
        emptyWrapper.setEmptyView(emptyView)
        return emptyWrapper
    }

    private fun getImageListAdapter(): ImageListAdapter {
        return ImageListAdapter(this, R.layout.item_image, imageList)
    }

    private fun getLinearLayoutManager(): LinearLayoutManager {
        return LinearLayoutManager(this)
    }
}
