package com.jay.developaccumulation.android.Fragment.backstack;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.jay.developaccumulation.R;

public class BackStackActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_stack);
        initView();
        // 先默认添加fragment1
        addFragment(SimpleFragment1.newInstance("TAB-01"), "fragment1");
    }

    private void initView() {
        Button btnTab1 = findViewById(R.id.btn_tab1);
        Button btnTab2 = findViewById(R.id.btn_tab2);
        Button btnTab3 = findViewById(R.id.btn_tab3);
        Button btnTab4 = findViewById(R.id.btn_tab4);
        btnTab1.setOnClickListener(this);
        btnTab2.setOnClickListener(this);
        btnTab3.setOnClickListener(this);
        btnTab4.setOnClickListener(this);
    }

    /**
     * 根据点击的按钮---依次替换Fragment
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tab1:
                addFragment(SimpleFragment1.newInstance("TAB-01"), "fragment1");
                break;
            case R.id.btn_tab2:
                addFragment(SimpleFragment2.newInstance("TAB-02"), "fragment2");
                break;
            case R.id.btn_tab3:
                addFragment(SimpleFragment3.newInstance("TAB-03"), "fragment3");
                break;
            case R.id.btn_tab4:
                addFragment(SimpleFragment4.newInstance("TAB-04"), "fragment4");
                break;
            default:
                break;
        }
    }

    public void addFragment(Fragment fragment, String tag) {
        // 开启事务
        FragmentTransaction beginTransaction = getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(
                        R.anim.slide_right_in,
                        R.anim.slide_left_out,
                        R.anim.slide_left_in,
                        R.anim.slide_right_out
                );
        // 执行事务,添加Fragment
        beginTransaction.replace(R.id.framelayout, fragment, tag);
        // 添加到回退栈,并定义标记
        beginTransaction.addToBackStack(tag);
        // 提交事务
        beginTransaction.commit();
    }

    /**
     * 监听Activity中的返回键,判断当前回退栈中的Fragment个数,
     * 如果回退栈中有大于一个,就一个个清除Fragment,如果只剩一个,
     * 说明只剩首页Fragment所对应的Fragment,就finish();
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断当前按键是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            // 判断当前回退栈中的fragment个数,
            if (backStackEntryCount > 1) {
                // 立即回退一步
                getSupportFragmentManager().popBackStackImmediate();
                // 获取当前退到了哪一个Fragment上,重新获取当前的Fragment回退栈中的个数
                FragmentManager.BackStackEntry backStack = getSupportFragmentManager()
                        .getBackStackEntryAt(getSupportFragmentManager()
                                .getBackStackEntryCount() - 1);
                // 获取当前栈顶的Fragment的标记值
                String tag = backStack.getName();
                // 判断当前是哪一个标记
                if ("fragment1".equals(tag)) {
                    Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
                } else if ("fragment2".equals(tag)) {
                    Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
                } else if ("fragment3".equals(tag)) {
                    Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
                } else if ("fragment4".equals(tag)) {
                    Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
                }
            } else {
                //回退栈中只剩一个时,退出应用
                finish();
            }
        }
        return true;
    }


    /**
     * 退出所有只剩首页而Fragment的代码
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // 判断当前按键是返回键
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            // 获取当前回退栈中的Fragment个数
//            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
//            Toast.makeText(this, "backStackEntryCount=" + backStackEntryCount, Toast.LENGTH_SHORT).show();
//            // 回退栈中至少有多个fragment,栈底部是首页
//            if (backStackEntryCount > 1) {
//                // 如果回退栈中Fragment个数大于一.一直退出
//                while (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//                    getSupportFragmentManager().popBackStackImmediate();
//                    //选中第一个界面
//                    Toast.makeText(this, "到底了", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                finish();
//            }
//
//        }
//        return true;
//    }


}
