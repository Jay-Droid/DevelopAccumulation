package com.jay.develop.android.View;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import com.jay.develop.R;
import com.jay.develop.android.View.TouchEvent.EventActivity;

public class ViewActivity extends Activity {
    private static final String TAG = "ViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //从setContentView入手了解View的绘制其实过程
        setContentView(R.layout.activity_view);
        initEvent();
    }

    private void initEvent() {
        Button btnEvent = findViewById(R.id.btn_event);
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, EventActivity.class));
            }
        });
    }
}
