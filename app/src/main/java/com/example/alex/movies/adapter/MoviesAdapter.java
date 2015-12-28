package com.example.alex.movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.alex.movies.R;
import com.example.alex.movies.models.Categories;
import com.example.alex.movies.parsed.ParsedMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{
    public static List<Categories> categories;
    private Context context;

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, final int position) {
        Picasso.with(context).load(categories.get(position).urlImg).into(holder.imgTitleFilm);
        holder.imgTitleFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParsedMovie.initFilmData(categories.get(position).urlFilmInfo, categories.get(position));
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
