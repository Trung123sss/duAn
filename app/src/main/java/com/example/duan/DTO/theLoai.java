package com.example.duan.DTO;

public class theLoai {
    private int maTT;
    private String tenSanPham;

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public theLoai() {
    }

    public theLoai(int maTT, String tenSanPham) {
        this.maTT = maTT;
        this.tenSanPham = tenSanPham;
    }
}
