package pt.flag.flagmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pt.flag.flagmovies.R;
import pt.flag.flagmovies.http.entities.Movie;
import pt.flag.flagmovies.screens.MovieDetail;

public class RecycleViewAdapterSearchResults extends RecyclerView.Adapter<RecycleViewAdapterSearchResults.ViewHolder> {

    private List<Movie> resultList;
    private Context context;

    public RecycleViewAdapterSearchResults(List<Movie> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }




    @Override
    public RecycleViewAdapterSearchResults.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_in_theater, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterSearchResults.ViewHolder holder, final int position) {
        final Movie filme = resultList.get(position);

        holder.in_theater_title.setText(filme.getOriginalTitle());
        float rating =resultList.get(position).getVoteAverage();
        float rating_converted = (rating*5)/10;
        System.out.println(rating_converted);
        holder.ratingBar.setRating(rating_converted);


        try
        {
            System.out.println("O overview é " + filme.getOverview());
            System.out.println("O url é" + filme.getPosterURL());
            Picasso.get().load(filme.getPosterURL()).into(holder.in_theater_poster);
        }catch (Exception e){
            System.out.println("ERROOOOOOO");
        }

        holder.cardView_item_movie_on_air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra("Movie Title", resultList.get(position).getOriginalTitle());
                intent.putExtra("Movie Poster", resultList.get(position).getPosterURL());
                intent.putExtra("Description",resultList.get(position).getOverview() );
                intent.putExtra("Rating", resultList.get(position).getVoteAverage());
                intent.putExtra("Id", resultList.get(position).getMovieid());

                context.startActivity(intent);


            }
        });

        }



    @Override
    public int getItemCount() {
        return resultList.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView in_theater_title;
        private ImageView in_theater_poster;
        private CardView cardView_item_movie_on_air;
        private ImageView movie_detail_poster;
        private RatingBar ratingBar;


        public ViewHolder(final View itemView){
            super(itemView);
            in_theater_title =(TextView) itemView.findViewById(R.id.title_movie_in_theater);
            in_theater_poster = (ImageView) itemView.findViewById(R.id.poster_movie_in_theater);
            cardView_item_movie_on_air = (CardView) itemView.findViewById(R.id.cardview_item_movie_on_air);
            movie_detail_poster = (ImageView) itemView.findViewById(R.id.movie_detail_poster);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rating_movie_in_theater);




        }

    }




}
