package com.pi.bidamla.helper;

import android.support.annotation.FontRes;

import com.pi.bidamla.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by omral on 18.04.2018.
 */

public enum BidamlaFont {

    HelveticaNeueMedium(0),
    HelveticaNeueThin(1),
    HelveticaNeueLight(2),
    HelveticaNeueBold(3);

    private int value;
    private static Map<Integer, BidamlaFont> map = new HashMap<Integer, BidamlaFont>();

    static {
        for (BidamlaFont font: BidamlaFont.values())
            map.put(font.value, font);
    }

    BidamlaFont(int value) {
        this.value = value;
    }

    public static BidamlaFont valueOf(int value) {
        return map.get(value);
    }

    public @FontRes
    int getFontRes() {
        switch (this){
            case HelveticaNeueMedium : return R.font.helveticaneue_medium;
            case HelveticaNeueThin : return R.font.helveticaneue_thin;
            case HelveticaNeueLight: return R.font.helveticaneue_light;
            case HelveticaNeueBold: return R.font.helveticaneue_bold;
            default: return R.font.helveticaneue_medium;
        }
    }
}
