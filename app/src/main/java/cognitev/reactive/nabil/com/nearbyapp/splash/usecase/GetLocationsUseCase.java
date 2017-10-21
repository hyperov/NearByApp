package cognitev.reactive.nabil.com.nearbyapp.splash.usecase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.MainRepository;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
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
    public Observable<ApiResponse> getLocations(String location, int radius, boolean connection) {

        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
        String date = formattedDate.format(Calendar.getInstance().getTime());

        return mainRepository.getLocationsAndSaveToCashe(location, radius, date, connection);

    }
}
