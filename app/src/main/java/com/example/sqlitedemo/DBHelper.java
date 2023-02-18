package com.example.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    static final String DB_NAME =  "SIC.DB";
    static final int DB_VERSION = 1;
    static final String DB_TABLE = "USERS";
    static final String USER_ID = "_ID";
    static final String USER_NAME = "user_name";
    static final String USER_PASSWORD = "password";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DB_TABLE + " ( " + USER_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_NAME + " TEXT NOT NULL, " + USER_PASSWORD + " TEXT NOT NULL); ";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
    }
}
