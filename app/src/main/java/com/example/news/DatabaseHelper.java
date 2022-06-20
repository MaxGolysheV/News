package com.example.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        //Наименование БД;  Version >= 1
        super(context, "Userdata.db", null, 1);
        //context.deleteDatabase("Userdata.db");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Запрос на создание таблицы в БД
        sqLiteDatabase.execSQL("create Table UserInfo(id INTEGER primary key AUTOINCREMENT NOT NULL, login TEXT NOT NULL, password TEXT NOT NULL, role TEXT NOT NULL)");
        sqLiteDatabase.execSQL("create Table News(id INTEGER primary key AUTOINCREMENT NOT NULL, title TEXT NOT NULL, text TEXT NOT NULL)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Условие на удаление таблицы UserInfo
        sqLiteDatabase.execSQL("drop Table if exists UserInfo");
        sqLiteDatabase.execSQL("drop Table if exists News");
    }

    public Boolean InsertUser(String login, String password, String role){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login);
        contentValues.put("password", password);
        contentValues.put("role", role);
        long result = DB.insert("UserInfo", null, contentValues);
        return result != -1;
    }

    public Boolean InsertNews(String title, String text){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("text", text);
        long result = DB.insert("News", null, contentValues);
        return result != -1;
    }

    public Boolean UpdateNews(String id, String title, String text){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("text", text);
        long result = DB.update("News", contentValues, "id=?", new String[] {id});
        //long result = DB.update("News", contentValues, "id= '"+id+"'", null);
        return result != -1;
    }

    public Boolean DeleteNews(String id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        long result = DB.delete("News", "id=?", new String[] {id});
        return result != -0;
    }

    public Cursor getUserdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from UserInfo", null);
    }

    public Cursor getNewsdata(){
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("Select * from News", null);
    }


}
