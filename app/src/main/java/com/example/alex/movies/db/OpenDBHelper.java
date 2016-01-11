package com.example.alex.movies.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.adapter.MoviesAdapter;
import com.example.alex.movies.interfasec.IDBHelper;
import com.example.alex.movies.models.Categories;
import com.example.alex.movies.models.Constants;

public class OpenDBHelper implements IDBHelper {
    private SQLiteDatabase db;

    @Override
    public void read() {
        db = MainActivity.dbHelper.getWritableDatabase();
        MoviesAdapter.categories.clear();

        Cursor c = db.query(Constants.TABLE_MOVIE, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            int titleColIndex = c.getColumnIndex(Constants.FIELD_TITLE);
            int likeColIndex = c.getColumnIndex(Constants.FIELD_LIKE);
            int unlikeColIndex = c.getColumnIndex(Constants.FIELD_UNLIKE);
            int imgColIndex = c.getColumnIndex(Constants.FIELD_POSTER_URL);
            int infoColIndex = c.getColumnIndex(Constants.FIELD_URL_MOVIES_INFO);
            int dataColIndex = c.getColumnIndex(Constants.FIELD_DATA);

            do {
                MoviesAdapter.categories.add(new Categories(
                        c.getString(titleColIndex),
                        c.getString(dataColIndex),
                        c.getString(imgColIndex),
                        c.getString(likeColIndex),
                        c.getString(unlikeColIndex),
                        c.getString(infoColIndex)));

                Log.d("DBHelper", "READ ID = " + c.getString(titleColIndex));

            } while (c.moveToNext());

        } else
            c.close();
    }

    @Override
    public void save(int position) {
        ContentValues cv = new ContentValues();
        db = MainActivity.dbHelper.getWritableDatabase();
        cv.put(Constants.FIELD_TITLE, MoviesAdapter.categories.get(position).title);
        cv.put(Constants.FIELD_LIKE, MoviesAdapter.categories.get(position).like);
        cv.put(Constants.FIELD_UNLIKE, MoviesAdapter.categories.get(position).unlike);
        cv.put(Constants.FIELD_POSTER_URL, MoviesAdapter.categories.get(position).urlImg);
        cv.put(Constants.FIELD_URL_MOVIES_INFO, MoviesAdapter.categories.get(position).urlFilmInfo);
        cv.put(Constants.FIELD_DATA, MoviesAdapter.categories.get(position).data);

        long size = db.insert(Constants.TABLE_MOVIE, null, cv);
        Log.d("DBHelper", "SAVE row inserted, size " + size + " ID = , " + MoviesAdapter.categories.get(position).title);

        MainActivity.dbHelper.close();
    }

    @Override
    public void delete(int position) {
        db = MainActivity.dbHelper.getWritableDatabase();

        String[] whereArgs = {String.valueOf(MoviesAdapter.categories.get(position).title)};
        db.delete(Constants.TABLE_MOVIE, Constants.FIELD_TITLE + " = ?", whereArgs);

        Log.d("DBHelper", "DELETE ID = " + MoviesAdapter.categories.get(position).title);

        db.close();
    }
}
