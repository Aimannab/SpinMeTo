package com.exampleapp.android.androidlibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aiman Nabeel on 08/11/2018.
 */
//To save the names of random countries displayed on every swipe
public class CountriesDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "randomcountrieslist.db";
    private static final int DATABASE_VERSION = 1;

    public CountriesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_RANDOM_COUNTRIES_TABLE = "CREATE TABLE " +
                CountriesDBContract.RandomCountriesList.TABLE_NAME + " (" +
                CountriesDBContract.RandomCountriesList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_NAME + " TEXT NOT NULL" +
                //"UNIQUE " + "(" + CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_ID + ")" + " ON CONFLICT REPLACE" +
                ");";

        db.execSQL(SQL_CREATE_RANDOM_COUNTRIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CountriesDBContract.RandomCountriesList.TABLE_NAME);
        onCreate(db);
    }
}
