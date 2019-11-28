package com.jay.develop.demo.deeplink;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jay.develop.R;
import com.jay.develop.demo.deeplink.presenter.MobLinkHelper;
import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.util.LinkProperties;
import com.mob.moblink.MobLink;
import com.mob.moblink.Scene;

import java.util.HashMap;
import java.util.Map;

/**
 * deeplink:
 * https://com.jay.develop/deeplink
 */
public class DeepLinkActivity extends Activity implements MobLinkHelper.OnGetMobIdListener {
    TextView tv_data;
    private MobLinkHelper restorePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restorePresenter = new MobLinkHelper(this);
        setContentView(R.layout.activity_deeplink);
        tv_data = (TextView) findViewById(R.id.tv_data);
//        getDataFromBrowser();
//        getDataFromLinkedME();
        getMOdId();


    }

    private void getMOdId() {
        Log.d("jay", "---" + "getMOdId");

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("id", "111");
        params.put("scene", CommonUtils.SCENE_GAME);
        restorePresenter.getMobId(CommonUtils.GAME_PATH, params);

    }


    /**
     * 从deep link中获取数据
     */
    private void getDataFromBrowser() {
        Uri data = getIntent().getData();
        try {
            tv_data.setText(
                    "Uri  :" + data.toString() + "\n" +
                            "Scheme: " + data.getScheme() + "\n" +
                            "host: " + data.getHost() + "\n" +
                            "params: " + data.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDataFromLinkedME() {
        if (getIntent() != null) {
            //获取与深度链接相关的值
            LinkProperties linkProperties = getIntent().getParcelableExtra(LinkedME.LM_LINKPROPERTIES);
            if (linkProperties != null) {
                Log.i("LinkedME-Demo", "Channel " + linkProperties.getChannel());
                Log.i("LinkedME-Demo", "control params " + linkProperties.getControlParams());
                Log.i("LinkedME-Demo", "link(深度链接) " + linkProperties.getLMLink());
                Log.i("LinkedME-Demo", "是否为新安装 " + linkProperties.isLMNewUser());
                //获取自定义参数封装成的hashmap对象,参数键值对由集成方定义
                HashMap<String, String> hashMap = linkProperties.getControlParams();
                for (Map.Entry<String, String> stringStringEntry : hashMap.entrySet()) {
                    tv_data.append(stringStringEntry.getKey() + "---" + stringStringEntry.getValue() + "\n");
                }
            }
        }
    }

    @Override
    public void onReturnSceneData(Scene scene) {
        Log.d("jay", "onReturnSceneData");
        // 处理场景还原数据, 可以在这里做更新画面等操作
        String path = scene.getPath();
        HashMap<String, Object> params = scene.getParams();
        Log.d("jay", "path=" + path);
        for (Map.Entry<String, Object> stringStringEntry : params.entrySet()) {
            tv_data.append(stringStringEntry.getKey() + "---" + stringStringEntry.getValue() + "\n");
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MobLink.updateNewIntent(getIntent(), this);
    }

    @Override
    public void onMobIdGot(String mobId) {
        if (mobId != null) {
            mobId = mobId;
            Log.d("jay", "---mobId=" + mobId);
            Toast.makeText(DeepLinkActivity.this, "mobId=" + mobId, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(DeepLinkActivity.this, "Get MobID Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMobIdError(Throwable t) {
        if (t != null) {
            Log.d("jay", "---throwable=" + t.getLocalizedMessage());
            Toast.makeText(DeepLinkActivity.this, "error = " + t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}