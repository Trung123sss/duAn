package com.example.duan.DTO;

public class Hoadonchitiet {
    int ID;
    int maSP;
    int soLuong;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Hoadonchitiet(int ID, int maSP, int soLuong) {
        this.ID = ID;
        this.maSP = maSP;
        this.soLuong = soLuong;
    }

    public Hoadonchitiet() {
    }
}
