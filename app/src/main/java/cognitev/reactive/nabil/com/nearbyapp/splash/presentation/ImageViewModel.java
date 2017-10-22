package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

/**
 * Created by anabil on 10/22/2017.
 */

public class ImageViewModel {

    private String prefix;
    private String suffix;

    public ImageViewModel(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public ImageViewModel() {
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
