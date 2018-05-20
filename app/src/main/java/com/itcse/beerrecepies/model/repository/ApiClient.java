package com.itcse.beerrecepies.model.repository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.punkapi.com/v2/";

    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    public static ApiInterface getAPI() {

        if (retrofit == null) {
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            final OkHttpClient.Builder builder = new OkHttpClient.Builder();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }


}
