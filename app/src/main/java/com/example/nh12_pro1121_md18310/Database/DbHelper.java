package com.example.nh12_pro1121_md18310.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "QLBHNT1";
    static final int dbVersion = 1;
    public DbHelper(Context context) {super(context, dbName, null, dbVersion);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Bảng admin
        String createTableAdmin =
                "Create table admin (" +
                        "tK TEXT PRIMARY KEY, " +
                        "hoTenAd TEXT NOT NULL, " +
                        "mK TEXT NOT NULL)";
        db.execSQL(createTableAdmin);
        //Bảng nhân viên
        String createTableNhanVien =
                "Create table nhanVien (" +
                        "maNv INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "hoTenNv TEXT NOT NULL, " +
                        "namSinhNv INTEGER NOT NULL, " +
                        "sdtNv INTEGER NOT NULL, " +
                        "emailNv TEXT NOT NULL)";
        db.execSQL(createTableNhanVien);
        //Bảng khách hàng
        String createTableKhachHang =
                "Create table khachHang (" +
                        "maKh INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "hoTenKh TEXT NOT NULL, " +
                        "namSinhKh INTEGER NOT NULL, " +
                        "sdtKh INTEGER NOT NULL)";
        db.execSQL(createTableKhachHang);
        //Bảng hóa đơn
        String createTableHoaDon =
                "Create table hoaDon (" +
                        "maHd INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenSanPham TEXT NOT NULL, " +
                        "soLuong INTEGER NOT NULL, " +
                        "tongtien INTEGER NOT NULL, " +
                        "trangThaiTT TEXT NOT NULL)";
        db.execSQL(createTableHoaDon);
        //Bảng sản phẩm
        String createTableSanPham =
                "Create table sanPham (" +
                        "maSP INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenlLoai TEXT NOT NULL, " +
                        "tenSanPham TEXT NOT NULL, " +
                        "donGia INTEGER NOT NULL)";
        db.execSQL(createTableSanPham);
        //Bảng loại sản phẩm
        String createTableLoaiSanPham =
                "Create table loaiSanPham (" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSanPham);

        String add_admin = "INSERT INTO admin VALUES" +
                "('admin','loi','admin004')";
        db.execSQL(add_admin);

        String add_nhanvien = "INSERT INTO nhanVien VALUES" +
                "(0,'Nguyễn Đắc Lợi',2004,'0358759165','loindph36082')," +
                "(1,'Bùi Quang Minh',2004,'0123456789','mangbqph')," +
                "(2,'Dương Công Chức',2004,'0987654321','chucdcph')";
        db.execSQL(add_nhanvien);

        String add_khachhang = "INSERT INTO khachHang VALUES" +
                "(0,'Nguyễn Đắc Lợi',2004,'0358759165')," +
                "(1,'Bùi Quang Minh',2004,'0987654321')," +
                "(2,'Dương Công Chức',2004,'0123456789')";
        db.execSQL(add_khachhang);

        String add_sanpham = "INSERT INTO sanPham VALUES" +
                "(0,'Bàn','Bàn ghỗ',1200000)," +
                "(1,'Ghế','Ghế gỗ',100000)," +
                "(2,'Tủ','Tủ ghỗ',2000000)";
        db.execSQL(add_sanpham);

        String add_loaisanpham = "INSERT INTO loaiSanPham VALUES" +
                "(0,'Bàn')," +
                "(1,'Ghế')," +
                "(2,'Tủ')";
        db.execSQL(add_loaisanpham);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTablepAdmin = "Drop table if exists admin";
        String dropTablepnhanVien = "Drop table if exists nhanVien";
        String dropTablepkhacHang = "Drop table if exists khachHang";
        String dropTablephoaDon = "Drop table if exists hoaDon";
        String dropTablepsanPham = "Drop table if exists sanPham";
        String dropTableploaiSanPham = "Drop table if exists loaiSanPham";

        if(oldVersion != newVersion){
            db.execSQL(dropTablepAdmin);
            db.execSQL(dropTablepnhanVien);
            db.execSQL(dropTablepkhacHang);
            db.execSQL(dropTablephoaDon);
            db.execSQL(dropTablepsanPham);
            db.execSQL(dropTableploaiSanPham);
        }
    }
}
