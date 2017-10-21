package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.Repository;
import cognitev.reactive.nabil.com.nearbyapp.data.local.LocalRepository;
import cognitev.reactive.nabil.com.nearbyapp.data.remote.RemoteRepository;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.Local;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.Remote;
import dagger.Module;
import dagger.Provides;

/**
 * Created by anabil on 10/21/2017.
 */

@Module
public class DataModule {




    @Singleton
    @Local
    @Provides
    Repository provideTasksLocalDataSource(LocalRepository dataSource) {
        return dataSource;
    }

    @Singleton
    @Remote
    @Provides
    Repository provideTasksRemoteDataSource(RemoteRepository dataSource) {
        return dataSource;
    }

}
