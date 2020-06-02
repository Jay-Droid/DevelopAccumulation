package com.jay.develop.android.activity.lifecycle;

import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.develop.R;

/**
 * 以Dialog的形式展示的Activity
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);
    }
}
