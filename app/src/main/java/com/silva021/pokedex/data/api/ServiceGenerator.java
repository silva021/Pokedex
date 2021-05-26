package com.silva021.pokedex.data.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static String urlBase = "https://silva021-pokedex-api.herokuapp.com/";

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofitInstance = retrofitBuilder.build();

    public static <T> T createService(Class<T> serviceClass) {
        return retrofitInstance.create(serviceClass);
    }
}
