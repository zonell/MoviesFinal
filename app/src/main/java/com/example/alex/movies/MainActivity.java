package com.example.alex.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alex.movies.models.Constants;
import com.example.alex.movies.parsed.ParsedMoviesCategories;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private NavigationView navigationView;

    public static RecyclerView rvMovies;
    public static ProgressBar pbMovies;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(toolbar);

        initIO(Constants.URL_POPULAR_CATEGORIES);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initIO(String url) {
        ParsedMoviesCategories.initializeData(url);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        pbMovies = (ProgressBar) findViewById(R.id.pbMovies);
        context = getApplicationContext();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_data_of_renovation:
                initIO(Constants.URL_DATE_OF_RENOVATION);
                Toast.makeText(getApplicationContext(), "Click \"Data of renovation\"", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_by_rating:
                initIO(Constants.URL_BY_RATING);
                Toast.makeText(getApplicationContext(), "Click \"By rating\"", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_by_year_of_production:
                initIO(Constants.URL_BY_YEAR_OF_PRODUCTION);
                Toast.makeText(getApplicationContext(), "Click \"By year of production\"", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_by_popular:
                initIO(Constants.URL_POPULAR_CATEGORIES);
                Toast.makeText(getApplicationContext(), "Click \"Popular\"", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_in_trend:
                initIO(Constants.URL_IN_TREND);
                Toast.makeText(getApplicationContext(), "Click \"In Trend\"", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_favorite:
                Toast.makeText(getApplicationContext(), "\"Favorite\" add a new version", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
