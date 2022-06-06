package com.example.project_se150664;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //declare
        TextView home_user, home_password;
        Button button_shoping;
        Button button_logout;
        // reflect
        home_user = findViewById(R.id.h_username);
        home_password = findViewById(R.id.h_password);
        button_logout = findViewById(R.id.button);
        button_shoping = findViewById(R.id.button_shopping);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String logoutmessage =" LOGGED OUT";
                intent.putExtra("logout",logoutmessage);
                setResult(2,intent);
                finish();
            }


        });
        button_shoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ShoppingViewActivity.class);
                startActivity(intent);
            }
        });
       // get data from login activity
        if (getIntent() != null) {
            String username_h = getIntent().getStringExtra("users");
            home_user.setText(username_h);
            String password_h = getIntent().getStringExtra("passwords");
            home_password.setText(password_h);

        }

    }
}