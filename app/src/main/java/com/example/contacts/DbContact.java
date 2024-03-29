package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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
        String create_table = "create table " + TABLE_NAME + "(" + KEY_ID + " integer primary key, " + KEY_NAME + " varchar(30), "+  KEY_PHONE  +" integer )";

        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String delete_query = "DROP table  IF EXISTS " + TABLE_NAME;
        db.execSQL(delete_query);

        onCreate(db);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());

        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        String select_query = "select * from "+ TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {
            do {

                //String name = cursor.getString(1);

                // OR

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));

                Contact contact = new Contact(id, name, phone);

                contacts.add(contact);

            } while (cursor.moveToNext());
        }
        return contacts;
    }

    public Contact getContactById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String select_item_query = "select * from " + TABLE_NAME + " where id = " + id;

        Cursor cursor = db.rawQuery(select_item_query, null);

        Contact contact = null;

        if (cursor.moveToNext()) {
            int contactID = cursor.getInt(cursor.getColumnIndex(KEY_ID));

            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));

            int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));

            contact = new Contact(id, name, phone);
        }

        return contact;
    }

    public boolean updateContact (Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PHONE, contact.getPhone());
        db.update(TABLE_NAME, contentValues, KEY_ID + " = ? ", new String[] { Integer.toString(contact.getId()) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                KEY_ID + " = ? ",
                new String[] { Integer.toString(id) });
    }
}
