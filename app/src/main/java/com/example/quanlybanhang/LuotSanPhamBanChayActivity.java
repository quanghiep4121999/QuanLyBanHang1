package com.example.quanlybanhang;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.adapter.SanPhamAdapter;
import com.example.quanlybanhang.dao.SanPhamDAO;
import com.example.quanlybanhang.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class LuotSanPhamBanChayActivity extends AppCompatActivity {
    public static List<SanPham> dsSanPham = new ArrayList<>();
    ListView lvSanPham;
    SanPhamAdapter adapter = null;
    SanPhamDAO sanPhamDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luot_san_pham_ban_chay);
        setTitle("TOP 10 SẢN PHẨM BÁN CHẠY");
        lvSanPham = (ListView) findViewById(R.id.lvSanPhamTOP);
        edThang = (EditText) findViewById(R.id.edThang);
    }     public void VIEW_SAN_PHAM_TOP_10(View view){
        if (Integer.parseInt(edThang.getText().toString())>13 || Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getApplicationContext(),"Không đúng định dạng tháng (112)", Toast.LENGTH_SHORT).show();
        }else {
            sanPhamDAO = new SanPhamDAO(LuotSanPhamBanChayActivity.this);
            dsSanPham = sanPhamDAO.getSanPhamTop10(edThang.getText().toString());
            adapter = new SanPhamAdapter(this, dsSanPham);
            lvSanPham.setAdapter(adapter);
        }   
    }
}
