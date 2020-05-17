package com.jay.develop.demo.deeplink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jay.develop.R;

public class DeepLinkWebActivity extends AppCompatActivity {


    public static final String TAG = "deeplink";

    WebView web_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link_web);

        web_main = (WebView) findViewById(R.id.web_main);

        web_main.loadUrl("file:///android_asset/deep_link_open_or_download.html");

        web_main.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "shouldOverrideUrlLoading: url=" + url);
                if (url.startsWith("https://")) {
                    Uri uri = Uri.parse(url);
                    Toast.makeText(DeepLinkWebActivity.this, "Uri=" + uri.toString(), Toast.LENGTH_SHORT)
                            .show();
                    openDeepLink(uri.toString());
                    return true; //返回true，代表要拦截这个url
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    private void openDeepLink(String uri) {
        if (uri.contains("https://com.jay.develop/deeplink")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage(getPackageName());
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            startActivity(intent);
        }
    }
}
