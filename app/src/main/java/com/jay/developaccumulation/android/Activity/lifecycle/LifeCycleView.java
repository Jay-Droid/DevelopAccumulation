package com.jay.developaccumulation.android.Activity.lifecycle;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Created by Jay on 2019/3/17.
 */
public class LifeCycleView extends AppCompatTextView implements LifecycleObserver {

    private String TAG = "LifecycleActivity";

    private boolean lifeCycleEnable = true;

    public boolean isLifeCycleEnable() {
        return lifeCycleEnable;
    }

    public void setLifeCycleEnable(boolean lifeCycleEnable) {
        this.lifeCycleEnable = lifeCycleEnable;
    }

    public LifeCycleView(Context context) {
        super(context);

    }

    public LifeCycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public LifeCycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    // ****************** lifeCycle ******************

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        if (lifeCycleEnable) {
            String text = System.currentTimeMillis() + "-creat\n";
            Log.e(TAG, text);
            this.append(text);
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        if (lifeCycleEnable) {
            String text = System.currentTimeMillis() + "-start\n";
            Log.e(TAG, text);
            this.append(text);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        if (lifeCycleEnable) {
            String text = System.currentTimeMillis() + "-resume\n";
            Log.e(TAG, text);
            this.append(text);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause() {
        if (lifeCycleEnable) {
            String text = System.currentTimeMillis() + "-pause\n";
            Log.e(TAG, text);
            this.append(text);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop() {
        if (lifeCycleEnable) {
            String text = System.currentTimeMillis() + "-stop\n";
            Log.e(TAG, text);
            this.append(text);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory() {
        if (lifeCycleEnable) {
            String text = System.currentTimeMillis() + "-destory\n";
            Log.e(TAG, text);
            this.append(text);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void any() {
        if (lifeCycleEnable) {
            String text = System.currentTimeMillis() + "-any\n";
            Log.e(TAG, text);
            this.append(text);
        }
    }

}