package com.silva021.pokedex.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//            .baseUrl("http://localhost:8090/api/pokemon/")
            .baseUrl("http://192.168.172.2:8090/api/pokemon/")
            .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofitInstance = retrofitBuilder.build();

    public static <T> T createService(Class<T> serviceClass){
        return retrofitInstance.create(serviceClass);
    }
}
