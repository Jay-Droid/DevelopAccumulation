package com.jay.java.Android相关.event;

import com.jay.java.Android相关.event.listener.OnClickListener;
import com.jay.java.Android相关.event.listener.OnTouchListener;

/**
 * Author：Jay On 2019/5/12 22:32
 * <p>
 * Description: 模拟Android的Activity类
 */
public class Activity {
    
    public static void main(String[] args) {
        //创建一个ViewGroup
        ViewGroup viewGroup = new ViewGroup(0, 0, 1080, 1920);
        viewGroup.setName("顶层ViewGroup");
        //设置点击事件
        viewGroup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("点击了-顶层ViewGroup-onClick");
            }
        });
        viewGroup.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("点击了-顶层ViewGroup-onTouch");
                return false;
            }
        });
        View view = new View(0, 0, 200, 200);
        viewGroup.addView(view);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("点击了-View-onClick");
            }
        });
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("点击了-View-onTouch");
                return false;
            }
        });
        //产生一个DOWN事件
        MotionEvent motionEvent = new MotionEvent(100, 100);
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);
        //ViewGroup分发这个事件
        viewGroup.dispatchTouchEvent(motionEvent);

    }
}
