package pt.flag.flagmovies.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import pt.flag.flagmovies.R;
import pt.flag.flagmovies.adapter.RecycleViewAdapterSearchResults;
import pt.flag.flagmovies.collections.SearchMovies;
import pt.flag.flagmovies.http.entities.SearchResponse;

public class SearchScreenResults extends Screen {
    private TextView textView;
    private Intent intent;
    private RecyclerView recyclerView;
    private RecycleViewAdapterSearchResults recycleViewAdapterSearchResults;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen_results);
        findViews();
        setText();
        recyclerViewManager();
        getSearchResults();
    }

    public void findViews(){
        textView = findViewById(R.id.search_test);
        intent = getIntent();
        recyclerView = findViewById(R.id.recycleview_search_results);

    }

    public void setText(){
        textView.setText("Pesquisou: " + intent.getExtras().getString("Search Query"));
    }

    public void recyclerViewManager(){
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void getSearchResults(){
        new SearchMovies(this, intent.getExtras().getString("Search Query")){


            @Override
            protected void onResponseSuccess(SearchResponse searchResponse) {
                recycleViewAdapterSearchResults = new RecycleViewAdapterSearchResults(searchResponse.getMoviesResults(), SearchScreenResults.this);
                recyclerView.setAdapter(recycleViewAdapterSearchResults);
            }

            @Override
            protected void onNetworkError() {
                System.out.println("Ola");
            }
        }.execute();
    }


}
