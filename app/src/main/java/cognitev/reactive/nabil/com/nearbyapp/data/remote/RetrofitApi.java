package cognitev.reactive.nabil.com.nearbyapp.data.remote;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anabil on 10/20/2017.
 */

public interface RetrofitApi {

    @GET("explore")
    Observable<ApiResponse> getLocations(@Query("ll") String location,
                                         @Query("radius") int radius,
                                         @Query("v") String date);

}
