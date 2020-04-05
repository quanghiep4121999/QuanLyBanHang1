package com.example.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chk = findViewById(R.id.chkRememberPass);
    }

    public void rememberUserPass(String u, String p, boolean status){
        SharedPreferences sharedPref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(status==false){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
    public int checkLogin1(String u, String p){
        if(u.equals("admin") && p.equals(("admin"))){
            return 1;
        } else {
            return -1;
        }
    }
    String strU, strP;
    public void checkLogin(View view){
        strU = edUserName.getText().toString();
        strP = edPassword.getText().toString();
        if(strU.isEmpty() || strP.isEmpty()){
            Toast.makeText(getApplicationContext(), "Không được để trống user, password",Toast.LENGTH_LONG).show();
        }else {
            if(checkLogin1(strU,strP)>0){
                Toast.makeText(getApplicationContext(), "Login Thành Công",Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                }, 1500);
            }else {
                Toast.makeText(getApplicationContext(), "User hoặc Pass không đúng",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void saveUP(View view){
        String u = edUserName.getText().toString();
        String p = edPassword.getText().toString();
        boolean status = chk.isChecked();
        rememberUserPass(u,p,status);
    }
}
