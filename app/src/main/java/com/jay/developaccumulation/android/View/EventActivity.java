package com.jay.developaccumulation.android.View;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.developaccumulation.R;

public class EventActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private static final String TAG = "EventActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Button button1 = findViewById(R.id.button1);
        LinearLayout layout = findViewById(R.id.layout);

        layout.setOnTouchListener(this);
        button1.setOnTouchListener(this);
        layout.setOnClickListener(this);
        button1.setOnClickListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i(TAG, "onTouch:acton: " + event.getAction() + "----view:" + v);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: acton: " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick----view:" + v);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.d(TAG, "onPointerCaptureChanged: ");
    }

}
