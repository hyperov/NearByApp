package cognitev.reactive.nabil.com.nearbyapp.data.remote;

import cognitev.reactive.nabil.com.nearbyapp.data.Repository;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public class RemoteRepository implements Repository {

    private RetrofitClient retrofitClient;

    public RemoteRepository(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    @Override
    public Observable<ApiResponse> getData(String location, int radius, String date) {
        return retrofitClient.getLocations(location, radius, date);
    }

    @Override
    public boolean saveData(String location, int radius, String date) {
        //not implemented
        return false;
    }
}
