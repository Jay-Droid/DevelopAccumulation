package com.jay.develop.android.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.develop.R;

public class ServiceActivity extends AppCompatActivity {
    private int intentServiceCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initStartService();
        initBindService();
        initIntentService();
        initForegroundService();
    }

    private void initForegroundService() {
        Button btnPlay = findViewById(R.id.btn_play_music);
        Button btnPauth = findViewById(R.id.btn_pause_music);
        Button btnStop = findViewById(R.id.btn_stop_music);
        final Intent intent = new Intent(this, ForegroundService.class);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ForegroundService.TAG, "onClick: play");
                Bundle bundle = new Bundle();
                bundle.putSerializable("Key", ForegroundService.Control.PLAY);
                intent.putExtras(bundle);
                startService(intent);
            }
        });
        btnPauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ForegroundService.TAG, "onClick: pauth");
                Bundle bundle = new Bundle();
                bundle.putSerializable("Key", ForegroundService.Control.PAUSE);
                intent.putExtras(bundle);
                startService(intent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(ForegroundService.TAG, "onClick: stop");
                Bundle bundle = new Bundle();
                bundle.putSerializable("Key", ForegroundService.Control.STOP);
                intent.putExtras(bundle);
                startService(intent);
                //或者是直接如下调用
                //Intent intent = new Intent(this, MyService.class);
                //stopService(intent);
            }
        });
    }

    private void initIntentService() {
        Button btnStartIntent = findViewById(R.id.btn_start_intent);
        Button btnStopIntent = findViewById(R.id.btn_stop_intent);
        final Intent intent = new Intent(this, IntentTestService.class);

        btnStartIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(IntentTestService.TAG, "onClick: 开启IntentService");
                Bundle bundle = new Bundle();
                bundle.putString("key", "当前值：" + intentServiceCount++);
                intent.putExtras(bundle);
                startService(intent);
            }
        });
        btnStopIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(IntentTestService.TAG, "onClick: 停止IntentService");
                stopService(intent);
            }
        });
    }

    private void initStartService() {
        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        final Intent intent = new Intent(this, StartService.class);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);

            }
        });
    }

    /**
     * ServiceConnection 代表与服务的链接，它只有两个方法
     * onServiceConnected 在链接服务成功是被调用
     * onServiceDisConnected 在服务崩溃或者被杀死导致的链接中断时被调用
     */
    private ServiceConnection connection;
    private BindService bindService;

    private void initBindService() {
        Button btnBind = findViewById(R.id.btn_bind);
        Button btnUnbind = findViewById(R.id.btn_unbind);
        Button btnGet = findViewById(R.id.btn_get);
        //创建绑定对象
        final Intent intent = new Intent(this, BindService.class);
        //开启绑定
        btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(BindService.TAG, "onClick: 绑定调用:bindService");
                bindService(intent, connection, Service.BIND_AUTO_CREATE);
            }
        });
        //解除绑定
        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(BindService.TAG, "onClick: 解除绑定调用:unbindService");
                if (bindService != null) {
                    bindService = null;
                    unbindService(connection);
                }
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(BindService.TAG, "onClick: 从Service获取数据");
                String serviceData = bindService == null ? "还没有绑定" : "count=" + String.valueOf(bindService.getCount());
                Toast.makeText(ServiceActivity.this, serviceData, Toast.LENGTH_SHORT).show();
            }
        });
        connection = new ServiceConnection() {
            /**
             * 与服务器端交互的接口方法，绑定服务的时候被回调，在这个方法获取绑定Service传递管来的IBinder对象，
             * 通过这个IBinder对象实现宿主和Service的交互
             *
             * @param name
             * @param service
             */
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(BindService.TAG, "绑定成功调用：onServiceConnected");
                //获取Binder
                if (service instanceof BindService.LocalBinder) {
                    BindService.LocalBinder binder = (BindService.LocalBinder) service;
                    bindService = binder.getService();
                }
            }

            /**
             * 当取消绑定的时候被调用，但正常情况下是不会被调用的，它的调用时机是当Service服务被意外销毁时，
             * 例如内存的资源不足是会调用这个方法
             * @param name
             */
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(BindService.TAG, "解除绑定调用：onServiceDisconnected");
                bindService = null;
            }
        };
    }

}
