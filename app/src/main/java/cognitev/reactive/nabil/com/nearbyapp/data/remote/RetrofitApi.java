package cognitev.reactive.nabil.com.nearbyapp.data.remote;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anabil on 10/20/2017.
 */

public interface RetrofitApi {

    @GET("explore")
    Observable<ApiResponseLocation> getLocations(@Query("ll") String location,
                                                 @Query("radius") int radius,
                                                 @Query("v") String date);

    @GET("{VENUE_ID}/photos")
    Observable<ApiResponsePhoto> getPhotos(@Path("VENUE_ID") String locationId,
                                           @Query("limit") int limit,
                                           @Query("v") String date);

}
