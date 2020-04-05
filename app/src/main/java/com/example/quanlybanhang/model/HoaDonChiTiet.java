package com.example.quanlybanhang.model;

public class HoaDonChiTiet {
    private int maHDCT;
    private HoaDon hoaDon;
    private SanPham sanPham;
    private int soLuongMua;
    public HoaDonChiTiet() {

    }
    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, SanPham sanPham, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.soLuongMua = soLuongMua;     }
    public int getMaHDCT() {
        return maHDCT;     }
    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;     }
    public HoaDon getHoaDon() {
        return hoaDon;     }
    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;     }
    public SanPham getSanPham() {
        return sanPham;     }
    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;     }
    public int getSoLuongMua() {
        return soLuongMua;     }
    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;     }
    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + hoaDon.toString() +
                ", sanPham=" + sanPham.toString() +
                ", soLuongMua=" + soLuongMua +
                '}';     }
}
