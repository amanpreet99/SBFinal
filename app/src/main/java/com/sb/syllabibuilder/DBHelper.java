package com.sb.syllabibuilder;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME="users";
    public DBHelper(@Nullable Context context) {
        super(context, "users",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        //Mydb.execSQL("CREATE TABLE users(email TEXT primary key, password TEXT, department TEXT)");
        Mydb.execSQL("CREATE TABLE users(email TEXT unique, password TEXT, department TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("drop Table if exists users");

    }

    public boolean insertData(String email, String password, String department){
        SQLiteDatabase Mydb= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password", password);
        contentValues.put("department", department);
        long result= Mydb.insert("users",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public boolean checkemail(String email){
        SQLiteDatabase Mydb= this.getWritableDatabase();
        Cursor cursor= Mydb.rawQuery("Select * from users where email=?",new String[] {email});
        if(cursor.getCount()>0) return true;
        else
            return false;
    }
    public boolean checkpass(String email, String password){
        SQLiteDatabase Mydb= this.getWritableDatabase();
        Cursor cursor= Mydb.rawQuery("Select * from users where email=? and password=?",new String[] {email,password});
        if(cursor.getCount()>0) {
            return true;
        }
        else
            return false;
    }

}

