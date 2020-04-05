package com.example.quanlybanhang.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG ="CREATE TABLE NguoiDung (username text primary key, password text, phone text, hoten text, chucvu text);";
    public static final String TAG = "NguoiDungDAO";
    public NguoiDungDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();     }
    //insert
    public int inserNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("username",nd.getUserName());
        values.put("password",nd.getPassword());
        values.put("phone",nd.getPhone());
        values.put("hoten",nd.getHoTen());
        values.put("chucvu",nd.getChucVu());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;             }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }         return 1;     }
        //getAll
      public List<NguoiDung> getAllNguoiDung(){
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
          while (c.isAfterLast()==false){
              NguoiDung ee = new NguoiDung();
              ee.setUserName(c.getString(0));
              ee.setPassword(c.getString(1));
              ee.setPhone(c.getString(2));
              ee.setHoTen(c.getString(3));
              ee.setChucVu(c.getString(4));
              dsNguoiDung.add(ee);
              Log.d("//=====",ee.toString());
              c.moveToNext();         }
          c.close();
          return dsNguoiDung;
      }
      //update
    public int updateNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("username",nd.getUserName());
        values.put("password",nd.getPassword());
        values.put("phone",nd.getPhone());
        values.put("hoten",nd.getHoTen());
        values.put("chucvu",nd.getChucVu());
        int result = db.update(TABLE_NAME,values,"username=?", new String[]{nd.getUserName()});
        if (result == 0){
            return -1;         }
        return 1;     }
        public int changePasswordNguoiDung(NguoiDung nd){
        ContentValues values = new ContentValues();
        values.put("username",nd.getUserName());
        values.put("password",nd.getPassword());
        int result = db.update(TABLE_NAME,values,"username=?", new String[]{nd.getUserName()});
        if (result == 0){
            return -1;
        }         return 1;     }
        public int updateInfoNguoiDung(String username, String phone, String name, String chucvu){
        ContentValues values = new ContentValues();
        values.put("phone",phone);
        values.put("hoten",name);
        values.put("chucvu",chucvu);
        int result = db.update(TABLE_NAME,values,"username=?", new String[]{username});
        if (result == 0){
            return -1;
        }         return 1;     }
    //delete
    public int deleteNguoiDungByID(String username){
        int result = db.delete(TABLE_NAME,"username=?",new String[]{username});
        if (result == 0)
            return -1;
        return 1;     }
        //check login

}
