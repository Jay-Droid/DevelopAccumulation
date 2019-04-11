package com.jay.developaccumulation.android.Handler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.developaccumulation.R;

import java.util.UUID;

public class HandlerActivity extends AppCompatActivity {
    private static final String TAG = "HandlerActivity";
    private TextView tvRunnableInfo;
    private TextView tvMessageInfo;
    private Handler runnableHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initOtherAsyncMessage();
        initHandlerWithRunnable();
        initHandlerWithMessage();
        initHandlerWithThread();
        initHandlerWithMyself();
    }

    private void initOtherAsyncMessage() {
        findViewById(R.id.btn_asynctask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HandlerActivity.this, AsyncTaskActivity.class));
            }
        });
        findViewById(R.id.btn_handlerthread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HandlerActivity.this, HandlerThreadActivity.class));
            }
        });
        findViewById(R.id.btn_intendservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HandlerActivity.this, IntentServiceActivity.class));
            }
        });
    }

    private void initHandlerWithMyself() {
        com.jay.java.myHandler.Looper.prepare();
        myselfHandler = new com.jay.java.myHandler.Handler() {
            @Override
            public void handlerMessage(com.jay.java.myHandler.Message msg) {
                super.handlerMessage(msg);
                Log.d(TAG, "myselfHandler-handleMessage: thread id=" + Thread.currentThread().getId());
                Log.d(TAG, "myselfHandler-handleMessage: msg= " + msg.toString());
            }
        };

        Button btnMyHandler = findViewById(R.id.btn_handler_my);
        btnMyHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread myThread = new MyThread();
                myThread.start();
            }
        });
        // TODO: 2019/4/4  思循环导致AMR
//        Looper.loop();
    }

    com.jay.java.myHandler.Handler myselfHandler;

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Log.d(TAG, "MyThread-run: thread id=" + Thread.currentThread().getId());
                Log.d(TAG, "开始发送");
                Thread.sleep(5000);
                Log.d(TAG, "发送完成");
                com.jay.java.myHandler.Message message = new com.jay.java.myHandler.Message(UUID.randomUUID().toString());
                myselfHandler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Handler threadHandler;

    private void initHandlerWithThread() {
        new Thread(new Runnable() {
            @SuppressLint("HandlerLeak")
            @Override
            public void run() {
                /**
                 *  1、创建了Looper对象，然后Looper对象中创建了MessageQueue
                 *  2、并将当前的Looper对象跟当前的线程（子线程）绑定ThreadLocal
                 */
                Looper.prepare();

                /**
                 * 1、创建Handler对象，然后从当前线程中获取Looper对象，然后获取到MessageQueue对象
                 */
                threadHandler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.d(TAG, "threadHandler-handleMessage: thread id=" + Thread.currentThread().getId());
                        Toast.makeText(HandlerActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    }
                };

                //获取当前线程中的Looper对象
                Looper.myLooper();
                /**
                 * 1、从当前线程中找到之前创建的Looper对象，然后找到MessageQueue
                 * 2、开启死循环，遍历消息池中的消息
                 * 3、当获取到msg的时候，调用这个msg的handler的dispatchMsg方法，让msg执行起来
                 */
                Looper.loop();
            }
        }).start();
        Button btnThread = findViewById(R.id.btn_handler_thread);
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从消息池中获取一个旧的msg，如果没有，重新创建消息
                Log.d(TAG, "btnThread-onClick: thread id=" + Thread.currentThread().getId());
                Message message = threadHandler.obtainMessage(1, "我是主线程发送来是消息");
                message.sendToTarget();
            }
        });
    }

    private void initHandlerWithRunnable() {
        Button btnRunnable = findViewById(R.id.btn_handler_runnable);
        tvRunnableInfo = findViewById(R.id.tv_run_info);
        btnRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "btnRunnable-onClick: thread id=" + Thread.currentThread().getId());
                DownloadThread downloadThread = new DownloadThread();
                downloadThread.start();
                tvRunnableInfo.setVisibility(View.GONE);
            }
        });
    }

    class DownloadThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Log.d(TAG, "downloadThread-run: thread id=" + Thread.currentThread().getId());
                Log.d(TAG, "开始下载");
                Thread.sleep(5000);
                Log.d(TAG, "下载完成");
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "runnable-run: thread id=" + Thread.currentThread().getId());
                        tvRunnableInfo.setVisibility(View.VISIBLE);
                    }
                };
                runnableHandler.post(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d(TAG, "messageHandler-handleMessage: thread id=" + Thread.currentThread().getId());
                    tvMessageInfo.setVisibility(View.VISIBLE);
            }
        }
    };

    private void initHandlerWithMessage() {
        Button btnMessage = findViewById(R.id.btn_handler_message);
        tvMessageInfo = findViewById(R.id.tv_msg_info);
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "btnMessage-onClick: thread id=" + Thread.currentThread().getId());
                UploadThread uploadThread = new UploadThread();
                uploadThread.start();
                tvMessageInfo.setVisibility(View.GONE);
            }
        });
    }

    class UploadThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Log.d(TAG, "uploadThread-run: thread id=" + Thread.currentThread().getId());
                Log.d(TAG, "开始上传");
                Thread.sleep(5000);
                Log.d(TAG, "上传完成");
                Message message = new Message();
                message.what = 1;
                messageHandler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
