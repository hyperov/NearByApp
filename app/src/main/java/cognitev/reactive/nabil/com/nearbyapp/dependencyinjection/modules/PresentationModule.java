package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashContract;
import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashPresenter;
import cognitev.reactive.nabil.com.nearbyapp.splash.usecase.GetLocationsUseCase;
import dagger.Module;
import dagger.Provides;

/**
 * Created by anabil on 10/21/2017.
 */

@Module
public class PresentationModule {

    @Singleton
    @Provides
    public SplashContract.Presenter provideSplashPresenter(GetLocationsUseCase useCase) {
        return new SplashPresenter(useCase);
    }

//    @Singleton
//    @Provides
//    public SplashContract.View provideSplashActivity() {
//        return new SplashActivity();
//    }
}
