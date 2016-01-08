package com.example.alex.movies.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.R;
import com.example.alex.movies.db.OpenDBHelper;
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
    private OpenDBHelper dbHelper;


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
                if (holder.cbStars.isChecked()) {
                    dbHelper.save(position);
                    Toast.makeText(MainActivity.context, "save", Toast.LENGTH_SHORT).show();
                    Constants.CATEGORIES.add(categories.get(position));
                } else {
                    dbHelper.delete(position);
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


}
