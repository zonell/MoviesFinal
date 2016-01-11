package com.example.alex.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.alex.movies.models.Constants;
import com.example.alex.movies.parsed.ParsedSearch;

public class SearchActivity extends AppCompatActivity {
    private EditText editText;
    public static RecyclerView rvMovies;
    public static ProgressBar pbSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initIO();
    }

    private void initIO(){
        editText = (EditText) findViewById(R.id.search);
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies_search);
        pbSearch = (ProgressBar) findViewById(R.id.pbSearch);
    }

    public void onClickSearch(View view) {
        pbSearch.setVisibility(View.VISIBLE);
        ParsedSearch.initializeData(Constants.URL_SEARCH + editText.getText());
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new GridLayoutManager(this, 3));

    }
}
