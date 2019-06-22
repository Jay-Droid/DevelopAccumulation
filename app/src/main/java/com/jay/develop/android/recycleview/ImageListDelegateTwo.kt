package com.jay.develop.android.recycleview

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jay.develop.R
import com.jay.develop.android.recycleview.xadapter.base.ItemViewDelegate
import com.jay.develop.android.recycleview.xadapter.base.ViewHolder

/**

 * Author：Jay On 2019/6/9 00:04

 * Description:

 */
class ImageListDelegateTwo(private val activity: Activity) : ItemViewDelegate<String> {
    override fun getItemViewLayoutId(): Int {
        return R.layout.item_image_double
    }

    override fun isForViewType(item: String?, position: Int): Boolean {
        return position % 2 != 0
    }

    override fun convert(holder: ViewHolder, item: String?, position: Int) {
        val ivState = holder.getView<ImageView>(R.id.iv_image)
        val ivState2 = holder.getView<ImageView>(R.id.iv_image_2)
        Glide.with(activity).load(item).into(ivState)
        Glide.with(activity).load(item).into(ivState2)
    }
}