package cognitev.reactive.nabil.com.nearbyapp.splash.usecase;

import javax.inject.Inject;
import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.Utils.AppUtils;
import cognitev.reactive.nabil.com.nearbyapp.data.MainRepository;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

@Singleton
public class GetLocationsUseCase implements UseCase {

    public MainRepository mainRepository;

    @Inject
    public GetLocationsUseCase(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public Observable<ApiResponseLocation> getLocations(String location, int radius, boolean connection) {


        return mainRepository.getLocationsAndSaveToCashe(location, radius, AppUtils.getCurrentDate(), connection);

    }

    @Override
    public Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit) {

        return mainRepository.getLocationPhoto(locationId, limit, AppUtils.getCurrentDate());
    }
}
