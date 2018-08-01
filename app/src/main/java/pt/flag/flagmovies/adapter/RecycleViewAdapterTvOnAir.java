package pt.flag.flagmovies.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import pt.flag.flagmovies.http.entities.TvShow;
import pt.flag.flagmovies.screens.TvDetail;

public class RecycleViewAdapterTvOnAir extends RecyclerView.Adapter<RecycleViewAdapterTvOnAir.ViewHolder> {

    private List<TvShow> tvshowList;
    private Context context;


    public RecycleViewAdapterTvOnAir(Context context, List<TvShow> tvshowList) {

        this.tvshowList = tvshowList;
        this.context=context;
    }


    @Override
    public RecycleViewAdapterTvOnAir.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_on_air, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterTvOnAir.ViewHolder holder,final int position) {
        TvShow tvshow = tvshowList.get(position);

        holder.title_tv_on_air.setText(tvshow.getOriginalTitle());

        float rating =tvshowList.get(position).getVoteAverage();
        float rating_converted = (rating*5)/10;
        System.out.println(rating_converted);
        holder.ratingBar_tv_detail.setRating(rating_converted);

        try
        {
            if(!tvshow.getPosterURL().equals("null")) {
                Picasso.get().load(tvshow.getPosterURL()).into(holder.poster_tv_on_air);
            }
            else if(tvshow.getPosterURL().equals("null")){
                Bitmap bitmap = BitmapFactory.decodeFile("mipmap-hdpi/app_icon.png");
                holder.poster_tv_on_air.setImageBitmap(bitmap);
            }
        }catch (Exception e){
            System.out.println("ERROOOOOOO");
        }

        holder.cardView_item_tv_on_air.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TvDetail.class);
                intent.putExtra("TvShow Title", tvshowList.get(position).getOriginalTitle());
                intent.putExtra("TvShow Poster", tvshowList.get(position).getPosterURL());
                intent.putExtra("Description",tvshowList.get(position).getOverview() );
                intent.putExtra("Rating", tvshowList.get(position).getVoteAverage());


                context.startActivity(intent);

            }

        });




    }



    @Override
    public int getItemCount() {
        return tvshowList.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title_tv_on_air;
        private ImageView poster_tv_on_air;
        private CardView cardView_item_tv_on_air;
        private ImageView tv_detail_poster;
        private RatingBar ratingBar_tv_detail;


        public ViewHolder(final View itemView){
            super(itemView);
            title_tv_on_air =(TextView) itemView.findViewById(R.id.title_tv_on_air);
            poster_tv_on_air = (ImageView) itemView.findViewById(R.id.poster_tv_on_air);
            cardView_item_tv_on_air = (CardView) itemView.findViewById(R.id.cardview_item_tv_on_air);
            tv_detail_poster = (ImageView) itemView.findViewById(R.id.tv_detail_poster);
            ratingBar_tv_detail = (RatingBar) itemView.findViewById(R.id.rating_tv_on_air);




        }

    }
}
