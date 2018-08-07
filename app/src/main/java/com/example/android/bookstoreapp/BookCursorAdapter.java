package com.example.android.bookstoreapp;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.bookstoreapp.data.BookContract.BookEntry;

/**
 * Created by dzsub on 2018.07.27..
 */

public class BookCursorAdapter extends CursorAdapter {

    private static final int SALE_ONE = 1;

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        Button saleButton = (Button) view.findViewById(R.id.sale_button);

        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_QUANTITY);
        int idColumnIndex = cursor.getColumnIndex(BookEntry._ID);

        String productName = cursor.getString(nameColumnIndex);
        int bookPrice = cursor.getInt(priceColumnIndex);
        final int bookQuantity = cursor.getInt(quantityColumnIndex);
        final int bookId = cursor.getInt(idColumnIndex);

        nameTextView.setText(productName);
        priceTextView.setText((Integer.toString(bookPrice)) + " $");
        quantityTextView.setText(Integer.toString(bookQuantity));

        final Uri currentUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, bookId);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver contentResolver = view.getContext().getContentResolver();
                int newQuantity;
                if (bookQuantity <= 0) {
                    return;
                } else {
                    newQuantity = bookQuantity - SALE_ONE;
                }
                ContentValues values = new ContentValues();
                values.put(BookEntry.COLUMN_QUANTITY, newQuantity);
                contentResolver.update(currentUri, values, null, null);
            }
        });

    }
}
