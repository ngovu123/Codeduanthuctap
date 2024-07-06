package com.example.baohongtaisantdmu_admin.Adapter.TaiSan;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.R;

import java.util.ArrayList;
import java.util.List;

public class AdminLoaiTaiSanAdapter extends RecyclerView.Adapter<AdminLoaiTaiSanAdapter.LoaitaisanViewHolder> {

    private List<LoaiTaiSan> listloaits;
    private final RCVClickItem rcvClickItem;

    public AdminLoaiTaiSanAdapter(List<LoaiTaiSan> listloaits, RCVClickItem rcvClickItem) {
        this.listloaits = listloaits;
        this.rcvClickItem = rcvClickItem;
    }

    @NonNull
    @Override
    public LoaitaisanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rcv_loaitaisan, parent, false);
        return new LoaitaisanViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LoaitaisanViewHolder holder, int position) {
        LoaiTaiSan loaiTaiSan = listloaits.get(position);
        if (loaiTaiSan == null) {
            return;
        }
        holder.maloaits.setText(loaiTaiSan.getMaLTS() + "");
        holder.tenloaits.setText(loaiTaiSan.getTenLTS());
        holder.editloaits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rcvClickItem.onClickRCV(loaiTaiSan, "EDIT");
            }
        });
        holder.deleteloaits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rcvClickItem.onClickRCV(loaiTaiSan, "DELETE");
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listloaits != null) {
            return listloaits.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(List<LoaiTaiSan> searchlist) {
        listloaits = searchlist;
        notifyDataSetChanged();
    }

    public LoaiTaiSan getItem(int position) {
        return listloaits.get(position);
    }


    public static class LoaitaisanViewHolder extends RecyclerView.ViewHolder {
        private final TextView maloaits;
        private final TextView tenloaits;
        private final ImageView editloaits;
        private final ImageView deleteloaits;

        public LoaitaisanViewHolder(@NonNull View itemView) {
            super(itemView);
            maloaits = itemView.findViewById(R.id.MaLoaiTS);
            tenloaits = itemView.findViewById(R.id.txtTenLoaiTS);
            editloaits = itemView.findViewById(R.id.EditLoaiTS);
            deleteloaits = itemView.findViewById(R.id.DeleteLoaiTS);
        }
    }
}
