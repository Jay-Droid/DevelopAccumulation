package com.jay.develop.android.Activity.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.develop.R;
/**
 * 正常显示的Activity
 */
public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
}
