package com.example.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.dao.SanPhamDAO;
import com.example.quanlybanhang.dao.LoaiHangDAO;
import com.example.quanlybanhang.model.SanPham;
import com.example.quanlybanhang.model.LoaiHang;

import java.util.ArrayList;
import java.util.List;

public class ThemSanPhamActivity extends AppCompatActivity {
    SanPhamDAO sanPhamDAO;
    LoaiHangDAO loaiHangDAO;
    Spinner spnLoaiHang;
    EditText edMaSanPham, edTenSanPham, edGia, edSoLuong;
    String maLoaiHang = "";
    List<LoaiHang> listLoaiHang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);
        setTitle("THÊM SẢN PHẨM");
        spnLoaiHang = (Spinner) findViewById(R.id.spnLoaiHang);
        getLoaiHang();
        edMaSanPham = (EditText) findViewById(R.id.edMaSanPham);
        edTenSanPham = (EditText) findViewById(R.id.edTenSanPham);
        edGia = (EditText) findViewById(R.id.edGia);
        edSoLuong = (EditText)findViewById(R.id.edSoLuong);
        //
        spnLoaiHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiHang = listLoaiHang.get(spnLoaiHang.getSelectedItemPosition()).getMaLoaiHang();
            }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSanPham.setText(b.getString("MASANPHAM"));
            String maloaihang = b.getString("MALOAIHANG");
            edTenSanPham.setText(b.getString("TENSANPHAM"));
            edGia.setText(b.getString("GIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnLoaiHang.setSelection(checkPositionTheLoai(maloaihang));
        }
}     public void showSpinner(View view){
        sanPhamDAO = new SanPhamDAO(ThemSanPhamActivity.this);
        sanPhamDAO.getAllSanPham();
    }
    public void getLoaiHang(){
        loaiHangDAO = new LoaiHangDAO(ThemSanPhamActivity.this);
    listLoaiHang = loaiHangDAO.getAllLoaiHang();
    ArrayAdapter<LoaiHang> dataAdapter = new ArrayAdapter<LoaiHang>(this,
            android.R.layout.simple_spinner_item, listLoaiHang);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spnLoaiHang.setAdapter(dataAdapter);
    }
    public void addSanPham(View view){
        sanPhamDAO = new SanPhamDAO(ThemSanPhamActivity.this);
        SanPham sanPham = new SanPham(edMaSanPham.getText().toString(),maLoaiHang,edTenSanPham.getText().toString(),
                Double.parseDouble(edGia.getText().toString()), Integer.parseInt(edSoLuong.getText ().toString()));
        try {
            if (sanPhamDAO.inserSanPham(sanPham) > 0) {
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
    public void showSanPham(View view){
        finish();
    }
    public int checkPositionTheLoai(String strLoaiHang){
        for (int i = 0; i < listLoaiHang.size(); i++){
            if (strLoaiHang.equals(listLoaiHang.get(i).getMaLoaiHang())){
                return i;
            }
        }
        return 0;
    }
}
