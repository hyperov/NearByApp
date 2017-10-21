package cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.components;

import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.dependencyinjection.modules.PresentationModule;
import dagger.Component;

/**
 * Created by anabil on 10/21/2017.
 */

@Singleton
@Component(modules = {PresentationModule.class})
public class PresentationComponent {
}
