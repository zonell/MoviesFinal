package com.example.alex.movies.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.R;
import com.example.alex.movies.models.Categories;
import com.example.alex.movies.models.Constants;
import com.example.alex.movies.parsed.ParsedMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{
    private static final String LOG = "log";
    private static final String PREFS_NAME = "movies_prefs";


    public static List<Categories> categories;
    private Context context;
    private SQLiteDatabase db;


    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(final MoviesViewHolder holder, final int position) {
        final SharedPreferences setFavoritesCnB = MainActivity.context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isFavoriteChB = setFavoritesCnB.getBoolean(categories.get(position).title, false);

        holder.cbStars.setChecked(isFavoriteChB);
        Picasso.with(context).load(categories.get(position).urlImg).into(holder.imgTitleFilm);
        holder.imgTitleFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParsedMovie.initFilmData(categories.get(position).urlFilmInfo, categories.get(position));
            }
        });
        holder.cbStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = setFavoritesCnB.edit();
                editor.putBoolean(categories.get(position).title, holder.cbStars.isChecked());
                editor.apply();
                if (holder.cbStars.isChecked()){
                    saveInDB(position);
                    Toast.makeText(MainActivity.context, "save", Toast.LENGTH_SHORT).show();
                    Constants.CATEGORIES.add(categories.get(position));
                }
                else {
                    deleteFromDB(position);
                    Toast.makeText(MainActivity.context, "delete", Toast.LENGTH_SHORT).show();
                    Constants.CATEGORIES.remove(categories.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTitleFilm;
        private CheckBox cbStars;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            imgTitleFilm = (ImageView) itemView.findViewById(R.id.imgTitleFilm);
            cbStars = (CheckBox) itemView.findViewById(R.id.cbStars);
        }
    }

    private void saveInDB(int position){
        ContentValues cv = new ContentValues();
        db = MainActivity.dbHelper.getWritableDatabase();
        cv.put(Constants.FIELD_TITLE, categories.get(position).title);
        cv.put(Constants.FIELD_LIKE, categories.get(position).like);
        cv.put(Constants.FIELD_UNLIKE, categories.get(position).unlike);
        cv.put(Constants.FIELD_POSTER_URL, categories.get(position).urlImg);
        cv.put(Constants.FIELD_URL_MOVIES_INFO, categories.get(position).urlFilmInfo);
        cv.put(Constants.FIELD_DATA, categories.get(position).data);

        long rowID = db.insert(Constants.TABLE_MOVIE, null, cv);
        Log.d(LOG, "row inserted, ID = " + rowID);

        MainActivity.dbHelper.close();
    }

    private void deleteFromDB(int position){
        db = MainActivity.dbHelper.getWritableDatabase();

        String[] whereArgs = {String.valueOf(categories.get(position).title)};
        db.delete(Constants.TABLE_MOVIE, Constants.FIELD_TITLE, whereArgs);

        db.close();
    }
}
