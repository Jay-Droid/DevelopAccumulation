package com.jay.develop.android.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.jay.develop.R;

public class ForegroundService extends Service {

    public static final String TAG = "ForegroundService";

    private MediaPlayer mediaPlayer;

    private int startId;

    public enum Control {
        PLAY, PAUSE, STOP
    }

    public ForegroundService() {
        Log.d(TAG, "ForegroundService: ");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music);
            mediaPlayer.setLooping(false);
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand---startId: " + startId);
        this.startId = startId;
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Control control = (Control) bundle.getSerializable("Key");
            if (control != null) {
                switch (control) {
                    case PLAY:
                        play();
                        break;
                    case PAUSE:
                        pause();
                        break;
                    case STOP:
                        stop();
                        break;
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    private void play() {
        Log.d(TAG, "play: ");
//        if (!mediaPlayer.isPlaying()) {
//            mediaPlayer.start();
//        }
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification.Builder mBuilder;
            String channelID = "1";
            String channelName = "channel_name";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel chan1 = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(chan1);
                mBuilder = new Notification.Builder(this, "static");
                mBuilder.setChannelId(channelID);
            } else {
                mBuilder = new Notification.Builder(this);
            }
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle("这是标题");
            mBuilder.setContentText("播放着音乐");
            startForeground(1, mBuilder.build());
        }
    }

    private void pause() {
        Log.d(TAG, "pause: ");
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void stop() {
        Log.d(TAG, "stop: ");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        stopSelf(startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
