package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.MainRepository;
import cognitev.reactive.nabil.com.nearbyapp.splash.usecase.GetLocationsUseCase;
import cognitev.reactive.nabil.com.nearbyapp.splash.usecase.UseCase;
import dagger.Module;
import dagger.Provides;

/**
 * Created by anabil on 10/21/2017.
 */

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    public UseCase provideUseCase(MainRepository mainRepository) {
        return new GetLocationsUseCase(mainRepository);
    }
}
