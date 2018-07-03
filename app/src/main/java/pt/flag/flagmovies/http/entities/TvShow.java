package pt.flag.flagmovies.http.entities;


import com.google.gson.annotations.SerializedName;

public class TvShow {


    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("first_air_date")
    private String releaseDate;

    @SerializedName("original_name")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("name")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("vote_average")
    private Float voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }



    public String getPosterURL() {

        return "http://image.tmdb.org/t/p/w185/" + getPosterPath();
    }
}
