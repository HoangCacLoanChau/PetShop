package com.example.project_se150664.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_se150664.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btn_start, btn_stop;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btn_start = findViewById(R.id.start);
        btn_stop = findViewById(R.id.stop);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String channel_id = "chanel_id";
                CharSequence name = "channel_name";
                int importance= NotificationManager.IMPORTANCE_DEFAULT;
                Context context = getApplicationContext();
                Intent i = new Intent(
                  Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                PendingIntent pe = PendingIntent.getActivity(getApplicationContext(), 0,i,PendingIntent.FLAG_CANCEL_CURRENT);
                Notification builder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("this is title").setContentText(" Music is playing")
                                            .setChannelId(channel_id).setContentIntent(pe).build();
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){// chu o
                    NotificationChannel mchannel = new NotificationChannel(channel_id, name, importance);
                    manager.createNotificationChannel(mchannel);
                }
                manager.notify(0,builder);
                Intent ii = new Intent(NotificationActivity.this,MyService.class);
                startActivity(i);
            }
        }
        );
                btn_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(NotificationActivity.this,MyService.class);

                    }
                });
    }
}