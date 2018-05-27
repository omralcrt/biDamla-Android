package com.pi.bidamla.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omral on 18.04.2018.
 */

public class Constants {

    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String BLOOD_REQUEST = "BLOOD_REQUEST";

    public static List<String> settingsMenuItems() {
        List<String>  items = new ArrayList<>();
        items.add("Profilim");
        items.add("Çıkış");

        return items;
    }
}
