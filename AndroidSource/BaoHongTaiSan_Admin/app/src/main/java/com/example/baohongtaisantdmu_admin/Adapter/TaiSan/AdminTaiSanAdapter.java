package com.example.baohongtaisantdmu_admin.Adapter.TaiSan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminTaiSanAdapter extends RecyclerView.Adapter<AdminTaiSanAdapter.AdminTaiSanViewHolder> {

    private List<TaiSan> taiSanList;
    private View view;

    public AdminTaiSanAdapter(List<TaiSan> taiSanList) {
        this.taiSanList = taiSanList;
    }


    @NonNull
    @Override
    public AdminTaiSanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rcv_taisan, parent, false);
        return new AdminTaiSanViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdminTaiSanViewHolder holder, int position) {
        TaiSan taiSan = taiSanList.get(position);
        if (taiSan == null) {
            return;
        }
        holder.txtTenTS.setText(taiSan.getTenTS());
        holder.txtTenLTS.setText(taiSan.getTenLTS());
        holder.txtTenNTS.setText(taiSan.getTenNTS());
        holder.SLNV.setText("Số lượng nhập vào: " + taiSan.getSLNhapVao());
        holder.SLHC.setText("Số lượng hiện còn: " + taiSan.getSLHienCon());

    }

    @Override
    public int getItemCount() {
        if (taiSanList != null) {
            return taiSanList.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(List<TaiSan> searchlist) {
        taiSanList = searchlist;
        notifyDataSetChanged();
    }

    public static class AdminTaiSanViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView imgQLTS;
        private final TextView txtTenTS;
        private final TextView txtTenLTS;
        private final TextView txtTenNTS;
        private final TextView SLNV;
        private final TextView SLHC;

        public AdminTaiSanViewHolder(@NonNull View itemView) {
            super(itemView);
            imgQLTS = itemView.findViewById(R.id.imgQLTS);
            txtTenTS = itemView.findViewById(R.id.txtAdminQLTS_TenTS);
            txtTenLTS = itemView.findViewById(R.id.txtAdminQLTS_TenLTS);
            txtTenNTS = itemView.findViewById(R.id.txtAdminQLTS_TenNTS);
            SLNV = itemView.findViewById(R.id.txtAdminQLTS_SLNV);
            SLHC = itemView.findViewById(R.id.txtAdminQLTS_SLHC);
        }
    }

}
