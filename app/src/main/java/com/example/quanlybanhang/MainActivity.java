package com.example.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void viewNguoiDung(View view){
        Intent intent = new Intent(MainActivity.this, ListNguoiDungActivity.class);
        startActivity(intent);
    }

    public void viewLoaiHang(View view){
        Intent intent = new Intent(MainActivity.this, ListLoaiHangActivity.class);
        startActivity(intent);
    }

    public void viewListSanPhamActivity(View view){
        Intent intent = new Intent(MainActivity.this, ListSanPhamActivity.class);
        startActivity(intent);
    }
    public void ViewListHoaDonActivity(View view){
        Intent intent = new Intent(MainActivity.this,ListHoaDonActivity.class);
        startActivity(intent);
    }
    public void ViewTopSanPham(View view){
        Intent intent = new Intent(MainActivity.this, LuotSanPhamBanChayActivity.class);
        startActivity(intent);
    }
    public void ViewThongKeActivity(View view){
        Intent intent = new Intent(MainActivity.this,ThongKeDoanhThuActivity.class);
        startActivity(intent);
    }
}
