package com.jay.develop.android;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jay.develop.R;
import com.jay.develop.android.activity.lifecycle.LifecycleActivity;
import com.jay.develop.android.broadcastReceiver.BroadcastReceiverActivity;
import com.jay.develop.android.camera.QRCodeActivity;
import com.jay.develop.android.fragment.FragmentTestActivity;
import com.jay.develop.android.handler.HandlerActivity;
import com.jay.develop.android.recycleview.XAdapterActivity;
import com.jay.develop.android.service.ServiceActivity;
import com.jay.develop.android.view.ViewActivity;
import com.jay.develop.android.webview.WebViewActivity;
import com.jay.develop.demo.deeplink.DeepLinkActivity;
import com.jay.develop.demo.deeplink.DeepLinkWebActivity;
import com.jay.develop.main.DemoListAdapter;

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
        demoList.add(new DemoListAdapter.DemoItem("RecyclerView", "RecyclerView相关",
                XAdapterActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("WebViewActivity", "WebViewActivity",
                WebViewActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("QRCodeActivity", "相机相关",
                QRCodeActivity.class));

        demoList.add(new DemoListAdapter.DemoItem("DeepLinkWebActivity", "深度链接",
                DeepLinkWebActivity.class));
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
