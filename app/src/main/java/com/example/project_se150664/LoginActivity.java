package com.example.project_se150664;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    // declare
    EditText editTExt_User, editText_password;

    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //reflect to component in XML file
        editTExt_User = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.btn_login);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTExt_User.getText().toString();
                String password = editText_password.getText().toString();
                // hien thi thong bao cuoi man hinh
                if (user.equals("abc@abc.com") && password.equals("12345")) {
                    Toast.makeText(LoginActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("users", user);
                    intent.putExtra("passwords", password);
                    //startActivity(intent);

                    startActivityForResult(intent,2);
                } else {
                    Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_LONG).show();

                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            String lgmessage = data.getStringExtra("logout");
            Toast.makeText(LoginActivity.this, lgmessage, Toast.LENGTH_LONG).show();


        }
    }
}