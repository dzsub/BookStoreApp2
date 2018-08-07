package com.example.android.bookstoreapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dzsub on 2018.07.07..
 */

public class BookDbHelper extends SQLiteOpenHelper {

    //name of the database file
    private static final String DATABASE_NAME = "inventory.db";

    //version of the database
    private static final int DATABASE_VERSION = 1;

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_INVENTORY_TABLE = "CREATE TABLE "
                + BookContract.BookEntry.TABLE_NAME + "("
                + BookContract.BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookContract.BookEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + BookContract.BookEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                + BookContract.BookEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + BookContract.BookEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "
                + BookContract.BookEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
