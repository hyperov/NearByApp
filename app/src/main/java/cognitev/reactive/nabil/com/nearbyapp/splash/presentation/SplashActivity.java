package cognitev.reactive.nabil.com.nearbyapp.splash.presentation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
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
    SplashPresenter presenter;

    RecyclerView locationRecyclerView;
    LocationAdapter locationAdapter;

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
        setUpProgress();
//        try {
//            PresentationComponent presentationComponent = App.getPresentationComponent();
//            presentationComponent.inject(this);
//        } catch (Exception e) {
//            e.getMessage();
//
//        }
        initViews();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.takeView(this);
        presenter.getLocations("44.3,37.2", 100000, AppUtils.isNetworkConnected(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
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

}
