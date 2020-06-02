package com.jay.develop.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {
    public static final String TAG = "BindService";
    int mStartMode;       // indicates how to behave if the service is killed
    IBinder mBinder;      // interface for clients that bind
    boolean mAllowRebind; // indicates whether onRebind should be used
    private boolean isQuit;
    private int count;

    public int getCount() {
        return count;
    }

    /**
     * 创建Binder对象，返回给客户端即Activity使用，提供数据交换的接口
     */
    public class LocalBinder extends Binder {
        // 声明一个方法，getService 提供给客户端调用
        BindService getService() {
            //返回当前对象BinderService,这样我们就可以在客户端调用Service的公共方法了
            return BindService.this;
        }
    }

    @Override
    public void onCreate() {
        // The service is being created
        Log.d(TAG, "onCreate: ");
        mBinder = new LocalBinder();
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                //每隔一秒 count+1，直到isQuit为true
                while (!isQuit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        };
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        Log.d(TAG, "onStartCommand: ");
        return mStartMode;
    }

    /**
     * 把Binder 类返回给客户端
     *
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        Log.d(TAG, "onUnbind: ");
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
        Log.d(TAG, "onRebind: ");
    }

    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        Log.d(TAG, "onDestroy: ");
    }
}
