package com.example.nh12_pro1121_md18310.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nh12_pro1121_md18310.Database.DbHelper;
import com.example.nh12_pro1121_md18310.Model.KhachHang;
import com.example.nh12_pro1121_md18310.Model.NhanVien;
import com.example.nh12_pro1121_md18310.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {
    DbHelper dbHelper;
    private SQLiteDatabase db;
    public SanPhamDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<SanPham> getDs(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<SanPham> list = new ArrayList<>();
        try (Cursor cursor = sqLiteDatabase.rawQuery("Select * from sanPham", null);){
            if (cursor.getCount() >0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    SanPham sp = new SanPham();
                    sp.setMaSanPham(cursor.getInt(cursor.getColumnIndexOrThrow("maSP")));
                    sp.setTenlLoaisp(cursor.getString(cursor.getColumnIndexOrThrow("tenlLoai")));
                    sp.setTenSanPham(cursor.getString(cursor.getColumnIndexOrThrow("tenSanPham")));
                    sp.setDonGia(cursor.getInt(cursor.getColumnIndexOrThrow("donGia")));
                    list.add(sp);
                    cursor.moveToNext();
                }
                sqLiteDatabase.setTransactionSuccessful();
            }
        }catch (Exception e){

        }
        return list;
    }

    public boolean insert(SanPham sp) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenlLoai", sp.getTenlLoaisp());
        values.put("tenSanPham", sp.getTenSanPham());
        values.put("donGia", sp.getDonGia());
        long row = db.insert("sanPham", null, values);
        return (row > 0);
    }

    public boolean update(SanPham sp) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("tenlLoai", sp.getTenlLoaisp());
        values1.put("tenSanPham", sp.getTenSanPham());
        values1.put("donGia", sp.getDonGia());
        long row = db.update("sanPham", values1, "maSP=?",
                new String[]{String.valueOf(sp.getMaSanPham())});
        return (row > 0);
    }

    public boolean delete(SanPham sp) {
        if (sp.getMaSanPham() <= 0) {
            return false;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("sanPham", "maSP=?", new String[]{String.valueOf(sp.getMaSanPham())});
        return (row > 0);
    }
    private List<SanPham> getData(String sql, String ... selectionArgs) {
        List<SanPham> lstSach = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstSach.add(new SanPham(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3))
            ));
        }
        return lstSach;
    }
    public SanPham getID (String id) {
        String sql = "SELECT * FROM Sach WHERE maSach = ?";
        List<SanPham> lstTT = getData(sql,id);
        return lstTT.get(0);
    }

}
