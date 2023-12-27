package com.example.duan.DTO;

public class theLoai {
    private int maTT;
    private String tenSanPham;
    private byte[] anh;

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

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
