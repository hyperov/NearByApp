package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.UseCaseModule;
import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashPresenter;
import dagger.Component;

/**
 * Created by anabil on 10/21/2017.
 */

@Singleton
@Component(modules = {UseCaseModule.class})
public interface UseCaseComponent {

    void inject(SplashPresenter splashPresenter);

}
