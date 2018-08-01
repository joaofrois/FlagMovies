package pt.flag.flagmovies.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button button;

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
        button = findViewById(R.id.movie_page);

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
        final String url ="https://www.themoviedb.org/movie/"+ intent.getExtras().getInt("Id");
        System.out.println(url);

        Picasso.get().load(movieposter).into(poster);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(url);
                Intent i = null;

                i = new   Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);

            }
        });





    }
}
