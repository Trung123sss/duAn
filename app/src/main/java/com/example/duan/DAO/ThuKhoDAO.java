package com.example.duan.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan.DTO.ThuKho;
import com.example.duan.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class ThuKhoDAO {
    private SQLiteDatabase db;

    public ThuKhoDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long updatePass(ThuKho obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("ThuKho", values, "maTT = ?", new String[]{String.valueOf(obj.getMaTT())});
    }

    public List<ThuKho> getAll() {
        String sql = "SELECT * FROM ThuKho";
        return getData(sql);
    }

    public ThuKho getID(String id) {
        String sql = "SELECT * FROM ThuKho WHERE maTT=?";
        List<ThuKho> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM ThuKho WHERE maTT=? AND matKhau=?";
        List<ThuKho> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
    public int checkLogins(String id) {
        String sql = "SELECT * FROM ThuKho WHERE maTT=? ";
        List<ThuKho> list = getData(sql, id);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<ThuKho> getData(String sql, String... selectionArgs) {
        List<ThuKho> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            ThuKho obj = new ThuKho();
            obj.setMaTT(cursor.getString(cursor.getColumnIndex("maTT")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            list.add(obj);
        }
        return list;
    }
}
