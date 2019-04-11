package com.jay.developaccumulation.android.Activity.lifecycle;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jay.developaccumulation.R;
import com.jay.developaccumulation.android.Activity.lunchmode.StandardActivity;
import com.jay.developaccumulation.other.CustomPopupWindow;

/**
 * 参考博客：
 * https://blog.csdn.net/wenzhi20102321/article/details/68941263
 * https://developer.android.com/guide/components/activities/activity-lifecycle
 * https://www.jianshu.com/p/27181e2e32d2
 */
public class LifecycleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LifecycleActivity.class.getSimpleName();
    private ConstraintLayout clContainer;
    private TextView tvInfo;
    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    int mCurrentScore = 0;
    int mCurrentLevel = 0;
    private LifeCycleView tvLifeCycle;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.e(TAG, "onSaveInstanceState()→当您的Activity开始停止时，系统会调用onSaveInstanceState()");
        tvInfo.append("\n onSaveInstanceState()");
        // 保存用户自定义的状态
        savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
        savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);
        // 调用父类交给系统处理，这样系统能保存视图层次结构状态
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * 注意
     * 1、如果是用户自动按下返回键，或程序调用finish()退出程序，是不会触发onSaveInstanceState()和onRestoreInstanceState()的。
     * 2、每次用户旋转屏幕时，您的Activity将被破坏并重新创建。当屏幕改变方向时，系统会破坏并重新创建前台Activity，
     * 因为屏幕配置已更改，您的Activity可能需要加载替代资源（例如布局）。即会执行onSaveInstanceState()和onRestoreInstanceState()的。
     */

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e(TAG, "onRestoreInstanceState()→当Activity在之前被破坏后重新创建时会调用onRestoreInstanceState()");
        tvInfo.append("\n onRestoreInstanceState()");
        // 总是调用超类，以便它可以恢复视图层次超级
        super.onRestoreInstanceState(savedInstanceState);
        // 从已保存的实例中恢复状态成员
        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
        tvInfo.append("\n onRestoreInstanceState()→mCurrentScore= " + mCurrentScore);
        tvInfo.append("\n onRestoreInstanceState()→mCurrentLevel= " + mCurrentLevel);
    }

    private void getSavedInstanceState(Bundle savedInstanceState) {
        // 检查是否正在重新创建一个以前销毁的实例
        if (savedInstanceState != null) {
            // 从已保存状态恢复成员的值
            mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
            mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
            Log.e(TAG, "onCreate(Bundle savedInstanceState)→当Activity在之前被破坏后重新创建时，会从Bundle中取出之前的状态数据");
            tvInfo.append("\n onCreate(Bundle savedInstanceState)→mCurrentScore= " + mCurrentScore);
            tvInfo.append("\n onCreate(Bundle savedInstanceState)→mCurrentLevel= " + mCurrentLevel);
        }
    }

    /**
     * activity第一次创建时被调用
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        initView();
        getSavedInstanceState(savedInstanceState);
        Log.d(TAG, "onCreate()→android系统调用onCreate方法第一次创建LifecycleActivity，LifecycleActivity此时处于【运行状态】");
        tvInfo.append("\n onCreate()");
        getLifecycle().addObserver(tvLifeCycle);
    }

    /**
     * 初始化视图
     */
    private void initView() {
        clContainer = findViewById(R.id.cl_container);
        Button btnLunchmode = findViewById(R.id.btn_lunchmode);
        Button btnNormal = findViewById(R.id.btn_normal);
        Button btnDialog = findViewById(R.id.btn_dialog);
        Button btnOrientation = findViewById(R.id.btn_orientation);
        ImageView ivClearInfo = findViewById(R.id.iv_clear);
        tvLifeCycle = findViewById(R.id.tv_lifecycle);
        tvInfo = findViewById(R.id.tv_info);
        btnLunchmode.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        ivClearInfo.setOnClickListener(this);
        btnOrientation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lunchmode:
                startActivity(new Intent(LifecycleActivity.this, StandardActivity.class));
                break;
            case R.id.iv_clear:
                tvInfo.setText("Hello Activity Lifecycle:");
                tvLifeCycle.setText("LifeCycleView: \n");
                break;
            case R.id.btn_normal:
                startActivity(new Intent(LifecycleActivity.this, NormalActivity.class));
                break;
            case R.id.btn_dialog:
                dialogActivity();
                //在activity弹出一个对话框，并不会执行onPause()生命周期方法
                //在我们创建对话框的时候,需要传入一个context类型的参数.这个参数也就说明我们的dialog是在这个上下文创建的,
                //我们的activity也是正在交互的,并不会执行onPause的
//                alertDialog();
//                popupWindow();
                break;
            case R.id.btn_orientation:
                //获取屏幕的方向  ,数值1表示竖屏，数值2表示横屏
                int screenNum = getResources().getConfiguration().orientation;
                //（如果竖屏）设置屏幕为横屏
                if (screenNum == 1) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //设置为置屏幕为竖屏
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
        }
    }

    private void popupWindow() {
        CustomPopupWindow scalablePopupWindow = new CustomPopupWindow(this);
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popup, null);
        scalablePopupWindow.setContentView(contentView);
        scalablePopupWindow.showAsDropDown(clContainer, Gravity.CENTER, 0, 0);
    }

    private void alertDialog() {
        final String[] items = new String[]{"北京", "上海", "广州", "深圳"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher).setTitle("提示").setItems(items, null);
        builder.create().show();
    }

    private void dialogActivity() {
        Intent intent1 = new Intent(LifecycleActivity.this, DialogActivity.class);
        startActivity(intent1);
    }

    /**
     * 屏幕方向发生改变的回调方法
     * 在xml文件中定义了android:configChanges="orientation"后，并且视图显示后屏幕的方向发生改变，这个方法才会被回调。
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        tvInfo.append("\n onConfigurationChanged(Configuration newConfig)");
        Log.e(TAG, "onConfigurationChanged(Configuration newConfig)→newConfig.orientation=" + newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvInfo.append("\n 当前屏幕为横屏");
        } else {
            tvInfo.append("\n 当前屏幕为竖屏");
        }
        super.onConfigurationChanged(newConfig);
    }

    /**
     * activity从后台重新回到前台(由不可见变为可见)时被调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()→LifecycleActivity从后台重新回到前台(由不可见变为可见)，android系统调用了onStart方法，LifecycleActivity此时处于【运行状态】");
        tvInfo.append("\n onStart()");
    }

    /**
     * Activity准备好和用户进行交互的时候被调用
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()→LifecycleActivity准备好和用户进行交互的，android系统调用了onResume方法，LifecycleActivity此时处于【运行状态】");
        tvInfo.append("\n onResume()");
        mCurrentScore = 666;
        mCurrentLevel = 777;
    }

    /**
     * Activity准备去启动或者恢复另一个Activity的时候调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()→LifecycleActivity准备去启动另一个Activity，android系统调用了onPause方法，LifecycleActivity此时处于【暂停状态】");
        tvInfo.append("\n onPause()");
    }

    /**
     * 退出当前Activity或者跳转到新Activity时被调用
     * Activity完全不可见的时候调用
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()→LifecycleActivity已经完全不可见了，android系统调用了onStop方法，LifecycleActivity此时处于【停止状态】");
        tvInfo.append("\n onStop()");
    }

    /**
     * Activity从后台重新回到前台时被调用
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart()→LifecycleActivity由【停止状态】变为【运行状态】，android系统调用了onRestart方法，LifecycleActivity此时处于【运行状态】");
        tvInfo.append("\n onRestart()");
    }

    /**
     * 退出当前Activity时被调用,调用之后Activity就结束了
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()→LifecycleActivity已经准备被销毁了，android系统调用了onDestroy方法，LifecycleActivity此时处于【销毁状态】");
        tvInfo.append("\n onDestroy()");
    }

}
