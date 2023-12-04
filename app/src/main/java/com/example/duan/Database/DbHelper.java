package com.example.duan.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DTDT";
    private static final int DB_VERSION = 9;


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_THU_KHO = "" +
                "CREATE TABLE ThuKho(\n" +
                "maTT text PRIMARY KEY,\n" +
                "hoTen text NOT NULL,\n" +
                "matKhau text NOT NULL\n" +
                ")";
        db.execSQL(CREATE_TABLE_THU_KHO);

        String CREATE_TABLE_THANH_VIEN ="" +
                "CREATE TABLE ThanhVien(\n" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "hoTen text NOT NULL,\n" +
                "namSinh text NOT NULL,\n" +
                "taiKhoan text NOT NULL,\n" +
                "matKhau text NOT NULL\n" +
                ")";
        db.execSQL(CREATE_TABLE_THANH_VIEN);
        String CREATE_TABLE_LOAI = "create table Loai(" +
                "maTT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSanPham TEXT NOT NULL\n "+
                ")";
        db.execSQL(CREATE_TABLE_LOAI);
        String CREATE_TABLE_SAN_PHAM = "create table SanPham(" +
                "maSP INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSP TEXT NOT NULL, " +
                "gia INTEGER NOT NULL, " +
                "maTT INTEGER REFERENCES Loai(maTT),"+
                "soLuong INTEGER NOT NULL,"+
                "moTa TEXT "+
                ")";
        db.execSQL(CREATE_TABLE_SAN_PHAM);

        // data mẫu
        db.execSQL("INSERT INTO ThuKho VALUES('admin','Admin','admin')," +
                "('trung','Đinh Trần Đức Trung','123')");
        db.execSQL("INSERT INTO ThanhVien VALUES(1,'Đoàn Ngọc Mai','2000','makidt','123')," +
                "(2,'Phạm Thị Hân','2001','han12','123')");
        db.execSQL("INSERT INTO Loai VALUES(1,'Đồ ăn'),(2,'Quần Áo'),(3,'Điện Tử')");

        db.execSQL("INSERT INTO SanPham VALUES(1,'Ipone',100,'2',2000,'15')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("drop table if exists ThuKho");
            db.execSQL("drop table if exists ThanhVien");
            db.execSQL("drop table if exists Loai");
            db.execSQL("drop table if exists SanPham");

            onCreate(db);
        }
    }
}
