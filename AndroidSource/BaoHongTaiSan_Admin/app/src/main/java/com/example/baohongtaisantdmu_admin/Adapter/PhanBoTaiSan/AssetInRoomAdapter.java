package com.example.baohongtaisantdmu_admin.Adapter.PhanBoTaiSan;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AssetInRoomAdapter extends RecyclerView.Adapter<AssetInRoomAdapter.AssetInRoomViewHolder>{
    private List<PhanBo> phanBoList;
    private View view;

    public AssetInRoomAdapter(List<PhanBo> phanBoList) {
        this.phanBoList = phanBoList;
    }


    @NonNull
    @Override
    public AssetInRoomAdapter.AssetInRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rcv_taisan, parent, false);
        return new AssetInRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssetInRoomAdapter.AssetInRoomViewHolder holder, int position) {
        PhanBo phanBo = phanBoList.get(position);
        if (phanBo == null) {
            return;
        }
        holder.txtTenTS.setText(phanBo.getTenTS());
        holder.txtTenLTS.setText(phanBo.getTenLTS());
        holder.txtTenNTS.setText(phanBo.getTenNTS());
        holder.SLNV.setText("Số lượng hiện có trong phòng: " + phanBo.getSoLuong());
        holder.SLHC.setText("");

    }

    @Override
    public int getItemCount() {
        if (phanBoList != null) {
            return phanBoList.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(List<PhanBo> searchlist) {
        phanBoList = searchlist;
        notifyDataSetChanged();
    }

    public static class AssetInRoomViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView imgQLTS;
        private final TextView txtTenTS;
        private final TextView txtTenLTS;
        private final TextView txtTenNTS;
        private final TextView SLNV;
        private final TextView SLHC;

        public AssetInRoomViewHolder(@NonNull View itemView) {
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
