package com.pi.bidamla.ui.bloodRequests;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pi.bidamla.R;
import com.pi.bidamla.data.remote.BloodRequestModel.BloodRequestResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BloodRequestRowAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Context mContext;
    private BloodRequestResponse[] mRows;
    private OnItemClickListener mClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mClickListener = onItemClickListener;
    }

    public BloodRequestRowAdapter(Context context, List<BloodRequestResponse> rows) {
        this.mContext = context;
        this.mRows = rows.toArray(new BloodRequestResponse[rows.size()]);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BloodRequestRowViewHolder(LayoutInflater.from(mContext).inflate(R.layout.blood_request_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final BloodRequestResponse row = mRows[position];

        BloodRequestRowViewHolder bloodRequestRowViewHolder = (BloodRequestRowViewHolder) holder;
        bloodRequestRowViewHolder.bloodGroupTextView.setText(row.getBloodGroup());
        bloodRequestRowViewHolder.nameTextView.setText(row.getUser().getName());
        bloodRequestRowViewHolder.statusTextView.setText(row.getRequestStatus());
        bloodRequestRowViewHolder.hospitalTextView.setText("Hospital");

        DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault());

        String outputText = "";

        try {
            Date date = inputFormat.parse(row.getCreatedAt());
            outputText = outputFormat.format(date);
        } catch (Exception e) {
            outputText = "";
        }

        bloodRequestRowViewHolder.dateTextView.setText(outputText);

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

    class BloodRequestRowViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.blood_group_text_view)
        TextView bloodGroupTextView;
        @BindView(R.id.name_text_view)
        TextView nameTextView;
        @BindView(R.id.date_text_view)
        TextView dateTextView;
        @BindView(R.id.status_text_view)
        TextView statusTextView;
        @BindView(R.id.hospital_text_view)
        TextView hospitalTextView;

        public BloodRequestRowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
