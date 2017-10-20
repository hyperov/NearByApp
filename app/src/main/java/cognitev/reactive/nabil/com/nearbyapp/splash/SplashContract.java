package cognitev.reactive.nabil.com.nearbyapp.splash;

import cognitev.reactive.nabil.com.nearbyapp.BasePresenter;
import cognitev.reactive.nabil.com.nearbyapp.BaseView;

/**
 * Created by anabil on 10/20/2017.
 */

public interface SplashContract {

    public interface Presenter extends BasePresenter {

        void getData();
    }

    public interface View extends BaseView<Presenter> {

        //show progress bar
        void showLoadingBar(boolean isShown);

        //results are here ==> send to UI
        void displayData();

        //no results found
        void EmptyData();

        //error happened..failing to connect
        void displayError();


//        void finishLoading();

    }
}
