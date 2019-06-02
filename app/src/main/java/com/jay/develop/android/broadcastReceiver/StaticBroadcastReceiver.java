package com.jay.develop.android.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**

 */
public class StaticBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "StaticBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: 网络状态发生变化");
        if (!isNetworkAvailable(context)) {
            Toast.makeText(context, "network disconnected!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (NetworkInfo anInfo : info) {
                Log.d(TAG, "isNetworkAvailable: stage=" + anInfo.getDetailedState().name());
                if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }
}
