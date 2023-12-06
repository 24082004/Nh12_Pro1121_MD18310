package com.example.nh12_pro1121_md18310.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nh12_pro1121_md18310.Database.DbHelper;
import com.example.nh12_pro1121_md18310.Model.LoaiSanPham;

import java.util.ArrayList;
import java.util.Collection;

public class LoaiSanPhamDao {
        DbHelper dbHelper;
        public LoaiSanPhamDao(Context context){
            dbHelper = new DbHelper(context);
        }
        public Collection<? extends LoaiSanPham> getDs(){
            SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
            ArrayList<LoaiSanPham> list = new ArrayList<>();
            try (Cursor cursor = sqLiteDatabase.rawQuery("Select * from loaiSanPham", null);){
                if (cursor.getCount() >0){
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        LoaiSanPham lsp = new LoaiSanPham();
                        lsp.setMaLoai(cursor.getInt(cursor.getColumnIndexOrThrow("maLoai")));
                        lsp.setTenLoai(cursor.getString(cursor.getColumnIndexOrThrow("tenLoai")));
                        list.add(lsp);
                        cursor.moveToNext();
                    }
                    sqLiteDatabase.setTransactionSuccessful();
                }
            }catch (Exception e){

            }
            return list;
        }

        public boolean insert(LoaiSanPham lsp) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("tenLoai", lsp.getTenLoai());
            long row = db.insert("loaiSanPham", null, values);
            return (row > 0);
        }

        public boolean update(LoaiSanPham lsp) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values1 = new ContentValues();
            values1.put("tenLoai", lsp.getTenLoai());
            long row = db.update("loaiSanPham", values1, "maLoai=?",
                    new String[]{String.valueOf(lsp.getMaLoai())});
            return (row > 0);
        }

        public boolean delete(LoaiSanPham lsp) {
            if (lsp.getMaLoai() <= 0) {
                return false;
            }
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            long row = db.delete("loaiSanPham", "maLoai=?", new String[]{String.valueOf(lsp.getMaLoai())});
            return (row > 0);
        }
    }

