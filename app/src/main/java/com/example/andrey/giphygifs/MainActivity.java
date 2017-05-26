package com.example.andrey.giphygifs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String query = "funny+cat";
    String apiKey = "dc6zaTOxFJmzC";
    RecyclerView gifsList;
    ArrayList<String> data;
    DataAdapter adapter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (EditText) findViewById(R.id.queryGifs);
        gifsList = (RecyclerView) findViewById(R.id.gifsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        gifsList.setLayoutManager(layoutManager);
        data = new ArrayList<>();

    }

    public void onSearchClick(View view){
        data.clear();
        query = textView.getText().toString().replace(' ','+');
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.giphy.com/v1/gifs/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GiffyApi request = retrofit.create(GiffyApi.class);
        Call<JsonResponse> call = request.getData(query, apiKey);

        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if (response.isSuccessful()) {
                    for (Gif gif : response.body().getGifs()) {
                        data.add(gif.getImages().getOriginalURL().getUrl());
                    }
                    adapter = new DataAdapter(data);
                    gifsList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
