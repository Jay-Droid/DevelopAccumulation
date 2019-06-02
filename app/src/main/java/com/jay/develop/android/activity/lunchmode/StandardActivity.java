package com.jay.develop.android.activity.lunchmode;

import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jay.develop.R;
import com.jay.develop.main.DemoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class StandardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Log.d(TAG, "StandardActivity-->onCreate");
    }

    public static final String TAG = "LaunchMode";

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "StandardActivity-->onNewIntent");
    }

    private void initView() {
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(getLinearLayoutManager());
        recyclerview.setAdapter(getDemoAdapter());
    }

    private List<DemoListAdapter.DemoItem> getDemoData() {
        List<DemoListAdapter.DemoItem> demoList = new ArrayList<>();
        demoList.add(new DemoListAdapter.DemoItem("StandardActivity", "标准模式+FLAG_ACTIVITY_NEW_TASK\n这会产生与 \"singleTask\"launchMode 值相同的行为。",
                StandardActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("StandardActivity", "标准模式+FLAG_ACTIVITY_SINGLE_TOP\n这会产生与 \"singleTop\"launchMode 值相同的行为",
                StandardActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("StandardActivity", "标准模式+FLAG_ACTIVITY_CLEAR_TOP\n产生这种行为的 launchMode 属性没有对应值。",
                StandardActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("StandardActivity", "标准模式",
                StandardActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("SingleTopActivity", "栈顶复用模式",
                SingleTopActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("SingleTaskActivity", "栈内复用模式",
                SingleTaskActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("SingleInstanceActivity", "独享任务战模式",
                SingleInstanceActivity.class));
        return demoList;
    }

    public static final String FLAG_ACTIVITY_NEW_TASK = "FLAG_ACTIVITY_NEW_TASK";
    public static final String FLAG_ACTIVITY_SINGLE_TOP = "FLAG_ACTIVITY_SINGLE_TOP";
    public static final String FLAG_ACTIVITY_CLEAR_TOP = "FLAG_ACTIVITY_CLEAR_TOP";

    private DemoListAdapter getDemoAdapter() {
        return new DemoListAdapter(getDemoData(), new DemoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DemoListAdapter.DemoItem item) {
                Intent intent = new Intent(StandardActivity.this, item.getActivity());
                if (item.getDescription().contains(FLAG_ACTIVITY_NEW_TASK)) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else if (item.getDescription().contains(FLAG_ACTIVITY_SINGLE_TOP)) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                } else if (item.getDescription().contains(FLAG_ACTIVITY_CLEAR_TOP)) {
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                startActivity(intent);
            }
        });
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
