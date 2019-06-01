package com.jay.develop.android.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;

public class IntentTestService extends IntentService {

    public static final String TAG = "IntentTestService";

    public IntentTestService() {
        super("IntentTestService");
        Log.d(TAG, "IntentTestService: 构造方法");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "onHandleIntent: current thread name=" + Thread.currentThread().getName());
                Log.d(TAG, "onHandleIntent: count=" + bundle.getString("key", "默认值"));
            }
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
