package com.example.andrey.giphygifs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Andrey on 24.05.2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    ArrayList<String> gifsURLs;
    private ImageView gif;
    Context mContext;

    public DataAdapter(ArrayList<String> gifsURLs) {
        this.gifsURLs = gifsURLs;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gif_item, viewGroup, false);
        mContext = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        Glide.with(mContext)
                .load(gifsURLs.get(i))
                .into(gif);
    }

    @Override
    public int getItemCount() {
        return gifsURLs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view) {
            super(view);
            gif = (ImageView) view.findViewById(R.id.gifView);
        }
    }
}
