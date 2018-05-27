package com.pi.bidamla.ui.settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pi.bidamla.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Context mContext;
    private String[] mRows;
    private SettingsRowAdapter.OnItemClickListener mClickListener;

    public void setOnItemClickListener(SettingsRowAdapter.OnItemClickListener onItemClickListener) {
        this.mClickListener = onItemClickListener;
    }

    public SettingsRowAdapter(Context context, List<String> rows) {
        this.mContext = context;
        this.mRows = rows.toArray(new String[rows.size()]);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SettingsRowAdapter.SettingsRowViewHolder(LayoutInflater.from(mContext).inflate(R.layout.settings_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final String row = mRows[position];

        SettingsRowAdapter.SettingsRowViewHolder settingsRowViewHolder = (SettingsRowAdapter.SettingsRowViewHolder) holder;
        settingsRowViewHolder.titleTextView.setText(row);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRows.length;
    }

    class SettingsRowViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_text_view)
        TextView titleTextView;

        public SettingsRowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
