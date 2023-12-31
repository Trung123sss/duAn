package com.example.duan.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.Hoadonchitiet;
import com.example.duan.DTO.SanPham;
import com.example.duan.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class HoadonchitietDAO {
    private SQLiteDatabase db;

    public HoadonchitietDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Hoadonchitiet obj) {
        ContentValues values = new ContentValues();
        values.put("ID", obj.getID());
        values.put("maSP", obj.getMaSP());
        values.put("soLuong", obj.getSoLuong());
        return db.insert("Hoadonchitiet", null, values);
    }

    public long update(Hoadonchitiet obj) {
        ContentValues values = new ContentValues();
        values.put("maSP", obj.getMaSP());
        values.put("soLuong", obj.getSoLuong());
        return db.update("Hoadonchitiet", values, "ID = ?", new String[]{String.valueOf(obj.getMaSP())});
    }


    public List<Hoadonchitiet> getID(int id) {
        String sql = "SELECT * FROM Hoadonchitiet  where ID  = ?";
        List<Hoadonchitiet> list = getData(sql, String.valueOf(id));
        return list;
    }


    public int getdoanhthu(String tuNgay, String denNgay, String loai) {
        int tongTien = 0;
        try {
            String sql = "SELECT SUM(SanPham.gia * Hoadonchitiet.soLuong) " +
                    "FROM Hoadonchitiet " +
                    "JOIN SanPham ON Hoadonchitiet.maSP = SanPham.maSP " +
                    "JOIN Hoadon ON Hoadonchitiet.ID = Hoadon.MaHD " +
                    "WHERE Hoadon.ngay BETWEEN ? AND ? " +
                    "AND Hoadon.loai = ?";
            Cursor cursor = db.rawQuery(sql, new String[]{tuNgay,denNgay, loai});
            if (cursor.moveToFirst()) {
                tongTien = cursor.getInt(0);}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongTien;
    }


    @SuppressLint("Range")
    private List<Hoadonchitiet> getData(String sql, String... selectionArgs) {
        List<Hoadonchitiet> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Hoadonchitiet obj = new Hoadonchitiet();
            obj.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID"))));
            obj.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP"))));
            obj.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(obj);
        }
        return list;
    }
}
