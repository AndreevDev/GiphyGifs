package com.example.adnrey.giphygifs.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 21.05.2017.
 */

public class Gif {
    @SerializedName("images")
    @Expose
    private Images images;

    public Images getImages() {
        return images;
    }

}
