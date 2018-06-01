package com.pi.bidamla.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by omral on 18.04.2018.
 */

public class Utils {

    public static String dateFormatter(String oldDate) {
        DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault());

        String outputText = "";

        try {
            Date date = inputFormat.parse(oldDate);
            outputText = outputFormat.format(date);
        } catch (Exception e) {
            outputText = "";
        }

        return outputText;
    }

    public static String hourFormatter(String oldDate) {
        DateFormat outputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault());

        String outputText = "";

        try {
            Date date = inputFormat.parse(oldDate);
            outputText = outputFormat.format(date);
        } catch (Exception e) {
            outputText = "";
        }

        return outputText;
    }
}
