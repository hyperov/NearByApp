package cognitev.reactive.nabil.com.nearbyapp.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.Local;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.Remote;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/21/2017.
 */

@Singleton
public class MainRepository implements Repository {

    private Repository localRepository;
    private Repository remoteRepository;

    @Inject
    public MainRepository(@Local Repository localRepository, @Remote Repository remoteRepository) {
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
