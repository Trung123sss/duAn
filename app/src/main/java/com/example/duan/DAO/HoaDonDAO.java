package com.example.duan.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan.DTO.HoaDon;

import com.example.duan.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db, dc;

    public HoaDonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        dc = dbHelper.getReadableDatabase();
    }

    public long insert(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("MaTV", obj.getMaTV());
        values.put("ngay", obj.getNgay());
        values.put("loai", obj.getLoai());

        return db.insert("Hoadon", null, values);
    }

    public long update(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("MaTV", obj.getMaTV());
        values.put("ngay", obj.getNgay());
        values.put("loai", obj.getLoai());

        return db.update("Hoadon", values, "MaHD = ?", new String[]{String.valueOf(obj.getMaHD())});
    }

    public long delete(String id) {
        return db.delete("Hoadon", "MaHD = ?", new String[]{String.valueOf(id)});
    }

    public List<HoaDon> getAll() {
        String sql = "SELECT * FROM Hoadon";
        return getData(sql);
    }

    public HoaDon getID(String id) {
        String sql = "SELECT * FROM Hoadon WHERE MaHD=?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    public int idNew() {
        int id = -1;
        try {
            String query = "SELECT  MaHD FROM HoaDon ORDER BY MaHD DESC LIMIT 1";

            Cursor cursor = dc.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }
            cursor.close();

        } catch (Exception e) {
            id = -1;

        }
        return id;
    }

    @SuppressLint("Range")
    private List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon obj = new HoaDon();
            obj.setMaHD(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MaHD"))));
            obj.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV"))));
            obj.setLoai(cursor.getString(cursor.getColumnIndex("loai")));
            obj.setNgay(cursor.getString(cursor.getColumnIndex("ngay")));
            list.add(obj);
        }
        return list;
    }

    //    @SuppressLint("Range")
    @SuppressLint("Range")
    public List<HoaDon> getBYXUAT(String ngay, String loai) {
        List<HoaDon> hoaDons = new ArrayList<>();
        String selectQuery = "SELECT * FROM Hoadon WHERE ngay = ? AND loai = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{ngay, loai});
        if (cursor.moveToFirst()) {
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(cursor.getInt(cursor.getColumnIndex("MaHD")));
                hoaDon.setMaTV(cursor.getInt(cursor.getColumnIndex("maTV")));
                hoaDon.setNgay(cursor.getString(cursor.getColumnIndex("ngay")));
                hoaDon.setLoai(cursor.getString(cursor.getColumnIndex("loai")));

                hoaDons.add(hoaDon);
            } while (cursor.moveToNext());
        }
        return hoaDons;
    }
    @SuppressLint("Range")
    public List<HoaDon> getBYTrangThai(String tuNgay, String denNgay, String loai) {
        List<HoaDon> hoaDons = new ArrayList<>();
        String selectQuery = "SELECT * FROM Hoadon WHERE ngay BETWEEN ? AND ? AND loai = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{tuNgay, denNgay, loai});

        if (cursor.moveToFirst()) {
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHD(cursor.getInt(cursor.getColumnIndex("MaHD")));
                hoaDon.setMaTV(cursor.getInt(cursor.getColumnIndex("maTV")));
                hoaDon.setNgay(cursor.getString(cursor.getColumnIndex("ngay")));
                hoaDon.setLoai(cursor.getString(cursor.getColumnIndex("loai")));
                hoaDons.add(hoaDon);
            } while (cursor.moveToNext());
        }


        return hoaDons;
    }


    public int getSoHoaDon(String ngay , String loai) {
        int tongHoaDon = 0;
        try {
            String sql = "SELECT COUNT(*) FROM Hoadon WHERE ngay = ? AND loai=?";
            Cursor cursor = db.rawQuery(sql, new String[]{ngay, loai});
            if (cursor.moveToFirst()) {
                tongHoaDon = cursor.getInt(0);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongHoaDon;
    }
}
