package com.example.duan.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan.DTO.ThanhVien;
import com.example.duan.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        values.put("taiKhoan", obj.getTaiKhoan());
        values.put("matKhau", obj.getMatKhau());
        return db.insert("ThanhVien", null, values);
    }

    public long update(ThanhVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        values.put("taiKhoan", obj.getTaiKhoan());
        values.put("matKhau", obj.getMatKhau());
        return db.update("ThanhVien", values, "maTV = ?", new String[]{String.valueOf(obj.getMaTV())});
    }

    public long delete(String id) {
        return db.delete("ThanhVien", "maTV = ?", new String[]{String.valueOf(id)});
    }

    public List<ThanhVien> getAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(int id) {
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql, String.valueOf(id));
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<ThanhVien> getData(String sql, String... selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThanhVien obj = new ThanhVien();
            obj.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV"))));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setNamSinh(cursor.getString(cursor.getColumnIndex("namSinh")));
            obj.setTaiKhoan(cursor.getString(cursor.getColumnIndex("taiKhoan")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            Log.i("//==", obj.toString());
            list.add(obj);
        }
        return list;
    }

    public ThanhVien checkLogin(String id, String password) {
        String sql = "SELECT * FROM ThanhVien WHERE taiKhoan=? AND matKhau=?";
        List<ThanhVien> list = getData(sql, id, password);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
