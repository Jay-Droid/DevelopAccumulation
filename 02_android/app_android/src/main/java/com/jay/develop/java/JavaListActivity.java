package com.jay.develop.java;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jay.develop.R;
import com.jay.develop.java.dynamic_proxy.DynamicProxyActivity;
import com.jay.develop.java.dynamic_proxy.practice.XLRouter;
import com.jay.develop.java.mult_thread.ThreadActivity;
import com.jay.develop.java.reflection.ReflectionActivity;
import com.jay.develop.main.DemoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class JavaListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData() {
        XLRouter.initXLRouter(this);
    }

    private void initView() {
        RecyclerView recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(getLinearLayoutManager());
        recyclerview.setAdapter(getDemoAdapter());
    }

    private List<DemoListAdapter.DemoItem> getDemoData() {
        List<DemoListAdapter.DemoItem> demoList = new ArrayList<>();
        demoList.add(new DemoListAdapter.DemoItem("反射", "反射在Android中的运用",
                ReflectionActivity.class));

        demoList.add(new DemoListAdapter.DemoItem("动态代理", "动态代理实现页面路由框架",
                DynamicProxyActivity.class));
        demoList.add(new DemoListAdapter.DemoItem("多线程", "Android 线程信息",
                ThreadActivity.class));
        return demoList;
    }


    private DemoListAdapter getDemoAdapter() {
        return new DemoListAdapter(getDemoData(), new DemoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DemoListAdapter.DemoItem item) {
                if (item.getActivity().getSimpleName().contains("DynamicProxyActivity")) {
                    XLRouter.routerUri().jumpToDynamicProxyPage("我是通过XLRouter路由框架跳转的", "我是描述");
                } else {
                    startActivity(new Intent(JavaListActivity.this, item.getActivity()));

                }
            }
        });
    }

    private LinearLayoutManager getLinearLayoutManager() {
        return new LinearLayoutManager(this);
    }
}
