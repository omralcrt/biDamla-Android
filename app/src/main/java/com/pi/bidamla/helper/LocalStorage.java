package com.pi.bidamla.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import com.pi.bidamla.data.remote.UserModel.*;

public class LocalStorage {

    private static final String PREFS = "com.authorname.projectname.helper.prefs";
    private static final String TOKEN = "com.authorname.projectname.helper.token";
    private static final String USER  = "com.authorname.projectname.helper.user";
    private static final String FIRST_TIME = "com.authorname.projectname.helper.first_time";

    //region token

    public static void setToken(Context context, String token){
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit();
        if (token == null)
            editor.remove(TOKEN);
        else
            editor.putString(TOKEN, token);
        editor.apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences(PREFS,Context.MODE_PRIVATE).getString(TOKEN, null);
    }

    //endregion

    //region user

    public static UserResponse getUser(Context context) {
        return new Gson().fromJson(context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(USER, "{}"), UserResponse.class);
    }

    public static void setUser(Context context, UserResponse user) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();

        if (user == null) {
            editor.remove(USER);
        } else {
            editor.putString(TOKEN, gson.toJson(user));
        }

        editor.apply();
    }

    //endregion

    //region firstTime

    public static void setFirstTime(Context context) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putBoolean(FIRST_TIME, true).apply();
    }

    public static boolean isFirstTime(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getBoolean(FIRST_TIME, false);
    }

    //endregion

}
