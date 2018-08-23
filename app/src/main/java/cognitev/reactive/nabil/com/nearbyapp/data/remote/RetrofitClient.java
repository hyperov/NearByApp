package cognitev.reactive.nabil.com.nearbyapp.data.remote;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cognitev.reactive.nabil.com.nearbyapp.Utils.AppUtils;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponseLocation;
import cognitev.reactive.nabil.com.nearbyapp.data.model.ApiResponsePhoto;
import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.CLIENT_ID_KEY;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.CLIENT_ID_VALUE;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.CLIENT_SECRET_KEY;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.CLIENT_SECRET_VALUE;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.CONTENT_TYPE_KEY;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.CONTENT_TYPE_VALUE;

/**
 * Created by anabil on 10/20/2017.
 */

public class RetrofitClient {

    private static final String BASE_URL = "https://api.foursquare.com/v2/venues/";
    private RetrofitApi retrofitApi;


    @Inject
    public RetrofitClient() {
        initReq();
    }

    private void initReq() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        okHttpClientBuilder.connectTimeout(10, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(10, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor( chain -> {

            Request request = chain.request();

            HttpUrl originalHttpUrl = request.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter(CLIENT_ID_KEY, CLIENT_ID_VALUE)
                    .addQueryParameter(CLIENT_SECRET_KEY, CLIENT_SECRET_VALUE)
                    .build();


            Request.Builder builder = request.newBuilder()
                    .header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
                    .url(url);


            return chain.proceed(builder.build());
        } );


        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClientBuilder.build());


        Retrofit retrofit = retrofitBuilder.build();
        retrofitApi = retrofit.create(RetrofitApi.class);

    }

    Observable<ApiResponseLocation> getLocations(String location, int radius, String date) {
        AppUtils.cache = false;
        return retrofitApi.getLocations(location, radius, date);
    }

    Observable<ApiResponsePhoto> getLocationPhoto(String locationId, int limit, String date) {

        return retrofitApi.getPhotos(locationId, limit, date);
    }


}
