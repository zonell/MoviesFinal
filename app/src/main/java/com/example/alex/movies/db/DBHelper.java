package com.example.alex.movies.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alex.movies.models.Constants;

public class DBHelper  extends SQLiteOpenHelper{
    public DBHelper(Context context) {
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Constants.TABLE_MOVIE + " ("
                +  Constants.FIELD_TITLE + " text primary key, "
                +  Constants.FIELD_LIKE + " text,"
                +  Constants.FIELD_UNLIKE + " text,"
                +  Constants.FIELD_POSTER_URL + " text,"
                +  Constants.FIELD_URL_MOVIES_INFO + " text,"
                +  Constants.FIELD_DATA + " text"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
