package cognitev.reactive.nabil.com.nearbyapp.data.remote;

import cognitev.reactive.nabil.com.nearbyapp.data.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anabil on 10/20/2017.
 */

public interface RetrofitApi {

    @GET("explore")
    Call<Response> getLocations(@Query("ll") String location, @Query("radius") int radius
    ,@Query("v") String date);

}
