package cognitev.reactive.nabil.com.nearbyapp.data.local.realm;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by anabil on 10/23/2017.
 */

public class RealmRepository {


    private Realm realm;
    private static int primary = 0;

    @Inject
    public RealmRepository() {

    }

    public boolean saveLocation(String name, String locationId, String address) {
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm -> {
            LocationDb locationDb = realm.createObject(LocationDb.class, String.valueOf(++primary));
            locationDb.setAddress(address);
            locationDb.setLocationId(locationId);
            locationDb.setName(name);
        }
//        ,error -> Log.e("saveLocation: ",error.getMessage() )
        );

//        realm.commitTransaction();
//        try {
//            realm.insert(locationDb);
//        } catch (Exception e) {
//
//        }

        return true;
    }

    public void deleteAll() {
        realm.deleteAll();
    }

    public Observable<LocationDb> getLocations() {
        realm = Realm.getDefaultInstance();
        RealmResults<LocationDb> realmResults = realm.where(LocationDb.class).findAll();
        int size = realmResults.size();
        return Observable.fromIterable(realmResults.subList(0, realmResults.size() ));
    }
}
