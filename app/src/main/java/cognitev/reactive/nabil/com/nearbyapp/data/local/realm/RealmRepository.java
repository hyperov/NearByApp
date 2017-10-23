package cognitev.reactive.nabil.com.nearbyapp.data.local.realm;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by anabil on 10/23/2017.
 */

public class RealmRepository {


    private final Realm realm;

    @Inject
    public RealmRepository() {
        realm = Realm.getDefaultInstance();

    }

   public boolean saveLocation(String name, String locationId, String address) {
        LocationDb locationDb = new LocationDb(name, address, locationId);
        try {
            realm.insert(locationDb);
        } catch (Exception e) {

        }

        return true;
    }

   public void deleteAll() {
        realm.deleteAll();
    }

   public Observable<LocationDb> getLocations() {
        RealmResults<LocationDb> realmResults = realm.where(LocationDb.class).findAll();
        int size = realmResults.size();
        return Observable.fromIterable(realmResults.subList(0, realmResults.size() - 1));
    }
}
