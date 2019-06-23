package com.jay.develop.android.recycleview.xadapter.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 设置间距
 *
 * @author zhanghao
 * @date 2017-09-21 下午3:14
 */
public class SpacePackNoItemDecoration extends RecyclerView.ItemDecoration
{

    private int space;

    public SpacePackNoItemDecoration (int space)
    {

        this.space = space;
    }

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        //竖直方向的
        if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL)
        {
            //最后一项需要 bottom
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1)
            {
                outRect.bottom = 0;
            }
            else
            {
                outRect.bottom = space;
            }
            outRect.top = 0;
            outRect.left = 0;
            outRect.right = 0;
        }
        else
        {
            //最后一项需要right
            if (parent.getChildAdapterPosition(view) == layoutManager.getItemCount() - 1)
            {
                outRect.right = 0;
            }
            else
            {
                outRect.right = space;
            }
            outRect.top = 0;
            outRect.left = 0;
            outRect.bottom = 0;
        }
    }
}
