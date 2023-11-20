package com.example.nh12_pro1121_md18310.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nh12_pro1121_md18310.Database.DbHelper;
import com.example.nh12_pro1121_md18310.Model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private SQLiteDatabase db;
    public AdminDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Admin obj){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tK",obj.gettK());
        contentValues.put("hoTenAd", obj.getHoTenAd());
        contentValues.put("mK", obj.getmK());

        return db.insert("admin",null,contentValues);
    }

    public long update(Admin obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("hT", obj.getHoTenAd());
        contentValues.put("mK",obj.getmK());

        return db.update("admin",contentValues,"tK = ?",new String[]{String.valueOf(obj.gettK())});
    }

    public int delete(String id) {
        return db.delete("admin","tK = ?",new String[]{String.valueOf(id)});
    }
    private List<Admin> getData(String sql, String ... selectionArgs) {
        List<Admin> lstad = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstad.add(new Admin(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            ));
        }
        return lstad;
    }
    public Admin getID (String id) {
        String sql = "SELECT * FROM admin WHERE tK = ?";
        List<Admin> lstTT = getData(sql,id);
        return lstTT.get(0);
    }

    public List<Admin> getAll() {
        String sql = "SELECT * FROM admin";
        return getData(sql);
    }
    public long checkLogin(String username,String password) {
        Cursor cursor = db.rawQuery("SELECT * FROM admin WHERE tK = ? AND mK = ?",new String[]{username,password});
        if (cursor.getCount() > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
