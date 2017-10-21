package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

import java.util.List;

import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponse;
import cognitev.reactive.nabil.com.nearbyapp.data.model.GroupsItem;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ItemsItem;
import cognitev.reactive.nabil.com.nearbyapp.data.model.Response;
import cognitev.reactive.nabil.com.nearbyapp.data.model.Warning;
import cognitev.reactive.nabil.com.nearbyapp.splash.usecase.UseCase;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anabil on 10/20/2017.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private final CompositeDisposable mSubscriptions;
    private UseCase useCase;

    private SplashContract.View view;

    public SplashPresenter(UseCase useCase, SplashContract.View view) {
        this.useCase = useCase;
        this.view = view;

        mSubscriptions = new CompositeDisposable();

        view.setPresenter(this);
    }

    /**
     * unimplemented
     */
    @Override
    public void subscribe() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }


    @Override
    public Observable<ApiResponse> getLocations(String location, int radius, boolean connection) {

        view.showLoadingBar(true);

        Observable<ApiResponse> locations = useCase.getLocations(location, radius, connection);
        mSubscriptions.add(locations.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(apiResponse ->
                        {
                            Response response = apiResponse.getResponse();
                            Warning warning = response.getWarning();

                            if (response.getTotalResults() == 0)
                                view.EmptyData();

                            else if (warning != null &&
                                    warning.getText() != null &&
                                    !warning.getText().isEmpty()) {
                                view.displayError();
                            } else {
                                GroupsItem groupsItem = response.getGroups().get(0);
                                List<ItemsItem> items = groupsItem.getItems();
                                displayLocationsViewData(items);
//                                String name = groupsItem.getName();
//                                groupsItem.getItems().

                            }
                        }, throwable -> view.displayError(), () -> view.showLoadingBar(false)
                ));

//        locations
        return null;
    }

    private void displayLocationsViewData(List<ItemsItem> items) {
        mSubscriptions.add(Observable.fromIterable(items).flatMap(itemsItem ->
                {
                    String name = itemsItem.getVenue().getName();
                    String id = itemsItem.getVenue().getId();
                    String address = itemsItem.getVenue().getLocation().getAddress();
                    SplashViewModel splashViewModel = new SplashViewModel(name, id, address);
                    return Observable.just(splashViewModel);

                }
        ).toList()
                .subscribeOn(Schedulers.io()).
                        observeOn(AndroidSchedulers.mainThread()).
                        subscribe((splashViewModels, throwable) ->
                        {
                            if (throwable != null)
                                view.displayData();
                            else
                                view.displayData();

                        }));
    }
}
