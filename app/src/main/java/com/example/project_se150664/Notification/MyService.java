package com.example.project_se150664.Notification;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.project_se150664.R;

public class MyService extends Service {

    MediaPlayer mediaPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "on running", Toast.LENGTH_LONG).show();
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(MyService.this, "Create a service", Toast.LENGTH_LONG).show();
        mediaPlayer = MediaPlayer.create(this, R.raw.all);
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyService.this, "Create a service", Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}