package cognitev.reactive.nabil.com.nearbyapp.splash.usecase;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public interface UseCase {

    Observable<ApiResponseLocation> getLocations(String location, int radius, boolean connection);

    Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit);
}
