package com.example.temanhijrah.giantplay.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class DBContract {

    public DBContract() {
    }

    public static class FavouriteDB implements BaseColumns{
        public static final String TABLE_NAME = "favorit";
        public static final String COLUMN_FAV_TYPE = "fav_type";
        public static final String COLUMN_SURAH_NUMBER = "surah_number";
        public static final String COLUMN_SURAH_NAME = "surah_name";
        public static final String COLUMN_SURAH_TRANSLATE = "surah_translate";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FavouriteDB.TABLE_NAME + " (" +
                    FavouriteDB._ID + " INTEGER PRIMARY KEY," +
                    FavouriteDB.COLUMN_FAV_TYPE + " TEXT," +
                    FavouriteDB.COLUMN_SURAH_NUMBER + " TEXT," +
                    FavouriteDB.COLUMN_SURAH_NAME + " TEXT," +
                    FavouriteDB.COLUMN_SURAH_TRANSLATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FavouriteDB.TABLE_NAME;


    public static class FavoritDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "TemanHijrah.db";

        public FavoritDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}
