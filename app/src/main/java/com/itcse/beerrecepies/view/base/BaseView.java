package com.itcse.beerrecepies.view.base;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public interface BaseView {
    /**
     * Function to show or hide progress
     * @param showProgress true to show progress or hide progress
     */
    void showProgress(final boolean showProgress);

    Observable<?> showError(@Nullable final int errorMessage,
                            @NonNull final Observable throwable);
}
