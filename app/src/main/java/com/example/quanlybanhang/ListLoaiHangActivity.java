package com.example.quanlybanhang;

import android.content.Intent;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlybanhang.adapter.LoaiHangAdapter;
import com.example.quanlybanhang.dao.LoaiHangDAO;
import com.example.quanlybanhang.model.LoaiHang;

import java.util.ArrayList;
import java.util.List;

public class ListLoaiHangActivity extends AppCompatActivity {
    public static List<LoaiHang> dsLoaiHang = new ArrayList<>();
    ListView lvLoaiHang;
    LoaiHangAdapter adapter = null;
    LoaiHangDAO loaiHangDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("LOẠI HÀNG");
        setContentView(R.layout.activity_list_loai_hang);
        lvLoaiHang = (ListView) findViewById(R.id.lvLoaiHang);
        registerForContextMenu(lvLoaiHang);
        loaiHangDAO = new LoaiHangDAO(ListLoaiHangActivity.this);
        dsLoaiHang = loaiHangDAO.getAllLoaiHang();
        adapter = new LoaiHangAdapter(this, dsLoaiHang);
        lvLoaiHang.setAdapter(adapter);
        lvLoaiHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListLoaiHangActivity.this, LoaiHangActivity.class);
                Bundle b = new Bundle();
                b.putString("MALOAIHANG", dsLoaiHang.get(position).getMaLoaiHang());
                b.putString("TENLOAIHANG", dsLoaiHang.get(position).getTenLoaiHang());
                b.putString("MOTA", dsLoaiHang.get(position).getMoTa());
                b.putString("VITRI", String.valueOf(dsLoaiHang.get(position).getViTri()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_loai_hang, menu);
        return true;     }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListLoaiHangActivity.this, LoaiHangActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        dsLoaiHang.clear();
        dsLoaiHang = loaiHangDAO.getAllLoaiHang();
        adapter.changeDataset(dsLoaiHang);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn thông tin");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_ctx_edit:
                Intent intent1 = new Intent(ListLoaiHangActivity.this, LoaiHangActivity.class);
                startActivity(intent1);
                return(true);
                case R.id.menu_ctx_del:
                    Intent intent2 = new Intent(ListLoaiHangActivity.this, LoaiHangActivity.class);
                    startActivity(intent2);
                    return(true);
        }
        return super.onContextItemSelected(item);
    }
}

