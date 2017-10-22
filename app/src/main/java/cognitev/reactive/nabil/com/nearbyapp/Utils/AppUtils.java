package cognitev.reactive.nabil.com.nearbyapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.LOCATION_SHARED_KEY;
import static cognitev.reactive.nabil.com.nearbyapp.Utils.Constants.SETTING_SHARED_KEY;

/**
 * Created by anabil on 10/21/2017.
 */

public class AppUtils {

    private String handleError(Throwable throwable) {

        if (throwable instanceof IOException)
            return "No Network connection";
        else
            return "Something went wrong";
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static String getCurrentDate() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return formattedDate.format(Calendar.getInstance().getTime());
    }

    public static void saveSettingPreferences(Context context, int value) {
        SharedPreferences mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putInt(SETTING_SHARED_KEY, value);
        editor.apply();
    }

    public static int getSettingFromPref(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        return defaultSharedPreferences.getInt(SETTING_SHARED_KEY, 0);
    }

    public static void saveLocationPreferences(Context context, Location value) {
        SharedPreferences mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString(LOCATION_SHARED_KEY, json);
        editor.apply();
    }

    public static Location getLocationFromPref(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Gson gson = new Gson();
        String json = defaultSharedPreferences.getString(LOCATION_SHARED_KEY, "");
        return gson.fromJson(json, Location.class);
    }

    public static void deleteLocationPreferences(Context context) {
        SharedPreferences mSharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.remove(LOCATION_SHARED_KEY);
        editor.apply();
    }


}
