package com.example.nh12_pro1121_md18310.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nh12_pro1121_md18310.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class DoanhThuDao {

    SQLiteDatabase db;

private Context context;

    public DoanhThuDao (Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int getDoanhThu() {
        String sqlDoanhthu = "SELECT SUM(tongtien) as doanhThu FROM hoaDon";
        List<Integer> lstDT = new ArrayList<>();
        Cursor cursor = db.rawQuery(sqlDoanhthu,null);
        while (cursor.moveToNext()) {
            try {
                lstDT.add(Integer.parseInt(cursor.getString(0)));
            } catch (Exception e) {
                lstDT.add(0);
            }
        }
        return lstDT.get(0);
    }
}
