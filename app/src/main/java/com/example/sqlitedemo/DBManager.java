package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DBManager (Context context) {
        this.context = context;
    }

    public DBManager open() throws SQLDataException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public void insert(String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.USER_NAME, username);
        contentValues.put(DBHelper.USER_PASSWORD, password);
        db.insert(DBHelper.DB_TABLE, null, contentValues);

    }

    public Cursor fetch() {
        String [] columns = new String[] {DBHelper.USER_ID, DBHelper.USER_NAME, DBHelper.USER_PASSWORD };
        Cursor cursor = db.query(DBHelper.DB_TABLE, columns, null, null,
                null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String username, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.USER_NAME, username);
        contentValues.put(DBHelper.USER_PASSWORD, password);
        int ret = db.update(DBHelper.DB_TABLE, contentValues, DBHelper.USER_ID + "=" + id, null);
        return ret;
    }

    public void delete(long id) {
        db.delete(DBHelper.DB_TABLE, DBHelper.USER_ID + "=" + id, null);
    }
}
