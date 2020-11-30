package com.example.primevideoclone.Model;

import com.google.gson.annotations.SerializedName;

public class CategoryItem {

    //Estructura que tendra el banner de las peliculas.
    @SerializedName("id")
    private int id;
    @SerializedName("movieName")
    private String movieName;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("fileUrl")
    private String fileUrl;
    @SerializedName("estreno")
    private String estreno;
    @SerializedName("popularity")
    private double popularidad;
    @SerializedName("votes")
    private double votes;
    @SerializedName("details")
    private String details;
    @SerializedName("language")
    private String language;

    public CategoryItem(int id, String movieName, String imageUrl, String fileUrl, String estreno, double popularidad, double votes, String details, String language) {
        this.id = id;
        this.movieName = movieName;
        this.imageUrl = imageUrl;
        this.fileUrl = fileUrl;
        this.estreno = estreno;
        this.popularidad = popularidad;
        this.votes = votes;
        this.details = details;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public double getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(double popularidad) {
        this.popularidad = popularidad;
    }

    public double getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
