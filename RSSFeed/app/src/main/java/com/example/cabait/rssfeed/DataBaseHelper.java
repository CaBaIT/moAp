package com.example.cabait.rssfeed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cabait on 02.02.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "rss.db";
        private static final SQLiteDatabase.CursorFactory DATABASE_FACTORY= null;
        private static final int DATABASE_VERSION = 3;
        private static final String TABLE_NAME= "RSSURL";
        public static final String COLUMN_ID ="_id";
        public static final String COLUMN_URL="URL";

        public static DataBaseHelper instance;

        public static synchronized DataBaseHelper getInstance(Context context) {
            if (instance== null)
            {
                instance = new DataBaseHelper(context);
            }
            return instance;
        }

        private DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, DATABASE_FACTORY, DATABASE_VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            String createTable="CREATE TABLE " + TABLE_NAME
                    + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_URL + " TEXT"
                    + ")";

            db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            if (oldVersion!=newVersion)
            {
                db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
                onCreate(db);
            }
        }

        public Cursor getCursor()
        {
            SQLiteDatabase db = getReadableDatabase();

            db.beginTransaction();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            db.endTransaction();
            Log.d("Cabait","Database has" + cursor.getCount() + " entries");
            return cursor;
        }


        // Crud
        // Create
        // Read
        // Update
        // Delete



        //CREATE Method
        public boolean addUrl(String url)
        {
            try
            {
                SQLiteDatabase db = getWritableDatabase();

                db.beginTransaction();
                ContentValues values = new ContentValues();
                values.put(COLUMN_URL, url);
                db.insert(TABLE_NAME,null,values);

                db.setTransactionSuccessful();
                db.endTransaction();

                return  true;
            }
            catch (Exception e)
            {
                return false;
            }
        }

        //READ Method

        public String getUrl(long id)
        {
            String url = null;
            SQLiteDatabase db = getReadableDatabase();
            db.beginTransaction();

            Cursor cursor =db.query(TABLE_NAME,                         //Tablename
                    new String[]{COLUMN_ID, COLUMN_URL},    //Columns to return
                    COLUMN_ID + "=?",                                   //Where
                    new String[]{String.valueOf(id)},
                    null, //Group by
                    null, //Having
                    null, //Order by
                    null  //Limit
            );

            if (cursor != null)
            {
                cursor.moveToFirst();
                String urlDescription = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL));

                url = urlDescription;
            }
            db.endTransaction();
            return url;
        }
    public void deleteTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }




    }


