package cognitev.reactive.nabil.com.nearbyapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anabil on 10/22/2017.
 */

public class ApiResponseParent {

    @SerializedName("meta")
    private Meta meta;

    public void setMeta(Meta meta){
        this.meta = meta;
    }

    public Meta getMeta(){
        return meta;
    }

}
