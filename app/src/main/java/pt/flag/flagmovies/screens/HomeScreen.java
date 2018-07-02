package pt.flag.flagmovies.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pt.flag.flagmovies.R;
import pt.flag.flagmovies.adapter.RecycleViewAdapterMovieInTheaters;

import pt.flag.flagmovies.adapter.RecycleViewAdapterTvOnAir;
import pt.flag.flagmovies.collections.TVOnAir;

import pt.flag.flagmovies.collections.MoviesInTheaters;
import pt.flag.flagmovies.http.entities.MoviesResponse;
import pt.flag.flagmovies.http.entities.TvResponse;


public class HomeScreen extends Screen {

    private EditText editText;
    private Button button;


    private RecyclerView recyclerViewInTheaters;
    private LinearLayoutManager recyclerViewInTheatersLM;
    private RecycleViewAdapterMovieInTheaters recycleViewAdapter;
    private RecyclerView recyclerViewOnair;
    private LinearLayoutManager recyclerViewOnairLM;
    private RecycleViewAdapterTvOnAir recycleViewAdapterTvOnAir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        findViews();
        setListeners();
        recycleViewManager();
        GetMoviesinTheater();
        GetTvonAir();

    }


    public void findViews(){
        editText = findViewById(R.id.home_page_edit_text);
        button = findViewById(R.id.search_button);
        recyclerViewInTheaters = findViewById(R.id.recycleview_in_theaters);
        recyclerViewOnair = findViewById(R.id.recycleview_in_tv);





    }

    public void setListeners(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreen.this, SearchScreenResults.class );

                intent.putExtra("Search Query", editText.getText().toString());

                HomeScreen.this.startActivity(intent);
                //startActivity(new Intent(HomeScreen.this, SearchScreenResults.class));
            }
        });


    }



    public  void recycleViewManager() {
        recyclerViewInTheaters.setHasFixedSize(true);
        recyclerViewInTheatersLM = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewInTheaters.setLayoutManager(recyclerViewInTheatersLM);

        recyclerViewOnair.setHasFixedSize(true);
        recyclerViewOnairLM = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewOnair.setLayoutManager(recyclerViewOnairLM);
    }

    private void GetMoviesinTheater() {
        new  MoviesInTheaters(this) {


            @Override
            protected void onResponseSuccess(MoviesResponse moviesResponse) {
                recycleViewAdapter = new RecycleViewAdapterMovieInTheaters(HomeScreen.this,moviesResponse.getMovies());
                recyclerViewInTheaters.setAdapter(recycleViewAdapter);


            }



            @Override
            protected void onNetworkError() {
                System.out.println("Ola");

            }
        }.execute();
    }

    private void GetTvonAir() {
        new TVOnAir(this) {


            @Override
            protected void onResponseSuccess(TvResponse tvResponse) {
                recycleViewAdapterTvOnAir = new RecycleViewAdapterTvOnAir(HomeScreen.this, tvResponse.getTvshows());
                recyclerViewOnair.setAdapter(recycleViewAdapterTvOnAir);


            }



            @Override
            protected void onNetworkError() {
                System.out.println("Ola");

            }
        }.execute();
    }





    }







