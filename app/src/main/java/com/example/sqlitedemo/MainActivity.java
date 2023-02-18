package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etID;
    EditText etName;
    EditText etPassword;

    Button bCreate;
    Button bRead;
    Button bUpdate;
    Button bDelete;

    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID = findViewById(R.id.etID);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);

        bCreate = findViewById(R.id.bCreate);
        bRead = findViewById(R.id.bRead);
        bUpdate = findViewById(R.id.bUpdate);
        bDelete = findViewById(R.id.bDelete);

        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.insert(etName.getText().toString(), etPassword.getText().toString());
            }
        });

        bRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbManager.fetch();
                if(cursor.moveToFirst()) {
                    do {
                        int iID = cursor.getColumnIndex(DBHelper.USER_ID);
                        String ID = iID >= 0 ?  cursor.getString(iID) : "";
                        int iName = cursor.getColumnIndex(DBHelper.USER_NAME);
                        String name = iName >= 0 ? cursor.getString(iName) : "";
                        int iPassword = cursor.getColumnIndex(DBHelper.USER_PASSWORD);
                        String password = iPassword >= 0 ? cursor.getString(iPassword) : "";
                        Log.i("DB_TAG", "I have read ID : " + ID + " Username: " + name +
                                " password : " + password);
                    } while (cursor.moveToNext());
                }
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.update(Long.parseLong(etID.getText().toString()), etName.getText().toString(),
                        etPassword.getText().toString());
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.delete(Long.parseLong(etID.getText().toString()));
            }
        });

        dbManager = new DBManager(this);
        try {
            dbManager.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}