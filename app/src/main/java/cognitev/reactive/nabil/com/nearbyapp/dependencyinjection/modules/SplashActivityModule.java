package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules;

import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.SplashActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by anabil on 10/21/2017.
 */


@Module
public abstract class SplashActivityModule {


    @ContributesAndroidInjector(modules = PresentationModule.class)
    abstract SplashActivity provideActivity();

}
