package com.pi.bidamla.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.pi.bidamla.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BidamlaToolbar extends RelativeLayout {

    public interface BidamlaToolbarListener {
        void closeButtonClicked();
    }

    @BindView(R.id.close_button)
    ImageButton closeButton;

    private BidamlaToolbarListener listener;

    //region constructors

    public BidamlaToolbar(Context context) {
        super(context);
        init(false);
    }

    public BidamlaToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context.getTheme().obtainStyledAttributes(attrs,R.styleable.BidamlaToolbar, 0, 0).getBoolean(R.styleable.BidamlaToolbar_closeButtonVisible,false));
    }

    public BidamlaToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context.getTheme().obtainStyledAttributes(attrs,R.styleable.BidamlaToolbar, 0, 0).getBoolean(R.styleable.BidamlaToolbar_closeButtonVisible,false));
    }

    public BidamlaToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context.getTheme().obtainStyledAttributes(attrs,R.styleable.BidamlaToolbar, 0, 0).getBoolean(R.styleable.BidamlaToolbar_closeButtonVisible,false));
    }

    //endregion

    public void setListener(BidamlaToolbarListener listener) {
        this.listener = listener;
    }

    private void init(boolean closeButtonVisible) {
        View view = inflate(getContext(), R.layout.toolbar_bidamla, this);
        ButterKnife.bind(this, view);
        closeButton.setVisibility(closeButtonVisible ? VISIBLE : GONE);
    }

    @OnClick(R.id.close_button)
    void close() {
        if (listener != null)
            listener.closeButtonClicked();
    }

}
