package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import cognitev.reactive.nabil.com.nearbyapp.BaseActivity;
import cognitev.reactive.nabil.com.nearbyapp.R;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    SplashContract.Presenter presenter;
    RecyclerView locationRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        initViews();


    }


    private void initViews() {
        locationRecyclerView = (RecyclerView) findViewById(R.id.locationsList);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
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
    public void displayData() {

    }

    @Override
    public void EmptyData() {

    }

    @Override
    public void displayError() {

    }
}
