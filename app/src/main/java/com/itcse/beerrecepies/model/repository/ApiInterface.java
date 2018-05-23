package com.itcse.beerrecepies.model.repository;


import com.itcse.beerrecepies.model.data.BeerDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("beers")
    Observable<List<BeerDetails>> getBeerList(@Query("page") final int page);

    @GET("beers")
    Observable<List<BeerDetails>> getBeerByName(@Query("beer_name") final String beerName);
}
