package com.jay.develop.android.view.touchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import android.widget.FrameLayout;

public class EventViewGroup extends FrameLayout {
    private static final String TAG = "EventActivity";
    boolean inter;
    boolean childHanded;
    boolean handed;

    public EventViewGroup(Context context) {
        super(context);
    }

    public EventViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "=EventViewGroup======dispatchTouchEvent==" + ev.getAction());
        if (!inter) {
            inter = onInterceptTouchEvent(ev);
        }
        if (inter) {
            handed = onTouchEvent(ev);
        } else {
            childHanded = getChildAt(0).dispatchTouchEvent(ev);
            if (!childHanded) {
                handed = onTouchEvent(ev);
            }
        }
        return handed || childHanded;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "=EventViewGroup======onInterceptTouchEvent==" + ev.getAction());
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "=EventViewGroup======onTouchEvent==" + event.getAction());
        return true;
    }
}
