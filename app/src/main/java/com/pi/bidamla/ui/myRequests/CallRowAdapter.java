package com.pi.bidamla.ui.myRequests;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pi.bidamla.R;
import com.pi.bidamla.data.remote.CallModel;
import com.pi.bidamla.helper.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Context mContext;
    private CallModel.CallResponse[] mRows;
    private CallRowAdapter.OnItemClickListener mClickListener;

    public void setOnItemClickListener(CallRowAdapter.OnItemClickListener onItemClickListener) {
        this.mClickListener = onItemClickListener;
    }

    public CallRowAdapter(Context context, List<CallModel.CallResponse> rows) {
        this.mContext = context;
        this.mRows = rows.toArray(new CallModel.CallResponse[rows.size()]);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CallRowAdapter.CallRowViewHolder(LayoutInflater.from(mContext).inflate(R.layout.call_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final CallModel.CallResponse row = mRows[position];

        CallRowAdapter.CallRowViewHolder callRowViewHolder = (CallRowAdapter.CallRowViewHolder) holder;
        callRowViewHolder.bloodGroupTextView.setText(row.getUser().getBloodGroup());
        callRowViewHolder.nameTextView.setText(row.getUser().getName() + " " + row.getUser().getLastName().substring(0, 1) + ".");
        callRowViewHolder.dateTextView.setText(Utils.dateFormatter(row.getCreatedAt()));
        callRowViewHolder.hourTextView.setText(Utils.hourFormatter(row.getCreatedAt()));

        callRowViewHolder.gaveBloodButton.setOnClickListener(new View.OnClickListener() {
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

    class CallRowViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.blood_group_text_view)
        TextView bloodGroupTextView;
        @BindView(R.id.name_text_view)
        TextView nameTextView;
        @BindView(R.id.date_text_view)
        TextView dateTextView;
        @BindView(R.id.hour_text_view)
        TextView hourTextView;
        @BindView(R.id.gave_blood_button)
        Button gaveBloodButton;

        public CallRowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
