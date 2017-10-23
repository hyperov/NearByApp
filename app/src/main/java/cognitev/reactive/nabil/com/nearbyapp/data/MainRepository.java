package cognitev.reactive.nabil.com.nearbyapp.data;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Venue;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.Local;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.Remote;
import io.reactivex.Observable;

import static com.google.android.gms.internal.zzt.TAG;

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
//        App.getDataComponent().inject(this);
    }

    @Override
    public Observable<ApiResponseLocation> getData(String location, int radius, String date) {
        return remoteRepository.getData(location, radius, date);

    }

    @Override
    public boolean saveData(Venue venue) {
        return localRepository.saveData(venue);
    }

    public Observable<ApiResponseLocation> getLocationsAndSaveToCashe(
            String location,
            int radius,
            String date,
            boolean connection) {

        if (connection)
            return getData(location, radius, date).
                    doOnNext(response -> {
                        Venue venue = response.getResponse()
                                .getGroups().get(0).getItems().get(0).getVenue();

                        saveData(venue);
                    }).
                    doOnComplete(() -> Log.e(TAG, "getLocationsAndSaveToCashe completed: ")).
                    doOnError(throwable -> Log.e(TAG, "getLocationsAndSaveToCashe: " + throwable.getMessage()));
        else
            return localRepository.getData(location, radius, date);
    }

    @Override
    public Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit, String date) {

        return remoteRepository.getLocationPhoto(locationId, limit, date);
    }


}
