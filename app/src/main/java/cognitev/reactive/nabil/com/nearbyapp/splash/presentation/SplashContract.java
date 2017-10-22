package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

import java.util.List;

import cognitev.reactive.nabil.com.nearbyapp.BasePresenter;
import cognitev.reactive.nabil.com.nearbyapp.BaseView;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

public interface SplashContract {

    interface Presenter extends BasePresenter<View> {

        Observable<ApiResponseLocation> getLocations(String location, int radius, boolean connection);

        Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit, String date);
    }

    interface View extends BaseView<Presenter> {

        //show progress bar
        void showLoadingBar(boolean isShown);

        //results are here ==> send to UI
        void displayData(List<SplashViewModel> splashViewModels);

        //no results found
        void EmptyData();

        //error happened..failing to connect
        void displayError();

        void updateData(List<SplashViewModel> splashViewModels);


//        void finishLoading();

    }
}
