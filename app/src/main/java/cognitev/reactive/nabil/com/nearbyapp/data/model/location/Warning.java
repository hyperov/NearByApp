package cognitev.reactive.nabil.com.nearbyapp.data.model.location;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anabil on 10/21/2017.
 */

public class Warning {

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
