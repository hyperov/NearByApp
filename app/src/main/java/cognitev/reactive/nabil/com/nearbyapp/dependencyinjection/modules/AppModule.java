package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.app.App;
import dagger.Module;
import dagger.Provides;

/**
 * Created by anabil on 10/21/2017.
 */



@Module
public class AppModule {

    private App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Singleton
    @Provides
    public App provideApp() {
        return mApp;
    }

//    @Singleton
//    @Provides
//    public Retrofit provideRetrofit() {
//        return new Retrofit.Builder().baseUrl("http://services.hanselandpetal.com").
//                addConverterFactory(GsonConverterFactory.create()).build();
//    }




}
