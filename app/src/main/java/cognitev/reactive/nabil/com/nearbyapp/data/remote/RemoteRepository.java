package cognitev.reactive.nabil.com.nearbyapp.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.Repository;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Venue;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

@Singleton
public class RemoteRepository implements Repository {

    @Inject
    public RetrofitClient retrofitClient;

    @Inject
    public RemoteRepository(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    @Override
    public Observable<ApiResponseLocation> getData(String location, int radius, String date) {
        return retrofitClient.getLocations(location, radius, date);
    }

    @Override
    public boolean saveData(Venue venue) {
        return false;
    }



    @Override
    public Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit, String date) {

        return retrofitClient.getLocationPhoto(locationId, limit, date).take(1);
    }
}
