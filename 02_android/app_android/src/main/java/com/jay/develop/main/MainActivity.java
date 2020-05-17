package com.jay.develop.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jay.develop.R;
import com.jay.develop.android.AndroidListActivity;
import com.jay.develop.java.JavaListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jay
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(getLinearLayoutManager());
        recyclerview.setAdapter(getDemoAdapter());
    }

    private List<DemoListAdapter.DemoItem> getDemoData() {
        List<DemoListAdapter.DemoItem> demoList = new ArrayList<>();
        demoList.add(new DemoListAdapter.DemoItem("Android", "Android整理",
                AndroidListActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("Java", "Java整理",
                JavaListActivity.class));
        return demoList;
    }


    private DemoListAdapter getDemoAdapter() {
        return new DemoListAdapter(getDemoData(), new DemoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DemoListAdapter.DemoItem item) {
                startActivity(new Intent(MainActivity.this, item.getActivity()));
            }
        });
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
