package com.pi.bidamla.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.pi.bidamla.R;
import com.pi.bidamla.helper.ProjectNameFont;

/**
 * Created by omral on 18.04.2018.
 */

public class ProjectNameTextView extends AppCompatTextView {

    public ProjectNameTextView(Context context) {
        super(context);
    }

    public ProjectNameTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProjectNameTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {

            TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.ProjectNameTextView);

            if (arr != null) {
                ProjectNameFont font = ProjectNameFont.valueOf(arr.getInt(R.styleable.ProjectNameTextView_projectNameFont, -1));
                if (font != null)
                    setTypeface(ResourcesCompat.getFont(context, font.getFontRes()));
                arr.recycle();

            }
        }
    }
}
