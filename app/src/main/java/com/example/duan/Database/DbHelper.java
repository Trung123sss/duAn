package com.example.duan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DTDT";
    private static final int DB_VERSION = 2;
    static final String CREATE_TABLE_THU_KHO = "" +
            "CREATE TABLE ThuKho(\n" +
            "maTT text PRIMARY KEY,\n" +
            "hoTen text NOT NULL,\n" +
            "matKhau text NOT NULL\n" +
            ")";
    static final String CREATE_TABLE_THANH_VIEN ="" +
            "CREATE TABLE ThanhVien(\n" +
            "maTV INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "hoTen text NOT NULL,\n" +
            "namSinh text NOT NULL,\n" +
            "taiKhoan text NOT NULL,\n" +
            "matKhau text NOT NULL\n" +
            ")";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
