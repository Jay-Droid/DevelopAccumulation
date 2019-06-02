package com.jay.develop.android.broadcastReceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.develop.R;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private DynamicBroadcastReceiver dynamicBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
    }

    // 选择在Activity生命周期方法中的onResume()中注册
    @Override
    protected void onResume() {
        super.onResume();
        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
        dynamicBroadcastReceiver = new DynamicBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        // 3. 动态注册：调用Context的registerReceiver（）方法
        registerReceiver(dynamicBroadcastReceiver, intentFilter);
    }

    // 注册广播后，要在相应位置记得销毁广播
    // 即在onPause() 中unregisterReceiver(dynamicBroadcastReceiver)
    // 当此Activity实例化时，会动态将MyBroadcastReceiver注册到系统中
    // 当此Activity销毁时，动态注册的MyBroadcastReceiver将不再接收到相应的广播。
    @Override
    protected void onPause() {
        super.onPause();
        //销毁在onResume()方法中的广播
        unregisterReceiver(dynamicBroadcastReceiver);
    }
}
