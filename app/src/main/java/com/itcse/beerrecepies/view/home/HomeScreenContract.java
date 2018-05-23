package com.itcse.beerrecepies.view.home;

import com.itcse.beerrecepies.model.data.BeerDetails;
import com.itcse.beerrecepies.view.base.BasePresenter;
import com.itcse.beerrecepies.view.base.BaseView;

import java.util.List;

import io.reactivex.annotations.NonNull;

class HomeScreenContract {
    interface View extends BaseView {
        void setBeerList(final List<BeerDetails> beerList);

        void noBeerFound();

        void showToast(@NonNull final String message);

        /**
         * Function called when no beer found with name
         * @param beerName String containing name of the beer searched
         */
        void noBeerWithNameFound(@NonNull final String beerName);

        /**
         * Function called when search beers with name found
         * @param beerDetails List of beers with name searched
         */
        void searchedBeerListFound(@NonNull final List<BeerDetails> beerDetails);
    }

    interface Presenter extends BasePresenter {
        /**
         * Function to load beer list page by page
         * @param page denotes page number to be loaded
         */
        void getBeers(final int page);

        /**
         * Function to search beer by name
         * @param beerName String name to be searched
         */
        void searchBeerByName(@NonNull final String beerName);
    }
}
