package com.example.project_se150664;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import MODEL.User;
import quantriDAO.QTDAO;

public class LoginActivity extends AppCompatActivity {
    // declare
    EditText editTExt_User, editText_password;
    CheckBox checkBox;
    Button button_login;
    public  static User users;
    QTDAO qtdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        qtdao = new QTDAO(LoginActivity.this);

        //reflect to component in XML file
        editTExt_User = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        button_login = findViewById(R.id.btn_login);
        checkBox = findViewById(R.id.ckb);
        // load data
            loadData();
        //

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTExt_User.getText().toString();
                String password = editText_password.getText().toString();

                boolean check = checkBox.isChecked();
                User newuser = new User(username,password);

                if(qtdao.CheckUser(newuser)){
                    SaveInfo(username,password,check);
                    users = newuser;
                    Toast.makeText(LoginActivity.this, "OK", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ShoppingViewActivity.class);

                    startActivity(intent);



                }
                else {
                    Toast.makeText(LoginActivity.this, "I can not login", Toast.LENGTH_LONG).show();

                }

                // hien thi thong bao cuoi man hinh
                /*if (user.equals("abc@abc.com") && password.equals("12345")) {
                    Toast.makeText(LoginActivity.this, "Thanks", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("users", user);
                    intent.putExtra("passwords", password);
                    //startActivity(intent);

                    startActivityForResult(intent, 2);
                } else {
                    Toast.makeText(LoginActivity.this, "fail", Toast.LENGTH_LONG).show();

                }*/


            }

        });
    }
    private  void SaveInfo( String un, String pw, boolean check){
        SharedPreferences pref = getSharedPreferences("thongtin.save",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(check){
            editor.putString("username", un);
            editor.putString("password", pw);
            editor.putBoolean("check",check);
        }
        else {
            editor.clear();
        }
        editor.commit();
    }

    private  void loadData(){
        SharedPreferences pref = getSharedPreferences("thongtin.save",MODE_PRIVATE);
        boolean check = pref.getBoolean("check",false);
        if(check){
            editTExt_User.setText(pref.getString("username",""));
            editText_password.setText(pref.getString("password",""));
            checkBox.setChecked(check);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String lgmessage = data.getStringExtra("logout");
            Toast.makeText(LoginActivity.this, lgmessage, Toast.LENGTH_LONG).show();


        }
    }
}