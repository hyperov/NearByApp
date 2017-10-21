package cognitev.reactive.nabil.com.nearbyapp.splash.usecase;

import cognitev.reactive.nabil.com.nearbyapp.data.MainRepository;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public class GetLocationsUseCase implements UseCase {

    private MainRepository mainRepository;

    public GetLocationsUseCase(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public Observable<ApiResponse> getLocations(String location, int radius, boolean connection) {
        //get data from local if no connection
        String date = "";
        return mainRepository.getLocationsAndSaveToCashe(location, radius, date, connection);

    }
}
