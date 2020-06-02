package com.jay.develop.android.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.jay.develop.R;
import com.jay.develop.android.activity.lifecycle.LifeCycleView;
import com.jay.develop.android.fragment.adapter.MyFragmentPagerAdapter;
import com.jay.develop.android.fragment.adapter.MyFragmentStatePagerAdapter;
import com.jay.develop.android.fragment.backstack.BackStackActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentTestActivity extends AppCompatActivity {

    private static final String TAG = "SimpleFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "FragmentTestActivity-->onCreate: ");
        setContentView(R.layout.activity_fragment);
        initNavigation();
        initBackStack();
        initLifeCycleView();
        initDynamicFragment();
        initAdapterFragment();
    }

    private void initBackStack() {
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentTestActivity.this, BackStackActivity.class));
            }
        });
    }
   private void initNavigation() {
        Button btnNavigation = findViewById(R.id.btn_navigation);
        btnNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentTestActivity.this, NavigationActivity.class));
            }
        });
    }

    private void initAdapterFragment() {
        Button btnTab1 = findViewById(R.id.btn_tab1);
        Button btnTab2 = findViewById(R.id.btn_tab2);
        Button btnTab3 = findViewById(R.id.btn_tab3);

        Button btnTab11 = findViewById(R.id.btn_tab11);
        Button btnTab22 = findViewById(R.id.btn_tab22);
        Button btnTab33 = findViewById(R.id.btn_tab33);
        final ViewPager viewPager = findViewById(R.id.viewpager);
        final ViewPager viewPager2 = findViewById(R.id.viewpager2);
        List<Fragment> fragmentList = new ArrayList<>();
        List<Fragment> fragmentList2 = new ArrayList<>();
        fragmentList.add(SimpleFragment.newInstance("Tab" + 0));
        fragmentList.add(StaticFragment.newInstance("Tab" + 1));
        fragmentList.add(StaticFragment.newInstance("Tab" + 2));

        fragmentList2.add(SimpleFragment.newInstance("Tab" + 0));
        fragmentList2.add(StaticFragment.newInstance("Tab" + 1));
        fragmentList2.add(StaticFragment.newInstance("Tab" + 2));

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        MyFragmentStatePagerAdapter fragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager(), fragmentList2);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager2.setAdapter(fragmentStatePagerAdapter);
        btnTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        btnTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        btnTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

        btnTab11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(0);
            }
        });
        btnTab22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(1);
            }
        });
        btnTab33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(2);
            }
        });
    }

    private void initDynamicFragment() {
        Button btnAdd = findViewById(R.id.btn_add);
        Button btnRemove = findViewById(R.id.btn_remove);
        Button btnReplace = findViewById(R.id.btn_replace);
        Button btnHide = findViewById(R.id.btn_hide);
        Button btnShow = findViewById(R.id.btn_show);
        Button btnAttach = findViewById(R.id.btn_attach);
        Button btnDetach = findViewById(R.id.btn_detach);
        final SimpleFragment simpleFragment = SimpleFragment.newInstance("simpleFragment");
        final SimpleFragment simpleFragment2 = SimpleFragment.newInstance("simpleFragment2");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fl_container, simpleFragment);
                simpleFragment.setInfo("Add Fragment");
                fragmentTransaction.commit();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.remove(simpleFragment);
                fragmentTransaction.remove(simpleFragment2);
                fragmentTransaction.commit();
            }
        });
        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.fl_container, simpleFragment2);
                simpleFragment2.setInfo("Replease Fragment");
                fragmentTransaction.commit();
            }
        });

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.hide(simpleFragment);
                fragmentTransaction.hide(simpleFragment2);
                fragmentTransaction.commit();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.show(simpleFragment);
                fragmentTransaction.show(simpleFragment2);
                simpleFragment.setInfo("Show Fragment");
                simpleFragment2.setInfo("Show Fragment");
                fragmentTransaction.commit();
            }
        });
        btnAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.attach(simpleFragment);
                fragmentTransaction.attach(simpleFragment2);
                simpleFragment.setInfo("Attach Fragment");
                simpleFragment2.setInfo("Attach Fragment");
                fragmentTransaction.commit();
            }
        });

        btnDetach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(simpleFragment);
                fragmentTransaction.detach(simpleFragment2);
                fragmentTransaction.commit();
            }
        });

    }

    private void initLifeCycleView() {
        LifeCycleView lifeCycleView = findViewById(R.id.tv_lifecycle);
        getLifecycle().addObserver(lifeCycleView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "FragmentTestActivity-->onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "FragmentTestActivity-->onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "FragmentTestActivity-->onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "FragmentTestActivity-->onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "FragmentTestActivity-->onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "FragmentTestActivity-->onDestroy: ");
    }

}
