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
import android.util.Log;
import android.view.Menu;
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


    public SplashActivity() {
    }

    //    @Inject
//    @Override
//    public SplashContract.Presenter getPresenter() {
////        presenter = super.getPresenter();
//        return presenter;
//    }

    private void setUpProgress() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        //progressDialog.setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        updateValuesFromBundle(savedInstanceState);
        setUpProgress();

        initViews();
        buildGoogleApiClient();
        createLocationRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.takeView(this);

//        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
        if (mGoogleApiClient.isConnected() ) {
            startLocationUpdates();
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
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationAdapter = new LocationAdapter(new ArrayList<SplashViewModel>(), this);

        locationRecyclerView.setAdapter(locationAdapter);
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
        locationAdapter = new LocationAdapter(splashViewModels, this);
        locationRecyclerView.setAdapter(locationAdapter);

    }

    @Override
    public void EmptyData() {
        Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();

    }

    protected void showProgress() {
        progressDialog.show();
    }

    protected void hideProgress() {
        progressDialog.dismiss();
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
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
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
        mRequestingLocationUpdates=true;
        String newLocation=String.valueOf(location.getLatitude()).concat(",").concat(String.valueOf(location.getLongitude()));
        presenter.getLocations(newLocation, 1000, AppUtils.isNetworkConnected(this));

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "Connection failed:" + connectionResult.getErrorCode());
        String errorMessage = connectionResult.getErrorMessage();
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
