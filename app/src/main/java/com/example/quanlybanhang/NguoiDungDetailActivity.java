package com.example.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.dao.NguoiDungDAO;


public class NguoiDungDetailActivity extends AppCompatActivity {
    EditText edFullName, edPhone, edChucVu;
    NguoiDungDAO nguoiDungDAO;
    String username,fullname,phone, chucvu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("UPDATE NHÂN VIÊN");
        setContentView(R.layout.activity_nguoi_dung_detail);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edChucVu = (EditText) findViewById(R.id.edChucVu);
        nguoiDungDAO = new NguoiDungDAO(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        username = b.getString("USERNAME");
        chucvu = b.getString("CHUCVU");
        edFullName.setText(fullname);
        edPhone.setText(phone);
        edChucVu.setText(chucvu);
    }
    public void updateUser(View view){
        if (nguoiDungDAO.updateInfoNguoiDung(username,edPhone.getText().toString(),edFullName.getText().toString(), edChucVu.getText().toString())>0){
            Toast.makeText(getApplicationContext(),"Lưu thành công", Toast.LENGTH_SHORT).show();
        }
    }
    public void Huy(View view){
        finish();
    }

}

