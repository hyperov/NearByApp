package cognitev.reactive.nabil.com.nearbyapp.data.model;

import com.google.gson.annotations.SerializedName;

import cognitev.reactive.nabil.com.nearbyapp.data.model.photo.ResponsePhoto;

/**
 * Created by anabil on 10/22/2017.
 */

public class ApiResponsePhoto extends ApiResponseParent {

    @SerializedName("response")
    private ResponsePhoto response;

    public ResponsePhoto getResponse() {
        return response;
    }

    public void setResponse(ResponsePhoto response) {
        this.response = response;
    }
}
