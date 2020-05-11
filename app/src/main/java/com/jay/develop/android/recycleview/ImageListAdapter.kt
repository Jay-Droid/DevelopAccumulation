package com.jay.develop.android.recycleview

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jay.develop.R
import com.jay.develop.android.recycleview.xadapter.BaseCommonAdapter
import com.jay.develop.android.recycleview.xadapter.base.ViewHolder

/**

 * Author：Jay On 2019/6/8 20:59

 * Description:

 */
class ImageListAdapter : BaseCommonAdapter<String> {

    constructor(context: Context, layoutId: Int, datas: List<String>) : super(context, layoutId, datas)

    override fun convert(holder: ViewHolder, m: String, position: Int) {
        val ivState = holder.getView<ImageView>(R.id.iv_image)
        Glide.with(mContext).load(m).into(ivState)
    }
}