package cognitev.reactive.nabil.com.nearbyapp.data.local;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.Repository;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

@Singleton
public class LocalRepository implements Repository {

    public LocalRepository() {
    }

    @Override
    public Observable<ApiResponse> getData(String location, int radius, String date) {
        //not implemented yet
        return null;
    }

    //let the input be object
    @Override
    public boolean saveData(String location, int radius, String date) {
        //not implemented yet
        //insert to local database

        return false;
    }
}
