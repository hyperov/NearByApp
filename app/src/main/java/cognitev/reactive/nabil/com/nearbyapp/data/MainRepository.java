package cognitev.reactive.nabil.com.nearbyapp.data;

import java.io.IOException;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/21/2017.
 */

public class MainRepository implements Repository {

    private Repository localRepository;
    private Repository remoteRepository;

    public MainRepository(Repository localRepository, Repository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public Observable<ApiResponse> getData(String location, int radius, String date) {
        return remoteRepository.getData(location, radius, date);

    }

    @Override
    public boolean saveData(String location, int radius, String date) {
        return localRepository.saveData(location, radius, date);
    }

    public Observable<ApiResponse> getLocationsAndSaveToCashe(
            String location,
            int radius,
            String date,
            boolean connection) {

        if (connection)
            return getData(location, radius, date).
                    doOnNext(response -> saveData(location, radius, date));
        else
            return localRepository.getData(location, radius, date);
    }


}
