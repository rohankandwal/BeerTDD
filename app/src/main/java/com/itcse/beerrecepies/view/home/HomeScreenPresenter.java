package com.itcse.beerrecepies.view.home;

import android.support.annotation.NonNull;

import com.itcse.beerrecepies.R;
import com.itcse.beerrecepies.model.data.BeerDetails;
import com.itcse.beerrecepies.model.repository.ApiInterface;

import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class HomeScreenPresenter implements HomeScreenContract.Presenter {

    private HomeScreenContract.View view;
    private ApiInterface apiInterface;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    HomeScreenPresenter(@NonNull final HomeScreenContract.View view,
                        @NonNull final ApiInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    @Override
    public void getBeers() {
        final Observable<List<BeerDetails>> beerList = apiInterface.getBeerList();
        beerList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                if(throwable instanceof UnknownHostException) {
                                    return view.showError(R.string.error_internet, beerList);
                                }
                                return Observable.just(throwable);
                            }
                        });
                    }
                })
                .subscribe(new Observer<List<BeerDetails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<BeerDetails> beerDetails) {
                        Timber.d("got data");
                        if (beerDetails.size() > 0) {
                            view.setBeerList(beerDetails);
                        } else {
                            view.noBeerFound();
                        }
                        view.showProgress(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("Error e = %s", e.getMessage());
                        view.showProgress(false);
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete");
                        view.showProgress(false);
                    }
                });
    }

    @Override
    public void destroy() {
        view = null;
        apiInterface = null;
        compositeDisposable.dispose();
    }
}
