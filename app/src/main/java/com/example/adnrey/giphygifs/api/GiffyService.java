package com.example.adnrey.giphygifs.api;

import com.example.andrey.giphygifs.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by garu on 06/10/17.
 */

public class GiffyService {

    private GiffyApi api;

    public GiffyService(String endpoint) {
        this.api = new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GiffyApi.class);
    }

    public GiffyApi getApi() {
        return api;
    }

}
