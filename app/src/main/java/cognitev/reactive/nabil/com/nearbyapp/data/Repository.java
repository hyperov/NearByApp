package cognitev.reactive.nabil.com.nearbyapp.data;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public interface Repository {

    Observable<ApiResponse> getData(String location, int radius, String date);

    boolean saveData(String location, int radius, String date);
}
