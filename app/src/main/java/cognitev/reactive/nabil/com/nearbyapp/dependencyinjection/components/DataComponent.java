package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.MainRepository;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.DataModule;
import dagger.Component;

/**
 * Created by anabil on 10/21/2017.
 */


@Singleton
@Component(modules = {DataModule.class})
public interface DataComponent {
    void inject(MainRepository mainRepository);
}
