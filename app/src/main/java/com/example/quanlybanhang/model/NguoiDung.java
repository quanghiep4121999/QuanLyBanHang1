package com.example.quanlybanhang.model;

public class NguoiDung {
    private String userName;
    private String password;
    private String phone;
    private String hoTen;
    private String chucVu;
    public NguoiDung() {

    }
    public NguoiDung(String userName, String password, String phone, String hoTen, String chucVu) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.hoTen = hoTen;
        this.chucVu = chucVu;

    }
    public String getUserName() {
        return userName;     }
    public void setUserName(String userName) {
        this.userName = userName;     }
    public String getPassword() {         return password;     }
    public void setPassword(String password) {         this.password = password;     }
    public String getPhone() {         return phone;     }
    public void setPhone(String phone) {         this.phone = phone;     }
    public String getHoTen() {         return hoTen;     }
    public void setHoTen(String hoTen) {         this.hoTen = hoTen;     }
    public String getChucVu(){
        return chucVu;
    }
    public void setChucVu(String chucVu){
        this.chucVu = chucVu;
    }
    @Override
    public String toString() {
        return "NguoiDung{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", chucVu='"+ chucVu + '\'' +
                '}';     }

}
