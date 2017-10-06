package com.example.adnrey.giphygifs.api;

import com.example.adnrey.giphygifs.api.responses.JsonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Andrey on 24.05.2017.
 */

public interface GiffyApi {
    @GET("search")
    Call<JsonResponse> getData(@Query("q") String query, @Query("api_key") String publicKey);
}
