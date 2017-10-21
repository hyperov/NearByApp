package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cognitev.reactive.nabil.com.nearbyapp.BaseActivity;
import cognitev.reactive.nabil.com.nearbyapp.R;
import cognitev.reactive.nabil.com.nearbyapp.Utils.AppUtils;
import cognitev.reactive.nabil.com.nearbyapp.splash.presentation.adapter.LocationAdapter;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter presenter;

    RecyclerView locationRecyclerView;
    LocationAdapter locationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initViews();



    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getLocations("44.3,37.2",100000, AppUtils.isNetworkConnected(this));
    }

    private void initViews() {
        locationRecyclerView = (RecyclerView) findViewById(R.id.locationsList);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        locationAdapter = new LocationAdapter(new ArrayList<SplashViewModel>(), this);

        locationRecyclerView.setAdapter(locationAdapter);
    }


    public void setPresenter(SplashContract.Presenter presenter) {
        this.presenter = presenter;
    }

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
        Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError() {
        Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show();

    }
}
