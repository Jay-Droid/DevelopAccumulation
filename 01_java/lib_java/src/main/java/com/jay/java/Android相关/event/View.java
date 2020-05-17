package com.jay.java.Android相关.event;

import com.jay.java.Android相关.event.listener.OnClickListener;
import com.jay.java.Android相关.event.listener.OnTouchListener;

/**
 * Author：Jay On 2019/5/12 15:14
 * <p>
 * Description: 模拟Android中俄View类
 * Android源码中的View类中的代码60%关于时间，20%关于测量，10%关于绘制
 */
public class View {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private OnClickListener onClickListener;
    private OnTouchListener onTouchListener;

    public View(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    /**
     * 判断边界值，判断用户触发是事件坐标是否属于该View的范围
     */
    protected boolean isContain(int x, int y) {
        if (x >= left && x < right && y >= top && y < bottom) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前View是否消费这个事件
     */
    protected boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("View-->dispatchTouchEvent");
        boolean result = false;
        if (onTouchListener != null && onTouchListener.onTouch(this, ev)) {
            result = true;
        }
        //如果onTouch没有返回true,才执行View的 OnTouchEvent 方法
        if (!result && OnTouchEvent(ev)) {
            result = true;
        }
        return result;
    }

    /**
     * 消费事件
     */
    private boolean OnTouchEvent(MotionEvent ev) {
        if (onClickListener != null) {
            onClickListener.onClick(this);
            return true;
        }
        return false;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }
}
