package com.example.alex.movies.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.adapter.MoviesAdapter;
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
            int titleColIndex = c.getColumnIndex("title");
            int likeColIndex = c.getColumnIndex("like");
            int unlikeColIndex = c.getColumnIndex("unlike");
            int imgColIndex = c.getColumnIndex("poster");
            int infoColIndex = c.getColumnIndex("info");
            int dataColIndex = c.getColumnIndex("data");

            do {
                MoviesAdapter.categories.add(new Categories(
                        c.getString(titleColIndex),
                        c.getString(dataColIndex),
                        c.getString(imgColIndex),
                        c.getString(likeColIndex),
                        c.getString(unlikeColIndex),
                        c.getString(infoColIndex)));
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

        MainActivity.dbHelper.close();
    }

    @Override
    public void delete(int position) {
        db = MainActivity.dbHelper.getWritableDatabase();

        String[] whereArgs = {String.valueOf(MoviesAdapter.categories.get(position).title)};
        db.delete(Constants.TABLE_MOVIE, Constants.FIELD_TITLE + " = ?", whereArgs);

        db.close();
    }
}
