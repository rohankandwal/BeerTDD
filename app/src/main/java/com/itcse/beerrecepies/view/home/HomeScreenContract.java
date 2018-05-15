package com.itcse.beerrecepies.view.home;

import com.itcse.beerrecepies.model.data.BeerDetails;

import java.util.List;

public class HomeScreenContract {
    interface View {
        void setBeerList(final List<BeerDetails> beerList);

        void noBeerFound();
    }

    interface Presenter {
        void getBeers();
    }
}
