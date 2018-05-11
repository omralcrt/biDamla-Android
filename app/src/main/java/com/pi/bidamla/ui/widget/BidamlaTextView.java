package com.pi.bidamla.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.pi.bidamla.R;
import com.pi.bidamla.helper.BidamlaFont;

/**
 * Created by omral on 18.04.2018.
 */

public class BidamlaTextView extends AppCompatTextView {

    public BidamlaTextView(Context context) {
        super(context);
    }

    public BidamlaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BidamlaTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.BidamlaTextView);

            if (arr != null) {
                BidamlaFont font = BidamlaFont.valueOf(arr.getInt(R.styleable.BidamlaTextView_bidamlaFont, -1));
                if (font != null)
                    setTypeface(ResourcesCompat.getFont(context, font.getFontRes()));
                arr.recycle();

            }
        }
    }
}
