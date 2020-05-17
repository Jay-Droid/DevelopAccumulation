package com.jay.java.Android相关.event.listener;

import com.jay.java.Android相关.event.MotionEvent;
import com.jay.java.Android相关.event.View;

/**
 * Author：Jay On 2019/5/12 15:17
 * <p>
 * Description: 模拟触摸监听的回调接口
 */
public interface OnTouchListener {
    boolean onTouch(View view, MotionEvent event);
}
