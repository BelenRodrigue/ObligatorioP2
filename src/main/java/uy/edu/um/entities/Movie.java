package uy.edu.um.entities;

import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

public class Movie {
    private boolean adults;
    private MyLinkedListImpl<Collection> collection;
    private int budget;
    private MyLinkedListImpl<Genero> genres;
    private String homepage;
    private int id;
    private String imdbid;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private String productionCompanies;
    private String productionCountries;
    private String releasedDate;
    private long revenue;
    private double runtime;
    private String spokenLanguages;
    private String status;
    private String tagline;
    private String title;

    public Movie(boolean adults, MyLinkedListImpl<Collection> collection, int budget, MyLinkedListImpl<Genero> genres, String homepage, int id, String imdbid, String originalLanguage, String originalTitle, String overview, String productionCompanies, String productionCountries, String releasedDate, long revenue, double runtime, String spokenLanguages, String status, String tagline, String title) {
        this.adults = adults;
        this.collection = collection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdbid = imdbid;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releasedDate = releasedDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
    }
}
