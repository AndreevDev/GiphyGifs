package ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ui.activity.giffy.GiffyApi;

import com.example.andrey.giphygifs.BuildConfig;
import com.example.andrey.giphygifs.R;

import java.util.ArrayList;
import java.util.List;

import jsonstructure.Gif;
import jsonstructure.JsonResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String query = "";
    private RecyclerView gifsList;
    private List<String> data;
    private DataAdapter adapter;
    private TextView textView;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (EditText) findViewById(R.id.queryGifs);
        gifsList = (RecyclerView) findViewById(R.id.gifsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        gifsList.setLayoutManager(layoutManager);
        data = new ArrayList<>();

    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void onSearchClick(View view) {
        data.clear();
        query = textView.getText().toString().replace(' ', '+');
        loadJSON();
    }

    private void loadJSON() {

        GiffyApi request = getRetrofit().create(GiffyApi.class);
        Call<JsonResponse> call = request.getData(query, getResources().getString(R.string.GIFFY_API_KEY));

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
