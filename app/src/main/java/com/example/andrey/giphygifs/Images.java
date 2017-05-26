package com.example.andrey.giphygifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrey on 24.05.2017.
 */

public class Images {
    @SerializedName("original")
    @Expose
    private OriginalURL originalURL;

    public OriginalURL getOriginalURL() {
        return originalURL;
    }

}
