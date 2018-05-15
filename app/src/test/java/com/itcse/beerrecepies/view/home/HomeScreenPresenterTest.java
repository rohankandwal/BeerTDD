package com.itcse.beerrecepies.view.home;

import com.itcse.beerrecepies.model.data.BeerDetails;
import com.itcse.beerrecepies.model.repository.ApiInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.mock.Calls;

@RunWith(MockitoJUnitRunner.class)
public class HomeScreenPresenterTest {

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
        Mockito.when(apiInterface.getBeerList()).thenReturn(Calls.response(beerDetailsList));
        // When
        presenter.getBeers();
        // Then
        Mockito.verify(view).setBeerList(beerDetailsList);
    }

    @Test
    public void noBeerFound() {
        // Given
        Mockito.when(apiInterface.getBeerList()).thenReturn(Calls.response(Collections.<BeerDetails>emptyList()));
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