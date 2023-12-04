package com.example.nh12_pro1121_md18310.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nh12_pro1121_md18310.Database.DbHelper;
import com.example.nh12_pro1121_md18310.Model.KhachHang;

import java.util.ArrayList;

public class KhachHangDao {
    DbHelper dbHelper;
    public KhachHangDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<KhachHang> getDs(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<KhachHang> list = new ArrayList<>();
        try (Cursor cursor = sqLiteDatabase.rawQuery("Select * from KHACHHANG", null);){
            if (cursor.getCount() >0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaKh(cursor.getInt(cursor.getColumnIndexOrThrow("maKh")));
                    kh.setHoTenKh(cursor.getString(cursor.getColumnIndexOrThrow("hoTenKh")));
                    kh.setNamSinhKh(cursor.getInt(cursor.getColumnIndexOrThrow("namSinhKh")));
                    kh.setSdtKhachHang(cursor.getString(cursor.getColumnIndexOrThrow("sdtKh")));
                    list.add(kh);
                    cursor.moveToNext();
                }
                sqLiteDatabase.setTransactionSuccessful();
            }
        }catch (Exception e){

        }
        return list;
    }

    public boolean insert(KhachHang kh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTenKh", kh.getHoTenKh());
        values.put("namSinhKh", kh.getNamSinhKh());
        values.put("sdtKh", kh.getSdtKhachHang());
        long row = db.insert("khachHang", null, values);
        return (row > 0);
    }

    public boolean update(KhachHang kh) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("hoTenKh", kh.getHoTenKh());
        values1.put("namSinhKh", kh.getNamSinhKh());
        values1.put("sdtKh", kh.getSdtKhachHang());
        long row = db.update("khachHang", values1, "maKh=?",
                new String[]{String.valueOf(kh.getMaKh())});
        return (row > 0);
    }

    public boolean delete(KhachHang kh) {
        if (kh.getMaKh() <= 0) {
            return false;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("khachHang", "maKh=?", new String[]{String.valueOf(kh.getMaKh())});
        return (row > 0);
    }
}
