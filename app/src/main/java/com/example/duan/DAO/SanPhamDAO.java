package com.example.duan.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan.DTO.SanPham;
import com.example.duan.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;


    public SanPhamDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public SanPhamDAO() {

    }

    public long insert(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("tenSP", obj.getTenSp());
        values.put("gia", obj.getGia());
        values.put("maTT", obj.getMaTT());
        values.put("soLuong", obj.getSoLuong());
        values.put("moTa", obj.getMoTa());
        return db.insert("SanPham", null, values);
    }

    public long update(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("tenSP", obj.getTenSp());
        values.put("gia", obj.getGia());
        values.put("maTT", obj.getMaTT());
        values.put("soLuong", obj.getSoLuong());
        values.put("moTa", obj.getMoTa());
        return db.update("SanPham", values, "maSP = ?", new String[]{String.valueOf(obj.getMaSP())});
    }
   public long updateSL(SanPham obj ){
        ContentValues values = new ContentValues();
        values.put("soLuong", obj.getSoLuong());
        return db.update("SanPham", values, "maSP = ?", new String[]{String.valueOf(obj.getMaSP())});
   }

    public long delete(String id) {
        return db.delete("SanPham", "maSP = ?", new String[]{String.valueOf(id)});
    }

    public List<SanPham> getAll() {
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }

    public SanPham getID(String id) {
        String sql = "SELECT * FROM SanPham WHERE maSP=?";
        List<SanPham> list = getData(sql, new String[]{id});
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @SuppressLint("Range")
    private List<SanPham> getData(String sql, String... selectionArgs) {
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            SanPham obj = new SanPham();
            obj.setMaSP(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSP"))));
            obj.setTenSp(cursor.getString(cursor.getColumnIndex("tenSP")));
            obj.setGia(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gia"))));
            obj.setMaTT(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTT"))));
            obj.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            obj.setMoTa(cursor.getString(cursor.getColumnIndex("moTa")));
            list.add(obj);
        }
        return list;
    }

}
