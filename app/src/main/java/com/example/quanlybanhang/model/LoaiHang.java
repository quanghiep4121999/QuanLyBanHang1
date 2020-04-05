package com.example.quanlybanhang.model;

public class LoaiHang {
    private String maLoaiHang;
    private String tenLoaiHang;
    private String moTa;
    private int viTri;
    public LoaiHang() {

    }
    public LoaiHang(String maLoaiHang, String tenLoaiHang, String moTa, int viTri) {
        this.maLoaiHang = maLoaiHang;
        this.tenLoaiHang = tenLoaiHang;
        this.moTa = moTa;
        this.viTri = viTri;
    }

    public String getMaLoaiHang() {
        return maLoaiHang;
     }
    public void setMaLoaiHang(String maLoaiHang) {
        this.maLoaiHang = maLoaiHang;     }
    public String getTenLoaiHang() {
        return tenLoaiHang;     }
    public void setTenLoaiHang(String tenLoaiHang) {
        this.tenLoaiHang = tenLoaiHang;     }
    public String getMoTa() {
        return moTa;     }
    public void setMoTa(String moTa) {
        this.moTa = moTa;     }
    public int getViTri() {
        return viTri;     }
    public void setViTri(int viTri) {
        this.viTri = viTri;     }
    @Override
    public String toString() {
        return getMaLoaiHang()+" | "+getTenLoaiHang();     }
}

