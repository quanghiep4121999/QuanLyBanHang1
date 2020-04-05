package com.example.quanlybanhang.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlybanhang.dao.SanPhamDAO;
import com.example.quanlybanhang.dao.HoaDonChiTietDAO;
import com.example.quanlybanhang.dao.HoaDonDAO;
import com.example.quanlybanhang.dao.NguoiDungDAO;
import com.example.quanlybanhang.dao.LoaiHangDAO;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbQuanLyBanHang";
    public static final int VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(LoaiHangDAO.SQL_LOAI_HANG);
        db.execSQL(SanPhamDAO.SQL_SAN_PHAM);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);     }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+NguoiDungDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ LoaiHangDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ SanPhamDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+HoaDonDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+HoaDonChiTietDAO.TABLE_NAME);
        onCreate(db);     }

}
