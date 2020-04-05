package com.example.quanlybanhang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.LoaiHang;

import java.util.ArrayList;
import java.util.List;

public class LoaiHangDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "LoaiHang";
    public static final String SQL_LOAI_HANG ="CREATE TABLE LoaiHang (maloaihang text primary key, tenloaihang text, mota text, vitri int);";
    public static final String TAG = "LoaiHangDAO";
    public LoaiHangDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();     }
    //insert
    public int inserLoaiHang(LoaiHang loaiHang){
        ContentValues values = new ContentValues();
        values.put("maloaihang", loaiHang.getMaLoaiHang());
        values.put("tenloaihang", loaiHang.getTenLoaiHang());
        values.put("mota", loaiHang.getMoTa());
        values.put("vitri", loaiHang.getViTri());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;             }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());         }
        return 1;     }
        //getAllTheLoai
      public List<LoaiHang> getAllLoaiHang(){
        List<LoaiHang> dsLoaiHang = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
          c.moveToFirst();
          while (c.isAfterLast()==false){
              LoaiHang ee = new LoaiHang();
              ee.setMaLoaiHang(c.getString(0));
              ee.setTenLoaiHang(c.getString(1));
              ee.setMoTa(c.getString(2));
              ee.setViTri(c.getInt(3));
              dsLoaiHang.add(ee);
              Log.d("//=====",ee.toString());
              c.moveToNext();         }
          c.close();
          return dsLoaiHang;
      }     //update
       public int updateLoaiHang(LoaiHang loaiHang){
        ContentValues values = new ContentValues();
        values.put("maloaihang", loaiHang.getMaLoaiHang());
        values.put("tenloaihang", loaiHang.getTenLoaiHang());
        values.put("mota", loaiHang.getMoTa());
        values.put("vitri", loaiHang.getViTri());
        int result = db.update(TABLE_NAME,values,"maloaihang=?", new String[]{loaiHang.getMaLoaiHang()});
        if (result == 0){
            return -1;
        }         return 1;     }
    //delete
    public int deleteLoaiHangByID(String matheloai){
        int result = db.delete(TABLE_NAME,"maloaihang=?",new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;     }
}
