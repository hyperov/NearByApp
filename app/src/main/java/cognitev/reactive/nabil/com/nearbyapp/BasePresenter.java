package cognitev.reactive.nabil.com.nearbyapp;

/**
 * Created by anabil on 10/20/2017.
 */

public interface BasePresenter<T> {

    void subscribe();

    void unsubscribe();

    void takeView(T view);

    /**
     * Drops the reference to the view when destroyed
     */
    void dropView();
}
