package com.example.alex.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.movies.MainActivity;
import com.example.alex.movies.R;
import com.example.alex.movies.models.AllMovies;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>{
    public static List<AllMovies> allMovies;

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Picasso.with(MainActivity.context).load(allMovies.get(position).urlImg).into(holder.imgMovies);
        holder.tvTitle.setText(allMovies.get(position).title);

    }

    @Override
    public int getItemCount() {
        return allMovies.size();
    }


    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMovies;
        private TextView tvTitle;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            imgMovies = (ImageView) itemView.findViewById(R.id.img_Movies);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_TitleMovies);
        }
    }
}
