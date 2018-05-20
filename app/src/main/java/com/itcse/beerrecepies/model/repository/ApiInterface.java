package com.itcse.beerrecepies.model.repository;


import com.itcse.beerrecepies.model.data.BeerDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("beers")
    Observable<List<BeerDetails>> getBeerList();
}
