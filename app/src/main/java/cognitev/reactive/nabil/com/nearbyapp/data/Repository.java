package cognitev.reactive.nabil.com.nearbyapp.data;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Venue;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public interface Repository {

    Observable<ApiResponseLocation> getData(String location, int radius, String date);

    boolean saveData(Venue venue);

    Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit, String date);
}
