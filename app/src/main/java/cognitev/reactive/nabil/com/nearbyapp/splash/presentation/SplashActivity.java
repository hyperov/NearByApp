package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cognitev.reactive.nabil.com.nearbyapp.BaseActivity;
import cognitev.reactive.nabil.com.nearbyapp.R;
import cognitev.reactive.nabil.com.nearbyapp.Utils.AppUtils;
import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.adapter.LocationAdapter;

import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.LOCATION_KEY;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.REQUESTING_LOCATION_UPDATES_KEY;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.REQUEST_CODE_GET_LOCATION_PERMISSION;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.SETTING_SHARED_VALUE_REALTIME;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.SETTING_SHARED_VALUE_SINGLE_UPDATE;

public class SplashActivity extends BaseActivity implements SplashContract.View, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = SplashActivity.class.getSimpleName();
    @Inject
    SplashPresenter presenter;

    RecyclerView locationRecyclerView;
    LocationAdapter locationAdapter;

    protected GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private static Location myLocation;
    private boolean mRequestingLocationUpdates;

    View layoutError;
    View layoutLoading;
    private ImageView errorImage;
    private TextView errorText;
    private List<SplashViewModel> splashViewModelsList;


    public SplashActivity() {
    }


    private void setUpProgress() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        //progressDialog.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(R.string.app_name);

        if (AppUtils.getSettingFromPref(this) == 0)
            AppUtils.saveSettingPreferences(this, SETTING_SHARED_VALUE_REALTIME);

        updateValuesFromBundle(savedInstanceState);
        setUpProgress();

        initViews();
        buildGoogleApiClient();
        createLocationRequest();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_real_time) {
            AppUtils.saveSettingPreferences(this, SETTING_SHARED_VALUE_REALTIME);
        } else if (id == R.id.action_single_update) {
            AppUtils.saveSettingPreferences(this, SETTING_SHARED_VALUE_SINGLE_UPDATE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.takeView(this);

        mRequestingLocationUpdates = true;

//        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
        if (!AppUtils.isNetworkConnected(this))
            displayError();
        else if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
            checkLocationPermissions();
        }
    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {

            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        REQUESTING_LOCATION_UPDATES_KEY);
            }

            // Update the value of mCurrentLocation from the Bundle and update the
            // UI to show the correct latitude and longitude.
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                // Since LOCATION_KEY was found in the Bundle, we can be sure that
                // mCurrentLocationis not null.
                myLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY,
                mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, myLocation);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();

        presenter.unsubscribe();
        presenter.dropView();


    }

    private void initViews() {
        locationRecyclerView = (RecyclerView) findViewById(R.id.locationsList);
        initErrorLayout();
        initLoadingLayout();

        splashViewModelsList = new ArrayList<>();

        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationAdapter = new LocationAdapter(splashViewModelsList, this);

        locationRecyclerView.setAdapter(locationAdapter);

    }

    private void initErrorLayout() {
        layoutError = findViewById(R.id.layout_error);
        layoutError.setVisibility(View.GONE);
        errorImage = (ImageView) layoutError.findViewById(R.id.image_error);
        errorText = (TextView) layoutError.findViewById(R.id.text_error);
    }

    private void initLoadingLayout() {
        layoutLoading = findViewById(R.id.layout_loading);
        layoutLoading.setVisibility(View.GONE);
//        errorImage = (ImageView) layoutLoading.findViewById(R.id.image_error);
//        errorText = (TextView) layoutLoading.findViewById(R.id.text_error);
    }


