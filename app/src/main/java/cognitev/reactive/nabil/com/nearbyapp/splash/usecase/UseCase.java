package cognitev.reactive.nabil.com.nearbyapp.splash.usecase;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public interface UseCase {

    Observable<ApiResponse> getLocations(String location, int radius, boolean connection);
}
