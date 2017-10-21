package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.app.App;
import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.AppModule;
import dagger.Component;

/**
 * Created by anabil on 10/21/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App mApp);

}
