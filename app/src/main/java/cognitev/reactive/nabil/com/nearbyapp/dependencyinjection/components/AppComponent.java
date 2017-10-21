package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components;

import android.app.Application;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.app.App;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.AppModule;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.DataModule;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.PresentationModule;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.SplashActivityModule;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.UseCaseModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by anabil on 10/21/2017.
 */

@Singleton
@Component(modules = {AppModule.class, AndroidSupportInjectionModule.class,
        SplashActivityModule.class, PresentationModule.class,
        UseCaseModule.class, DataModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(App mApp);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder dataModules(DataModule dataModule);

        @BindsInstance
        Builder UseCases(UseCaseModule useCaseModule);

        @BindsInstance
        Builder presentation(PresentationModule presentationModule);

        AppComponent build();
    }

}
