package com.jay.develop.android.recycleview.xadapter.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 设置间距
 *
 * @author zhanghao
 * @date 2017-09-21 下午3:14
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration
{

    private int space;

    public SpaceItemDecoration (int space)
    {

        this.space = space;
    }

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {

        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;

        // Add top margin only for the first item to avoid double space between items
        /*if (parent.getChildLayoutPosition(view) % 3 == 0)
        {
            outRect.top = space;
        }
        else
        {
            outRect.top = 0;
        }*/
    }
}
