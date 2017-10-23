package cognitev.reactive.nabil.com.nearbyapp.data.local.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by anabil on 10/23/2017.
 */

public class LocationDb extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String address;
    private String locationId;


    public LocationDb(String name, String address, String locationId) {
        this.name = name;
        this.address = address;
        this.locationId = locationId;
    }

    public LocationDb() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
