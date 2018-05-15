package com.itcse.beerrecepies.model.repository;

import com.itcse.beerrecepies.model.data.BeerDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/beers")
    Call<List<BeerDetails>> getBeerList();
}
