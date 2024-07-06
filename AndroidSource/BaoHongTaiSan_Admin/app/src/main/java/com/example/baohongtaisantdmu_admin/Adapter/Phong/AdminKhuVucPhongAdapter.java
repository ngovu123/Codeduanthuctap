package com.example.baohongtaisantdmu_admin.Adapter.Phong;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.R;

import java.util.ArrayList;
import java.util.List;

public class AdminKhuVucPhongAdapter extends RecyclerView.Adapter<AdminKhuVucPhongAdapter.KhuvucphongViewHolder> {

    private List<KhuVucPhong> listKhuvucphong;
    private final RCVClickItem rcvClickItem;

    public AdminKhuVucPhongAdapter(List<KhuVucPhong> listKhuvucphong, RCVClickItem rcvClickItem) {
        this.listKhuvucphong = listKhuvucphong;
        this.rcvClickItem = rcvClickItem;
    }

    @NonNull
    @Override
    public KhuvucphongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rcv_khuvucphong, parent, false);
        return new KhuvucphongViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull KhuvucphongViewHolder holder, int position) {
        KhuVucPhong kv = listKhuvucphong.get(position);
        if (kv == null) {
            return;
        }
        holder.makhuvuc.setText(kv.getMaKVP() + "");
        holder.tenkhuvuc.setText(kv.getTenKVP());
        holder.editkhuvuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rcvClickItem.onClickRCV(kv, "EDIT");
            }
        });
        holder.deletekhuvuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rcvClickItem.onClickRCV(kv, "DELETE");
            }
        });
    }


    @Override
    public int getItemCount() {
        if (listKhuvucphong != null) {
            return listKhuvucphong.size();
        }
        return 0;
    }


    public void searchDataList(List<KhuVucPhong> searchlist) {
        listKhuvucphong = searchlist;
        notifyDataSetChanged();
    }


    public KhuVucPhong getItem(int position) {
        return listKhuvucphong.get(position);
    }


    public class KhuvucphongViewHolder extends RecyclerView.ViewHolder {
        private final TextView makhuvuc;
        private final TextView tenkhuvuc;
        private final ImageView editkhuvuc;
        private final ImageView deletekhuvuc;

        public KhuvucphongViewHolder(@NonNull View itemView) {
            super(itemView);
            makhuvuc = itemView.findViewById(R.id.Makv);
            tenkhuvuc = itemView.findViewById(R.id.txtTenkv);
            editkhuvuc = itemView.findViewById(R.id.Editkv);
            deletekhuvuc = itemView.findViewById(R.id.Deletekv);
        }
    }
}
