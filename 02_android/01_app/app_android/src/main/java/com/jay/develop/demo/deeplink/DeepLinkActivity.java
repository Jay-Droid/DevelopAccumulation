package com.jay.develop.demo.deeplink;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jay.develop.R;
import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.util.LinkProperties;

import java.util.HashMap;
import java.util.Map;

/** deeplink: https://com.jay.develop/deeplink */
public class DeepLinkActivity extends Activity {
  TextView tv_data;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_deeplink);
    tv_data = (TextView) findViewById(R.id.tv_data);
    getDataFromBrowser();
    //        getDataFromLinkedME();
  }

  /** 从deep link中获取数据 */
  private void getDataFromBrowser() {
    Uri data = getIntent().getData();
    try {
      tv_data.setText(
          "Uri  :"
              + data.toString()
              + "\n"
              + "Scheme: "
              + data.getScheme()
              + "\n"
              + "host: "
              + data.getHost()
              + "\n"
              + "params: "
              + data.getPath());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void getDataFromLinkedME() {
    if (getIntent() != null) {
      // 获取与深度链接相关的值
      LinkProperties linkProperties = getIntent().getParcelableExtra(LinkedME.LM_LINKPROPERTIES);
      if (linkProperties != null) {
        Log.i("LinkedME-Demo", "Channel " + linkProperties.getChannel());
        Log.i("LinkedME-Demo", "control params " + linkProperties.getControlParams());
        Log.i("LinkedME-Demo", "link(深度链接) " + linkProperties.getLMLink());
        Log.i("LinkedME-Demo", "是否为新安装 " + linkProperties.isLMNewUser());
        // 获取自定义参数封装成的hashmap对象,参数键值对由集成方定义
        HashMap<String, String> hashMap = linkProperties.getControlParams();
        for (Map.Entry<String, String> stringStringEntry : hashMap.entrySet()) {
          tv_data.append(stringStringEntry.getKey() + "---" + stringStringEntry.getValue() + "\n");
        }
      }
    }
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    setIntent(intent);
  }
}
