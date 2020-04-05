package com.example.quanlybanhang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "SanPham";
    public static final String SQL_SAN_PHAM ="CREATE TABLE SanPham (maSanPham text primary key, maLoaiHang text, tensanpham text, gia double, soLuong number);";
    public static final String TAG = "SanPhamDAO";
    public SanPhamDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();     }
        //insert
    public int inserSanPham(SanPham s){
        ContentValues values = new ContentValues();
        values.put("maSanPham",s.getMaSanPham());
        values.put("maLoaiHang",s.getMaLoaiHang());
        values.put("tensanpham",s.getTenSanPham());
        values.put("gia",s.getGia());
        values.put("soLuong",s.getSoLuong());
        if (checkPrimaryKey(s.getMaSanPham())){
            int result = db.update(TABLE_NAME,values,"masanpham=?", new String[]{s.getMaSanPham()});
            if (result == 0){
                return -1;
            }
        }else {
            try {
                if (db.insert(TABLE_NAME,null,values) == -1) {
                    return -1;                 }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());             }
        }         return 1;
    }
        //getAll
    public List<SanPham> getAllSanPham(){
        List<SanPham> dsSanPham = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();         while (c.isAfterLast()==false){
            SanPham s = new SanPham();
            s.setMaSanPham(c.getString(0));
            s.setMaLoaiHang(c.getString(1));
            s.setTenSanPham(c.getString(2));
            s.setGia(c.getDouble(3));
            s.setSoLuong(c.getInt(4));
            dsSanPham.add(s);
            Log.d("//=====",s.toString());
            c.moveToNext();         }
            c.close();
        return dsSanPham;
}
    //update
    public int updatseSanPham(SanPham s){
        ContentValues values = new ContentValues();
        values.put("maSanPham",s.getMaSanPham());
        values.put("maLoaiHang",s.getMaLoaiHang());
        values.put("tensanpham",s.getTenSanPham());
        values.put("gia",s.getGia());
        values.put("soLuong",s.getSoLuong());
        int result = db.update(TABLE_NAME,values,"masanpham=?", new String[]{s.getMaSanPham()});
        if (result == 0){
            return -1;         }
        return 1;     }
    //delete
    public int deleteSanPhamByID(String maCa){
        int result = db.delete(TABLE_NAME,"masanpham=?",new String[]{maCa});
        if (result == 0)
            return -1;
        return 1;     }
        //check
    public boolean checkPrimaryKey(String strPrimaryKey){
        //SELECT
        String[] columns = {"masanpham"};
        //WHERE clause
        String selection = "masanpham=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }             return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;         }
}     //check
    public SanPham checkSanPham(String strPrimaryKey){
        SanPham s = new SanPham();
        //SELECT
        String[] columns = {"masanpham"};
        //WHERE clause
        String selection = "masanpham=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast()==false){
                s.setMaSanPham(c.getString(0));
                s.setMaLoaiHang(c.getString(1));
                s.setTenSanPham(c.getString(2));
                s.setGia(c.getDouble(3));
                s.setSoLuong(c.getInt(4));
                Log.d("//=====",s.toString());
                break;             }
            c.close();
            return s;
        }catch(Exception e){
            e.printStackTrace();
            return null;         }
    }
    //getAll
        public SanPham getSanPhamByID(String maSanPham){
        SanPham s = null;
        //WHERE clause
            String selection = "masanpham=?";
            //WHERE clause arguments
            String[] selectionArgs = {maSanPham};
            Cursor c = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
            Log.d("getSanPhamByID","===>"+ c.getCount());
            c.moveToFirst();
            while (c.isAfterLast()==false){
                s = new SanPham();
                s.setMaSanPham(c.getString(0));
                s.setMaLoaiHang(c.getString(1));
                s.setTenSanPham(c.getString(2));
                s.setGia(c.getDouble(3));
                s.setSoLuong(c.getInt(4));
                break;
}         c.close();
            return s;
        }     //getAll
      public List<SanPham> getSanPhamTop10(String month){
        List<SanPham> dsSanPham = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month = "0"+month;
        }
        String sSQL = "SELECT maSanPham, SUM(soLuong) as soluong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m',HoaDon.ngayMua) = '"+month+"' " +
                "GROUP BY maSanPham ORDER BY soluong DESC LIMIT 10";
        Cursor c = db.rawQuery(sSQL, null);
          c.moveToFirst();
          while (c.isAfterLast()==false){
              Log.d("//=====",c.getString(0));
              SanPham s = new SanPham();
              s.setMaSanPham(c.getString(0));
              s.setSoLuong(c.getInt(1));
              s.setGia(0);
              s.setMaLoaiHang("");
              s.setTenSanPham("");
              dsSanPham.add(s);
              c.moveToNext();
          }         c.close();
          return dsSanPham;
      }
}