//    public void setPresenter(SplashContract.Presenter presenter) {
//        this.presenter = presenter;
//    }

    @Override
    public void showLoadingBar(boolean isShown) {
        if (isShown)
            showProgress();
        else
            hideProgress();

    }

    @Override
    public void displayData(List<SplashViewModel> splashViewModels) {

        locationRecyclerView.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);

        splashViewModelsList = splashViewModels;
        locationAdapter = new LocationAdapter(splashViewModelsList, this);
        locationRecyclerView.setAdapter(locationAdapter);


    }

    @Override
    public void EmptyData() {
        layoutError.setVisibility(View.VISIBLE);
        layoutLoading.setVisibility(View.GONE);
        locationRecyclerView.setVisibility(View.GONE);

        errorImage.setImageResource(android.R.drawable.stat_notify_error);
        errorText.setText(R.string.no_data_found);

        AppUtils.deleteLocationPreferences(this);

//        Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError() {
        hideProgress();
        layoutError.setVisibility(View.VISIBLE);
        layoutLoading.setVisibility(View.GONE);
        locationRecyclerView.setVisibility(View.GONE);

        errorText.setText(getString(R.string.something_went_wrong));
        errorImage.setImageResource(android.R.drawable.ic_delete);

        AppUtils.deleteLocationPreferences(this);

//        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void updateData(List<SplashViewModel> splashViewModels) {
        locationRecyclerView.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);

        splashViewModelsList.clear();
        splashViewModelsList.addAll(splashViewModels);
        locationAdapter.notifyItemRangeChanged(0, splashViewModels.size());
//        locationAdapter = new LocationAdapter(splashViewModels, this);
//        locationRecyclerView.setAdapter(locationAdapter);

    }

    protected void showProgress() {
//        progressDialog.show();

        layoutLoading.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);
        locationRecyclerView.setVisibility(View.GONE);
    }

    protected void hideProgress() {
//        progressDialog.dismiss();


        layoutLoading.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);
        locationRecyclerView.setVisibility(View.VISIBLE);
    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000 * 60);
//        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void startLocationUpdates() {
        Log.e(TAG, "startLocationUpdates");

        Log.e(TAG, "request_permission: " + (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED));

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationServices.FusedLocationApi.requestLocationUpdates(
                    mGoogleApiClient, mLocationRequest, this);

            Location lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (lastKnownLocation != null) {
                Log.e(TAG, "lastKnownLocation: " + lastKnownLocation.getLatitude());
                onLocationChanged(lastKnownLocation);
            } else
                Log.e(TAG, "lastKnownLocation: null");
        }
    }

    protected void stopLocationUpdates() {
        Log.e(TAG, "stopLocationUpdates");
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.e(TAG, "onConnected");
//        if (mRequestingLocationUpdates)
        checkLocationPermissions();
    }

    private void checkLocationPermissions() {
        int grant = PackageManager.PERMISSION_GRANTED;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == grant &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == grant) {
            startLocationUpdates();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_GET_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged, getLatitude: " + location.getLatitude());

        String newLocationText = String.valueOf(location.getLatitude()).concat(",").concat(String.valueOf(location.getLongitude()));

        if (AppUtils.isNetworkConnected(this)) {
            Location oldLocation = AppUtils.getLocationFromPref(this);
            boolean first = oldLocation != null;

            if (mRequestingLocationUpdates) {
                presenter.getLocations(newLocationText, 1000, AppUtils.isNetworkConnected(this));
                AppUtils.saveLocationPreferences(this, location);
                mRequestingLocationUpdates = false;
            } else if (first && AppUtils.getSettingFromPref(this) == SETTING_SHARED_VALUE_REALTIME) {
                float v = oldLocation.distanceTo(location);
                if (v > 500) {
                    //distance greater than 500 meter
                    presenter.getLocations(newLocationText, 1000, AppUtils.isNetworkConnected(this));
                    AppUtils.saveLocationPreferences(this, location);
                }

            } else {
                //first update
                presenter.getLocations(newLocationText, 1000, AppUtils.isNetworkConnected(this));
                AppUtils.saveLocationPreferences(this, location);
            }

        } else
            displayError();

    }

    private void updateLocation(Location location) {
        String newLocationText = String.valueOf(location.getLatitude()).concat(",").concat(String.valueOf(location.getLongitude()));

        presenter.getLocations(newLocationText, 1000, AppUtils.isNetworkConnected(this));

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "Connection failed:" + connectionResult.getErrorCode());
        String errorMessage = connectionResult.getErrorMessage();
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
