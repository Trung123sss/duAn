package com.example.duan.DTO;

public class HoaDon {
   int MaHD;
   int maTV;
   String TenTv;

    public HoaDon(String tenTv) {
        TenTv = tenTv;
    }

    public String getTenTv() {
        return TenTv;
    }

    public void setTenTv(String tenTv) {
        TenTv = tenTv;
    }

    public HoaDon(int maHD, int maTV, String loai, String ngay) {
        MaHD = maHD;
        this.maTV = maTV;
        this.loai = loai;
        this.ngay = ngay;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public HoaDon() {
    }

    String loai;
   String ngay;
}
