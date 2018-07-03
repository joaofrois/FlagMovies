package pt.flag.flagmovies.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pt.flag.flagmovies.R;

public class MovieDetail extends Screen {

    private Intent intent;
    private TextView title;
    private TextView description;
    private ImageView poster;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        findViews();
        receiveData();

    }

    private void findViews(){
        title = findViewById(R.id.movie_detail_title);
        description = findViewById(R.id.movie_detail_description);
        intent = getIntent();
        poster = findViewById(R.id.movie_detail_poster);
        ratingBar = findViewById(R.id.rating_movie_detail);

    }

    private void receiveData(){


        String movietitle = intent.getExtras().getString("Movie Title");
        String moviedescription = intent.getExtras().getString("Description");
        String movieposter = intent.getExtras().getString("Movie Poster");
        title.setText(movietitle);
        description.setText(moviedescription);
        float rating =intent.getExtras().getFloat("Rating");
        float rating_converted = (rating*5)/10;
        ratingBar.setRating(rating_converted);

        Picasso.get().load(movieposter).into(poster);





    }
}
