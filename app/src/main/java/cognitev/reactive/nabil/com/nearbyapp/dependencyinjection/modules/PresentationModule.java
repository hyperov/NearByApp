package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashActivity;
import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashContract;
import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashPresenter;
import cognitev.reactive.nabil.com.nearbyapp.splash.usecase.UseCase;
import dagger.Module;
import dagger.Provides;

/**
 * Created by anabil on 10/21/2017.
 */

@Module
public class PresentationModule {

    @Singleton
    @Provides
    public SplashContract.Presenter provideSplashPresenter(UseCase useCase, SplashContract.View view) {
        return new SplashPresenter(useCase, view);
    }

    @Singleton
    @Provides
    public SplashContract.View provideSplashActivity() {
        return new SplashActivity();
    }
}
