package com.pi.bidamla.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by omral on 18.04.2018.
 */

public class Keyboard {

    public static void hideSoftKeyboard(Activity activity) {

        if (activity == null){
            return;
        }

        View currentFocus = activity.getCurrentFocus();
        if (currentFocus!= null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    public static void showSoftKeyboard(Activity activity) {

        if (activity == null){
            return;
        }

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
}
