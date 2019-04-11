package com.jay.developaccumulation.android.Activity.lifecycle;

import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.jay.developaccumulation.R;

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
