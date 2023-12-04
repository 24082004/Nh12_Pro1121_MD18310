package com.example.nh12_pro1121_md18310.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nh12_pro1121_md18310.Database.DbHelper;
import com.example.nh12_pro1121_md18310.Model.NhanVien;

import java.util.ArrayList;

public class NhanVienDao {
    DbHelper dbHelper;
    public NhanVienDao(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<NhanVien> getDs(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<NhanVien> list = new ArrayList<>();
        try (Cursor cursor = sqLiteDatabase.rawQuery("Select * from NHANVIEN", null);){
            if (cursor.getCount() >0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNv(cursor.getInt(cursor.getColumnIndexOrThrow("maNv")));
                    nv.setHoTenNv(cursor.getString(cursor.getColumnIndexOrThrow("hoTenNv")));
                    nv.setNamSinhNv(cursor.getInt(cursor.getColumnIndexOrThrow("namSinhNv")));
                    nv.setSdtNv(cursor.getString(cursor.getColumnIndexOrThrow("sdtNv")));
                    nv.setEmailNv(cursor.getString(cursor.getColumnIndexOrThrow("emailNv")));
                    list.add(nv);
                    cursor.moveToNext();
                }
                sqLiteDatabase.setTransactionSuccessful();
            }
        }catch (Exception e){

        }
        return list;
    }

    public boolean insert(NhanVien nv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTenNv", nv.getHoTenNv());
        values.put("namSinhNv", nv.getNamSinhNv());
        values.put("sdtNv", nv.getSdtNv());
        values.put("emailNv", nv.getEmailNv());
        long row = db.insert("nhanVien", null, values);
        return (row > 0);
    }

    public boolean update(NhanVien nv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("hoTenNv", nv.getHoTenNv());
        values1.put("namSinhNv", nv.getNamSinhNv());
        values1.put("sdtNv", nv.getSdtNv());
        values1.put("emailNv", nv.getEmailNv());
        long row = db.update("nhanVien", values1, "maNv=?",
                new String[]{String.valueOf(nv.getMaNv())});
        return (row > 0);
    }

    public boolean delete(NhanVien sp) {
        if (sp.getMaNv() <= 0) {
            return false;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("nhanVien", "maNv=?", new String[]{String.valueOf(sp.getMaNv())});
        return (row > 0);
    }
}
