package com.example.smd_project;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataBase {
    private Context context;

    public DataBase(Context context) {
        this.context = context;
    }

    public void Register_User(User user){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("name", user.getName());
        contentValues.put("password", user.getPassword());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("User",null,contentValues);
        db.close();
    }

    @SuppressLint("Range")
    public String Login_User(String username, String password){
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        String result = "";
        String parameters[] = new String[2];
        parameters[0] = username;
        parameters[1] = password;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM User where username=? and password=?",parameters);
        if (cursor.moveToFirst()){
            result = cursor.getString(cursor.getColumnIndex("name"));
        }
        return result;

    }
}
