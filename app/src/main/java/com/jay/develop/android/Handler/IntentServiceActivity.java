package com.jay.develop.android.Handler;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.develop.R;
import com.jay.develop.android.Service.IntentTestService;

public class IntentServiceActivity extends AppCompatActivity {
    private int intentServiceCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        initIntentService();
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
}
