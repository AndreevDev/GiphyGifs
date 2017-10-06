package com.example.adnrey.giphygifs.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adnrey.giphygifs.api.GiffyService;
import com.example.adnrey.giphygifs.api.responses.Gif;
import com.example.adnrey.giphygifs.api.responses.JsonResponse;
import com.example.adnrey.giphygifs.ui.adapters.DataAdapter;
import com.example.andrey.giphygifs.BuildConfig;
import com.example.andrey.giphygifs.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String query = "";
    private RecyclerView gifsList;
    private List<String> data;
    private DataAdapter adapter;
    private TextView textView;

    private GiffyService giffy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (EditText) findViewById(R.id.queryGifs);
        gifsList = (RecyclerView) findViewById(R.id.gifsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        gifsList.setLayoutManager(layoutManager);
        data = new ArrayList<>();

        this.giffy = new GiffyService(BuildConfig.HOST);
    }


    public void onSearchClick(View view) {
        data.clear();
        query = textView.getText().toString().replace(' ', '+');
        loadJSON();
    }

    private void loadJSON() {

        Call<JsonResponse> call = this.giffy.getApi().getData(query, getResources().getString(R.string.GIFFY_API_KEY));

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
