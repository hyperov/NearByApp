package cognitev.reactive.nabil.com.nearbyapp.data.local;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.data.Repository;
import cognitev.reactive.nabil.com.nearbyapp.data.local.realm.LocationDb;
import cognitev.reactive.nabil.com.nearbyapp.data.local.realm.RealmRepository;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.ItemsItem;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Location;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Venue;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Warning;
import io.reactivex.Observable;

/**
 * Created by anabil on 10/20/2017.
 */

@Singleton
public class LocalRepository implements Repository {

    @Inject
    RealmRepository realmRepository;

    @Inject
    public LocalRepository() {
    }

    @Override
    public Observable<ApiResponseLocation> getData(String location, int radius, String date) {
        //not implemented yet
        ApiResponseLocation apiResponseLocation = new ApiResponseLocation();


        Observable<ItemsItem> itemsItemObservable = Observable.just(apiResponseLocation).flatMap(
                apiResponseLocation2 ->
                        Observable.fromIterable(apiResponseLocation2.
                                getResponse().
                                getGroups().
                                get(0).
                                getItems()));

        Observable<LocationDb> locations = realmRepository.getLocations();


        Observable<ItemsItem> zip = Observable.zip(itemsItemObservable, locations, (itemsItem, locationDb) ->
        {
            String name = locationDb.getName();
            String id = locationDb.getId();
            String address = locationDb.getAddress();

            Venue venue = new Venue();
            venue.setName(name);
            venue.setId(id);

            Location location1 = new Location();
            location1.setAddress(address);

            venue.setLocation(location1);
            itemsItem.setVenue(venue);
            return itemsItem;
        });

        List<ItemsItem> itemsItems = zip.toList().blockingGet();

        apiResponseLocation.getResponse().setWarning(new Warning());

        apiResponseLocation.getResponse().getGroups().get(0).setItems(itemsItems);


        return Observable.just(apiResponseLocation);
    }

    //let the input be object
    @Override
    public boolean saveData(Venue venue) {
        //not implemented yet
        //insert to local database
        realmRepository.saveLocation(venue.getName(), venue.getId(), venue.getLocation().getAddress());
        return true;
    }

    @Override
    public Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit, String date) {
        //not implemented

        return null;
    }
}
