package com.itcse.beerrecepies.view.home;

import com.itcse.beerrecepies.RxSchedulersOverrideRule;
import com.itcse.beerrecepies.model.data.BeerDetails;
import com.itcse.beerrecepies.model.repository.ApiInterface;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class HomeScreenPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Rule
    public RxSchedulersOverrideRule schedulersOverrideRule = new RxSchedulersOverrideRule();
    @Mock
    private
    HomeScreenContract.View view;
    @Mock
    private
    ApiInterface apiInterface;

    private HomeScreenPresenter presenter;

    @Before
    public void setUp() {
        presenter = new HomeScreenPresenter(view, apiInterface);
    }

    @Test
    public void getBeerList() {
        // Given
        final List<BeerDetails> beerDetailsList = Arrays.asList(new BeerDetails(), new BeerDetails());
        Mockito.when(apiInterface.getBeerList()).thenReturn(Observable.just(beerDetailsList));
        // When
        presenter.getBeers();
        // Then
        Mockito.verify(view).setBeerList(beerDetailsList);
    }

    @Test
    public void noBeerFound() {
        // Given
        // Don't call Observable.<List<BeerDetails>>empty() even when returning emptyList because
        // it emits nothing, so onNext() will never be called, directly onCompleted.
        Mockito.when(apiInterface.getBeerList()).thenReturn(Observable.<List<BeerDetails>>empty());
        // When
        presenter.getBeers();
        // Then
        Mockito.verify(view).noBeerFound();
    }

/*    @Test
    public void getBeerList() {
        // Given
        final HomeScreenContract.View view = new MockView();
        final ApiInterface apiInterface = new MockRepository(true);
        // When
        presenter.getBeers();
        // Then
        Mockito.verify(view).setBeerList(beerDetailsList);
    }

    @Test
    public void noBeerFound() {
        // Given
        final HomeScreenContract.View view = new MockView();
        // When
        presenter.getBeers();
        // Then
        Assert.assertFalse(((MockView) view).foundBeerList);
    }*


    /*class MockRepository implements ApiInterface {

        private boolean returnBooks;

        MockRepository(final boolean returnBooks) {
            this.returnBooks = returnBooks;
        }

        @Override
        public Call<List<BeerDetails>> getBeerList() {
            if (returnBooks) {
                return Arrays.asList(new BeerDetails(), new BeerDetails(), new BeerDetails());
            }
            return Collections.emptyList();
        }
    }*/

/*
    class MockView implements HomeScreenContract.View {

        boolean foundBeerList;

        @Override
        public void setBeerList(List<BeerDetails> beerList) {
            foundBeerList = true;
        }

        @Override
        public void noBeerFound() {
            foundBeerList = false;
        }
    }
*/


}