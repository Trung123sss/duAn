package com.example.duan.DTO;

public class SanPham {
    private int maSP;
    private String tenSp;
    private int gia;
    private int maTT;
    private int soLuong;

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public SanPham() {
    }

    public SanPham(int maSP, String tenSp, int gia, int maTT, int soLuong, String moTa) {
        this.maSP = maSP;
        this.tenSp = tenSp;
        this.gia = gia;
        this.maTT = maTT;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    private String moTa;
}
