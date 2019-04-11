package com.jay.developaccumulation.android;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jay.developaccumulation.R;
import com.jay.developaccumulation.android.Activity.lifecycle.LifecycleActivity;
import com.jay.developaccumulation.android.BroadcastReceiver.BroadcastReceiverActivity;
import com.jay.developaccumulation.android.Fragment.FragmentTestActivity;
import com.jay.developaccumulation.android.Handler.HandlerActivity;
import com.jay.developaccumulation.android.Service.ServiceActivity;
import com.jay.developaccumulation.android.View.ViewActivity;
import com.jay.developaccumulation.main.DemoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AndroidListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(getLinearLayoutManager());
        recyclerview.setAdapter(getDemoAdapter());
    }

    private List<DemoListAdapter.DemoItem> getDemoData() {
        List<DemoListAdapter.DemoItem> demoList = new ArrayList<>();
        demoList.add(new DemoListAdapter.DemoItem("Activity", "Activity生命周期相关",
                LifecycleActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("Fragment", "Fragment相关",
                FragmentTestActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("Service", "Service相关",
                ServiceActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("BroadcastReceiver", "BroadcastReceiver相关",
                BroadcastReceiverActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("Handler", "Handler相关",
                HandlerActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("View", "View相关",
                ViewActivity.class));
        return demoList;
    }


    private DemoListAdapter getDemoAdapter() {
        return new DemoListAdapter(getDemoData(), new DemoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DemoListAdapter.DemoItem item) {
                startActivity(new Intent(AndroidListActivity.this, item.getActivity()));
            }
        });
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
