package cognitev.reactive.nabil.com.nearbyapp.app;

import android.app.Activity;
import android.app.Application;

import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components.AppComponent;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components.DaggerAppComponent;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components.DataComponent;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components.UseCaseComponent;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.DataModule;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.PresentationModule;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.UseCaseModule;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;

/**
 * Created by anabil on 10/21/2017.
 */

public class App extends Application implements HasActivityInjector {

    static AppComponent appComponent;
    static DataComponent dataComponent;
    static UseCaseComponent useCaseComponent;
//    static PresentationComponent presentationComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    public static AppComponent getAppComponent() {

        return appComponent;

    }

    public static DataComponent getDataComponent() {
        return dataComponent;
    }

    public static UseCaseComponent getUseCaseComponent() {
        return useCaseComponent;
    }

//    public static PresentationComponent getPresentationComponent() {
//        return presentationComponent;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
//        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        dataComponent = DaggerDataComponent.builder().dataModule(new DataModule()).build();
//        useCaseComponent = DaggerUseCaseComponent.builder().useCaseModule(new UseCaseModule()).build();
//        presentationComponent = DaggerPresentationComponent.builder().presentationModule(new PresentationModule()).build();

        DaggerAppComponent.builder().application(this).dataModules(new DataModule())
                .presentation(new PresentationModule()).UseCases(new UseCaseModule())
                .build().inject(this);

        Realm.init(this);


//        appComponent.inject(this);
        Stetho.initializeWithDefaults(this);


//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                        .build());

    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
