package com.example.contacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbContact extends SQLiteOpenHelper {

    private static final String DB_NAME = "myphone_db";
    private static final int DB_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    private static final String TABLE_NAME = "contacts";

    public DbContact(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table " + TABLE_NAME + "(" + KEY_ID + " integer primary key, " + KEY_NAME + " varchar(30), integer " + KEY_PHONE + ")";

        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String delete_query = "DROP table  IF EXISTS " + TABLE_NAME;
        db.execSQL(delete_query);

        onCreate(db);
    }
}
