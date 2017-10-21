package cognitev.reactive.nabil.com.nearbyapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by anabil on 10/21/2017.
 */

public class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private T presenter;

    void setPresenter(T presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setUpProgress();
    }

    private void setUpProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        //progressDialog.setCancelable(false);
    }

    protected void showProgress() {
        progressDialog.show();
    }

    protected void hideProgress() {
        progressDialog.dismiss();
    }



}
