package jsonstructure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Andrey on 21.05.2017.
 */

public class JsonResponse {
    @SerializedName("data")
    @Expose
    private ArrayList<Gif> gifs = new ArrayList<>();

    public ArrayList<Gif> getGifs(){
        return gifs;
    }

}
