package com.example.andrey.giphygifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 26.05.2017.
 */

class OriginalURL {
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

}
