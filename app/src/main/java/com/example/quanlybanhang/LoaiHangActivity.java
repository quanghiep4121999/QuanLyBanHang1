package com.example.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.dao.LoaiHangDAO;
import com.example.quanlybanhang.model.LoaiHang;


public class LoaiHangActivity extends AppCompatActivity {
    LoaiHangDAO loaiHangDAO;
    EditText edMaLoaiHang, edTenLoaiHang,edViTri, edMota;
    String mMaloaihang;
    String mTenloaihang;
    int mVitri;
    String mMota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_hang);
        setTitle("Thêm Loại Hàng");
        edMaLoaiHang = findViewById(R.id.edMaLoaiHang);
        edTenLoaiHang = findViewById(R.id.edTenLoaiHang);
        edViTri = findViewById(R.id.edViTri);
        edMota = findViewById(R.id.edMoTa);
        loaiHangDAO = new LoaiHangDAO(LoaiHangActivity.this);
        try{
        Intent in = getIntent();
        Bundle b = in.getExtras();
        mMaloaihang = b.getString("MALOAIHANG");
        mTenloaihang = b.getString("TENLOAIHANG");
        mMota = b.getString("MOTA");
        mVitri = b.getInt("VITRI");
        edMaLoaiHang.setText(mMaloaihang);
        edMaLoaiHang.setEnabled(false);
        edTenLoaiHang.setText(mTenloaihang);
        edMota.setText(mMota);
        edViTri.setText(String.valueOf(mVitri));}
        catch(Exception ex){
    }

    }
    public void updateUser(View view){
        loaiHangDAO = new LoaiHangDAO(LoaiHangActivity.this);
        LoaiHang loaiHang = new LoaiHang(mMaloaihang,edTenLoaiHang.getText().toString(),edMota.getText().toString(), Integer.parseInt(edViTri.getText().toString()));
        if (loaiHangDAO.updateLoaiHang(loaiHang) > 0){
            Toast.makeText(getApplicationContext(),"Update thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public void showUsers(View view) {

        finish();
    }

    public void addUser(View view) {
        loaiHangDAO = new LoaiHangDAO(LoaiHangActivity.this);
        LoaiHang loaiHang = new LoaiHang(edMaLoaiHang.getText().toString(),edTenLoaiHang.getText().toString(),edMota.getText().toString(),
               Integer.parseInt(edViTri.getText().toString()));
        try {
            if (validateForm()>0){
                if (loaiHangDAO.inserLoaiHang(loaiHang) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }             }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public int validateForm(){
        int check = 1;
        if (edMaLoaiHang.getText().length() == 0 || edTenLoaiHang.getText().length() == 0
                || edMota.getText().length() == 0 || edViTri.getText().length()==0 ) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;

    }
}
