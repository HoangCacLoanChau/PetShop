package com.example.project_se150664;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShoppingViewActivity extends AppCompatActivity {
    String s1[], s2[];
    int Image[] = {R.drawable.scottish, R.drawable.golden,
            R.drawable.husky, R.drawable.labrador,
            R.drawable.bulldog, R.drawable.bernese, R.drawable.alaskan,
            R.drawable.dog, R.drawable.dog, R.drawable.dog
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.call:
                String channel_id = "chanel_id";
                CharSequence name = "channel_name";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                Context context = getApplicationContext();
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel: 18001000"));
                PendingIntent pe = PendingIntent.getActivity(getApplicationContext(), 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
                Notification builder = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_launcher_background).
                        setContentTitle("Notification").setContentText(" Calling to our Number")
                        .setChannelId(channel_id).setContentIntent(pe).build();
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// chu o
                    NotificationChannel mchannel = new NotificationChannel(channel_id, name, importance);
                    manager.createNotificationChannel(mchannel);
                }
                manager.notify(0, builder);

                return true;
            case R.id.product2:
                Toast.makeText(ShoppingViewActivity.this, "2", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        //
        RecyclerView recyclerView = findViewById(R.id.Recycle);
        recyclerView.setHasFixedSize(true);
        //araylist
        s1 = getResources().getStringArray(R.array.catagories);
        s2 = getResources().getStringArray(R.array.description);
        // insert to adapet
        Adapter adapter = new Adapter(this, s1, s2, Image);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //TODO: register context menu in onCreate
        registerForContextMenu(recyclerView);

    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.context_add:
                Toast.makeText(ShoppingViewActivity.this, "add to your wishlist", Toast.LENGTH_LONG).show();
                return true;
            case R.id.context_share:
                Toast.makeText(ShoppingViewActivity.this, "Share to your friend", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}