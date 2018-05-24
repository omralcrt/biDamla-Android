package com.pi.bidamla.ui.myRequests;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pi.bidamla.R;
import com.pi.bidamla.data.remote.BloodRequestModel;
import com.pi.bidamla.helper.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRequestRowAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private Context mContext;
    private BloodRequestModel.BloodRequestResponse[] mRows;
    private MyRequestRowAdapter.OnItemClickListener mClickListener;

    public void setOnItemClickListener(MyRequestRowAdapter.OnItemClickListener onItemClickListener) {
        this.mClickListener = onItemClickListener;
    }

    public MyRequestRowAdapter(Context context, List<BloodRequestModel.BloodRequestResponse> rows) {
        this.mContext = context;
        this.mRows = rows.toArray(new BloodRequestModel.BloodRequestResponse[rows.size()]);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyRequestRowAdapter.MyRequestRowViewHolder(LayoutInflater.from(mContext).inflate(R.layout.my_request_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final BloodRequestModel.BloodRequestResponse row = mRows[position];

        MyRequestRowAdapter.MyRequestRowViewHolder bloodRequestRowViewHolder = (MyRequestRowAdapter.MyRequestRowViewHolder) holder;
        bloodRequestRowViewHolder.bloodGroupTextView.setText(row.getBloodGroup());
        bloodRequestRowViewHolder.hospitalTextView.setText(row.getHospital().getName());

        if (row.getRequestStatus().equals("waiting")) {
            bloodRequestRowViewHolder.statusTextView.setText(R.string.waiting);
            bloodRequestRowViewHolder.statusTextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            bloodRequestRowViewHolder.statusTextView.setText(R.string.completed);
            bloodRequestRowViewHolder.statusTextView.setTextColor(mContext.getResources().getColor(R.color.green));
        }

        bloodRequestRowViewHolder.dateTextView.setText(Utils.dateFormatter(row.getCreatedAt()));

        if (row.getRequestStatus().equals("waiting")) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mRows.length;
    }

    class MyRequestRowViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.blood_group_text_view)
        TextView bloodGroupTextView;
        @BindView(R.id.date_text_view)
        TextView dateTextView;
        @BindView(R.id.status_text_view)
        TextView statusTextView;
        @BindView(R.id.hospital_text_view)
        TextView hospitalTextView;

        public MyRequestRowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
