package com.example.bluest.oauth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

//    private static final String COL_USERNAME = "username";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASS = "password";
//    private static final String COL_PASS = "password";
    public static final String databaseName = "User.db";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key, password TEXT)");
    }
    public Cursor getUserDataByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE " + COL_USERNAME + "=?", new String[]{username});
    }
//    public Cursor getUserData(String username) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.rawQuery("SELECT * FROM users WHERE " + COL_USERNAME + "=?", new String[]{username});
//    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
    }
    public boolean insertData(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("users",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where username = ?", new String[]{username});

        if (cursor.getCount() > 0){
            return  true;
        }else {
            return false;
        }
    }
    public Boolean checkPassword(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});

        if (cursor.getCount() > 0){
            return  true;
        }else {
            return false;
        }
    }
}
