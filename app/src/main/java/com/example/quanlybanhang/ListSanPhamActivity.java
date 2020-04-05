package com.example.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.adapter.SanPhamAdapter;
import com.example.quanlybanhang.dao.SanPhamDAO;
import com.example.quanlybanhang.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ListSanPhamActivity extends AppCompatActivity {
    public static List<SanPham> dsSanPham = new ArrayList<SanPham>();
    ListView lvSanPham;
    SanPhamAdapter adapter = null;
    SanPhamDAO sanPhamDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_san_pham);
        setTitle("QUẢN LÝ SẢN PHẨM");
        lvSanPham = (ListView) findViewById(R.id.lvSanPham);
        sanPhamDAO = new SanPhamDAO(ListSanPhamActivity.this);
        dsSanPham = sanPhamDAO.getAllSanPham();
        adapter = new SanPhamAdapter(this, dsSanPham);
        lvSanPham.setAdapter(adapter);
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sanPham = (SanPham) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListSanPhamActivity.this, ThemSanPhamActivity.class);
                Bundle b = new Bundle();
                b.putString("MASANPHAM", sanPham.getMaSanPham());
                b.putString("MALOAIHANG", sanPham.getMaLoaiHang());
                b.putString("TENSANPHAM", sanPham.getTenSanPham());
                b.putString("GIA", String.valueOf(sanPham.getGia()));
                b.putString("SOLUONG", String.valueOf(sanPham.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        // TextFilter
        lvSanPham.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearchSanPham);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void afterTextChanged(Editable s) {

        }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_san_pham, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListSanPhamActivity.this, ThemSanPhamActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        dsSanPham.clear();
        dsSanPham = sanPhamDAO.getAllSanPham();
        adapter.changeDataset(sanPhamDAO.getAllSanPham()); }


}

