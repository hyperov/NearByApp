package cognitev.reactive.nabil.com.nearbyapp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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


}
