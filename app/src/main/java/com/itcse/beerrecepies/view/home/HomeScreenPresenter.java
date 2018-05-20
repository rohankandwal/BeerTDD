package com.itcse.beerrecepies.view.home;

import android.support.annotation.NonNull;

import com.itcse.beerrecepies.model.data.BeerDetails;
import com.itcse.beerrecepies.model.repository.ApiInterface;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private HomeScreenContract.View view;
    private ApiInterface apiInterface;

    HomeScreenPresenter(@NonNull final HomeScreenContract.View view,
                        @NonNull final ApiInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    @Override
    public void getBeers() {
        apiInterface.getBeerList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<BeerDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<BeerDetails> beerDetails) {
                        Timber.d("got data");
                        if (beerDetails.size() > 0) {
                            view.setBeerList(beerDetails);
                        } else {
                            view.noBeerFound();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("Error e = %s", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete");
//                        view.noBeerFound();
                    }
                });
    }
}
