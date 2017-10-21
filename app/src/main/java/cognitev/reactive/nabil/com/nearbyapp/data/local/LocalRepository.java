package cognitev.reactive.nabil.com.nearbyapp.data.local;

import cognitev.reactive.nabil.com.nearbyapp.data.Repository;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public class LocalRepository implements Repository {


    @Override
    public Observable<ApiResponse> getData(String location, int radius, String date) {
        return null;
    }

    //let the input be object
    @Override
    public boolean saveData(String location, int radius, String date) {
        //insert to local database

        return false;
    }
}
