package com.example.temanhijrah.giantplay.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.temanhijrah.giantplay.object.Favorit;

import java.util.ArrayList;
import java.util.List;

public class FavoritDBAdapter {

    DBContract.FavoritDbHelper dbHelper;

    public FavoritDBAdapter(Context context) {
        dbHelper = new DBContract.FavoritDbHelper(context);
    }

    public long insertFav(String favType, String surahNumber, String surahName, String surahTranslate){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.FavouriteDB.COLUMN_FAV_TYPE, favType);
        values.put(DBContract.FavouriteDB.COLUMN_SURAH_NUMBER, surahNumber);
        values.put(DBContract.FavouriteDB.COLUMN_SURAH_NAME, surahName);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.FavouriteDB.TABLE_NAME, null, values);

        return newRowId;
    }

    public List<Favorit> getFav(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DBContract.FavouriteDB.COLUMN_FAV_TYPE,
                DBContract.FavouriteDB.COLUMN_SURAH_NUMBER,
                DBContract.FavouriteDB.COLUMN_SURAH_NAME
        };

        // Filter results WHERE "title" = 'My Title'
        //String selection = DBContract.FavouriteDB.COLUMN_FAV_TYPE + " = ?";
        //String[] selectionArgs = { "MyNewTitle" };

        String selection = null;
        String[] selectionArgs = null;

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DBContract.FavouriteDB.COLUMN_FAV_TYPE+ " DESC";

        Cursor cursor = db.query(
                DBContract.FavouriteDB.TABLE_NAME,   // The table to query
                projection,         // The array of columns to return (pass null to get all)
                selection,          // The columns for the WHERE clause
                selectionArgs,      // The values for the WHERE clause
                null,       // don't group the rows
                null,        // don't filter by row groups
                sortOrder           // The sort order
        );

        List<Favorit> items = new ArrayList<Favorit>();
        while(cursor.moveToNext()) {
            Favorit fav = new Favorit();

            fav.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.FavouriteDB._ID)));
            fav.setFavType(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.FavouriteDB.COLUMN_FAV_TYPE)));
            fav.setSurahNumber(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.FavouriteDB.COLUMN_SURAH_NUMBER)));
            fav.setSurahName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.FavouriteDB.COLUMN_SURAH_NAME)));

            items.add(fav);
        }
        cursor.close();

        return items;
    }

    public int deleteFav(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Define 'where' part of query.
        String selection = DBContract.FavouriteDB.COLUMN_FAV_TYPE+ " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { "Doa Harian" };
        // Issue SQL statement.
        int deletedRows = db.delete(DBContract.FavouriteDB.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }

    public int updateFav(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New value for one column
        String title = "MyNewTitle";
        ContentValues values = new ContentValues();
        values.put(DBContract.FavouriteDB.COLUMN_FAV_TYPE, title);

        // Which row to update, based on the title
        String selection = DBContract.FavouriteDB.COLUMN_FAV_TYPE+ " LIKE ?";
        String[] selectionArgs = { "Doa Harian" };

        int count = db.update(
                DBContract.FavouriteDB.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }
}
