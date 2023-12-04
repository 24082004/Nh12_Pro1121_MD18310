package com.example.nh12_pro1121_md18310.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nh12_pro1121_md18310.Database.DbHelper;
import com.example.nh12_pro1121_md18310.Model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    DbHelper dbHelper;

    public HoaDonDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<HoaDon> getDshd(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<HoaDon> list = new ArrayList<>();
        try (Cursor cursor = sqLiteDatabase.rawQuery("Select * from HOADON", null);){
            if (cursor.getCount() >0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    HoaDon hd = new HoaDon();
                    hd.setMaHoaDon(cursor.getInt(cursor.getColumnIndexOrThrow("maHd")));
                    hd.setTenSanPham(cursor.getString(cursor.getColumnIndexOrThrow("tenSanPham")));
                    hd.setSoLuong(cursor.getInt(cursor.getColumnIndexOrThrow("tongtien")));
                    hd.setTongTien(cursor.getInt(cursor.getColumnIndexOrThrow("soLuong")));
                    hd.setTrangThaiTT(cursor.getString(cursor.getColumnIndexOrThrow("trangThaiTT")));
                    list.add(hd);
                    cursor.moveToNext();
                }
                sqLiteDatabase.setTransactionSuccessful();
            }
        }catch (Exception e){

        }
        return list;
    }

    public boolean insert(HoaDon hd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSanPham", hd.getTenSanPham());
        values.put("tongtien", hd.getTongTien());
        values.put("soLuong", hd.getSoLuong());
        values.put("trangThaiTT", hd.getTrangThaiTT());
        long row = db.insert("hoaDon", null, values);
        return (row > 0);
    }

    public boolean update(HoaDon hd) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("tenSanPham", hd.getTenSanPham());
        values1.put("tongtien", hd.getTongTien());
        values1.put("soLuong", hd.getSoLuong());
        values1.put("trangThaiTT", hd.getTrangThaiTT());
        long row = db.update("hoaDon", values1, "maHd=?",
                new String[]{String.valueOf(hd.getMaHoaDon())});
        return (row > 0);
    }

    public boolean delete(HoaDon hd) {
        if (hd.getMaHoaDon() <= 0) {
            return false;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("hoaDon", "maHd=?", new String[]{String.valueOf(hd.getMaHoaDon())});
        return (row > 0);
    }
}
