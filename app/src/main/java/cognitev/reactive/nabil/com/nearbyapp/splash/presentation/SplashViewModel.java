package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

/**
 * Created by anabil on 10/21/2017.
 */

public class SplashViewModel {

    private String name;
    private String id;
    private String address;
    private String imageUrl;



    public SplashViewModel(String name, String id, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SplashViewModel() {
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


}
