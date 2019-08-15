package com.example.temanhijrah.giantplay.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.temanhijrah.giantplay.dev.Message;

public class DBAdapter {

    DBHelper dbHelper;

    public DBAdapter(Context context){
        dbHelper = new DBHelper(context);
    }

    public long insertData(String name, String pass){
        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.NAME, name);
        contentValues.put(dbHelper.PASS, pass);
        long id = dbb.insert(dbHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {dbHelper.UID, dbHelper.NAME, dbHelper.PASS};
        Cursor cursor = db.query(dbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(dbHelper.UID));
            String name = cursor.getString(cursor.getColumnIndex(dbHelper.NAME));
            String password = cursor.getString(cursor.getColumnIndex(dbHelper.PASS));
            buffer.append(cid + "  " + name + " " + password + " \n");
        }
        return buffer.toString();
    }

    static class DBHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "TemanHijrahDB";
        private static final String TABLE_NAME = "TemanHijrahTable";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASS = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ PASS+" VARCHAR(225));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DBHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context, "Started...");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "TABLE CREATED");
            }catch (Exception e){
                Message.message(context, ""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "On Upgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e){
                Message.message(context, ""+e);
            }
        }
    }

}
