package com.itcse.beerrecepies.view.home;

import android.support.annotation.NonNull;

import com.itcse.beerrecepies.model.data.BeerDetails;
import com.itcse.beerrecepies.model.repository.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private HomeScreenContract.View view;
    private ApiInterface apiInterface;

    public HomeScreenPresenter(@NonNull final HomeScreenContract.View view,
                               @NonNull final ApiInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    @Override
    public void getBeers() {
        apiInterface.getBeerList().enqueue(new Callback<List<BeerDetails>>() {
            @Override
            public void onResponse(@NonNull Call<List<BeerDetails>> call, @NonNull Response<List<BeerDetails>> response) {
                if (response.isSuccessful()) {
                    final List<BeerDetails> beerDetailsList = response.body();
                    if (beerDetailsList != null && beerDetailsList.size() > 0) {
                        view.setBeerList(beerDetailsList);
                    } else {
                        view.noBeerFound();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BeerDetails>> call, @NonNull Throwable t) {

            }
        });
    }
}
