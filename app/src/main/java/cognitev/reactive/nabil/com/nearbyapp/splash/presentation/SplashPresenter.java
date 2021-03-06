package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

import android.util.Log;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import cognitev.reactive.nabil.com.nearbyapp.Utils.AppUtils;
import cognitev.reactive.nabil.com.nearbyapp.Utils.Constants;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.GroupsItem;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.ItemsItem;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Response;
import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Warning;
import cognitev.reactive.nabil.com.nearbyapp.splash.usecase.UseCase;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.android.gms.internal.zzt.TAG;

/**
 * Created by anabil on 10/20/2017.
 */

@Singleton
public class SplashPresenter implements SplashContract.Presenter {

    private final CompositeDisposable mSubscriptions;


    public UseCase useCase;


    @Nullable
    public SplashContract.View view;
    private Observable<SplashViewModel> splashViewModelObservable;
    private Observable<ImageViewModel> imageObservable;

    @Inject
    public SplashPresenter(UseCase useCase) {
        this.useCase = useCase;

        mSubscriptions = new CompositeDisposable();

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
    public void takeView(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }


    @Override
    public Observable<ApiResponseLocation> getLocations(String location, int radius, boolean connection) {

        view.showLoadingBar(true);

        Observable<ApiResponseLocation> locations = useCase.getLocations(location, radius, connection);
        if (location == null) {
            view.showLoadingBar(false);
            view.displayError();
            return null;
        }
        mSubscriptions.add(locations.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(apiResponseLocation ->
                        {
                            Response response = apiResponseLocation.getResponse();
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
                                displayLocationsViewData(items, connection);
//                                String name = groupsItem.getName();
//                                groupsItem.getItems().

                            }
                        }, throwable -> {
                            view.showLoadingBar(false);
                            view.displayError();
                        }
                ));

//        locations
        return null;
    }

    private void displayLocationsViewData(List<ItemsItem> items, boolean connection) {
        splashViewModelObservable = Observable.fromIterable(items).flatMap(itemsItem ->
                {
                    String name = itemsItem.getVenue().getName();
                    String id = itemsItem.getVenue().getId();
                    String address = itemsItem.getVenue().getLocation().getAddress();
                    SplashViewModel splashViewModel = new SplashViewModel(name, id, address);
                    return Observable.just(splashViewModel);

                }
        );
        mSubscriptions.add(
                splashViewModelObservable.
                        toList().
                        subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe((splashViewModels, throwable) -> {
                            view.showLoadingBar(false);
                            if (throwable != null)
                                view.displayError();
                            else
                                view.displayData(splashViewModels);

                        }));
        if (connection) {
            mSubscriptions.add(
                    splashViewModelObservable
                            .flatMap(splashViewModel ->
                            {
                                String id = splashViewModel.getId();
                                Observable<ImageViewModel> imageObservable = getImageObservable(id, 1);
                                ImageViewModel imageViewModel = imageObservable.
                                        doOnError(throwable -> Log.e(TAG, "displayLocationsViewData: get image", throwable))
                                        .blockingFirst();

                                String url = imageViewModel.getPrefix()
                                        .concat(Constants.IMAGE_SIZE)
                                        .concat(imageViewModel.getSuffix());
                                splashViewModel.setImageUrl(url);
                                return Observable.just(splashViewModel);
                            })
                            .toList()
                            .subscribeOn(Schedulers.io()).
                            observeOn(AndroidSchedulers.mainThread()).
                            subscribe((splashViewModels, throwable) ->
                            {
                                if (throwable != null && !AppUtils.cache)
                                    view.displayError();
                                else
                                    view.updateData(splashViewModels);

                            }));
        }
    }

    @Override
    public Observable<ImageViewModel> getImageObservable(String locationId, int limit) {
        imageObservable = useCase.getLocationPhoto(locationId, limit)
                .flatMap(apiResponsePhoto -> {
                    List<cognitev.reactive.nabil.com.nearbyapp.data.model.photo.ItemsItem> items = apiResponsePhoto.getResponse().getPhotos().getItems();
                    String prefix = "", suffix = "";
                    if (!items.isEmpty()) {
                        prefix = items.get(0).getPrefix();
                        suffix = items.get(0).getSuffix();
                    }

                    ImageViewModel imageViewModel = new ImageViewModel(prefix, suffix);
                    return Observable.just(imageViewModel);
                });
        return imageObservable;
    }


}
