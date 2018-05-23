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

    }

    interface Presenter extends BasePresenter {
        /**
         * Function to load beer list page by page
         * @param page denotes page number to be loaded
         */
        void getBeers(final int page);
    }
}
