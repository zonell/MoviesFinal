package com.example.alex.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.R;
import com.example.alex.movies.models.Categories;
import com.example.alex.movies.parsed.ParsedMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{
    public static List<Categories> categories;


    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Picasso.with(MainActivity.context).load(categories.get(position).urlImg).into(holder.imgTitleFilm);
        final int finalPosition = position;
        holder.imgTitleFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParsedMovie.initFilmData(categories.get(finalPosition).urlFilmInfo);
            }
        });
        holder.tvCategoriesPopular.setText(categories.get(position).title);
        holder.tvDataCategoriesPopular.setText(categories.get(position).data);
        holder.tvLike.setText(categories.get(position).like);
        holder.tvUnlike.setText(categories.get(position).unlike);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCategoriesPopular;
        private TextView tvDataCategoriesPopular;
        private ImageView imgTitleFilm;
        private CheckBox cbStars;
        private TextView tvLike;
        private TextView tvUnlike;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            tvCategoriesPopular = (TextView) itemView.findViewById(R.id.tvTitleCategoriesPopular);
            tvDataCategoriesPopular = (TextView) itemView.findViewById(R.id.tvDataCategoriesPopular);
            tvLike = (TextView) itemView.findViewById(R.id.tvLike);
            tvUnlike = (TextView) itemView.findViewById(R.id.tvUnlike);
            imgTitleFilm = (ImageView) itemView.findViewById(R.id.imgTitleFilm);
            cbStars = (CheckBox) itemView.findViewById(R.id.cbStars);
        }
    }
}
