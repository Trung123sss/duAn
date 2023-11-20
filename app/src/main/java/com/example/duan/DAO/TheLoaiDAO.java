package com.example.duan.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan.DTO.theLoai;
import com.example.duan.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;

    public TheLoaiDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(theLoai obj) {
        ContentValues values = new ContentValues();
        values.put("tenSanPham", obj.getTenSanPham());
        return db.insert("Loai", null, values);
    }

    public long update(theLoai obj) {
        ContentValues values = new ContentValues();
        values.put("tenSanPham", obj.getTenSanPham());
        return db.update("Loai", values, "maTT = ?", new String[]{String.valueOf(obj.getMaTT())});
    }

    public long delete(String id) {
        return db.delete("Loai", "maTT = ?", new String[]{String.valueOf(id)});
    }

    public List<theLoai> getAll() {
        String sql = "SELECT * FROM Loai";
        return getData(sql);
    }

    public theLoai getID(String id) {
        String sql = "SELECT * FROM Loai WHERE maTT=?";
        List<theLoai> list = getData(sql, id);
        return list.get(0);
    }
    public List<theLoai>getAllName(String name){
        String sql = "SELECT * FROM Loai WHERE tenSanPham LIKE '%" + name + "%'";
        return getData(sql);
    }

    @SuppressLint("Range")
    private List<theLoai> getData(String sql, String... selectionArgs) {
        List<theLoai> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            theLoai obj = new theLoai();
            obj.setMaTT(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTT"))));
            obj.setTenSanPham(cursor.getString(cursor.getColumnIndex("tenSanPham")));

            list.add(obj);
        }
        return list;
    }

}
