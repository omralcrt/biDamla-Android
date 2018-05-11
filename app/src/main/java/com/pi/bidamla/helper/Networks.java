package com.pi.bidamla.helper;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by omral on 18.04.2018.
 */

public class Networks {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
