package com.example.duan.DTO;

public class ThanhVien {
    private int maTV;
    private String hoTen;
    private String namSinh;
    private String taiKhoan;

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    private String matKhau;

    public ThanhVien(int maTV, String hoTen, String namSinh, String taiKhoan, String matKhau) {
        this.maTV = maTV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public ThanhVien() {
    }
}
