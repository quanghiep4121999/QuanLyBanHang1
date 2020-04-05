package com.example.quanlybanhang.model;

public class SanPham {
    private String maSanPham;
    private String maLoaiHang;
    private String tenSanPham;
    private double gia;
    private int soLuong;
    public SanPham() {     }

    public SanPham(String maSanPham, String maLoaiHang, String tenSanPham, double gia, int soLuong) {
        this.maSanPham = maSanPham;
        this.maLoaiHang = maLoaiHang;
        this.tenSanPham = tenSanPham;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getMaLoaiHang() {
        return maLoaiHang;
    }

    public void setMaLoaiHang(String maLoaiHang) {
        this.maLoaiHang = maLoaiHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maCa='" + maSanPham + '\'' +
                ", maTheLoai='" + maLoaiHang + '\'' +
                ", tenCa='" + tenSanPham + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                '}';     }
}
