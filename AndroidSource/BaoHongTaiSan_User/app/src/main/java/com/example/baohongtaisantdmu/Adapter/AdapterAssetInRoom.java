package com.example.baohongtaisantdmu.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baohongtaisantdmu.Activity.ReportAssetActivity;
import com.example.baohongtaisantdmu.Model.AdapterClick;
import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.Phong;
import com.example.baohongtaisantdmu.R;
import com.google.gson.Gson;

import java.util.List;

public class AdapterAssetInRoom extends RecyclerView.Adapter<AdapterAssetInRoom.PhanBoViewHolder>{

    private List<AssetInRoom> assetInRoomList;
    private AdapterClick adapterClick;
    private Context context;


    public AdapterAssetInRoom(List<AssetInRoom> assetInRoomList, Context context, AdapterClick adapterClick) {
        this.assetInRoomList = assetInRoomList;
        this.adapterClick = adapterClick;
        this.context = context;
    }


    @NonNull
    @Override
    public PhanBoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rcv_assetinroom, parent, false);
        return new PhanBoViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PhanBoViewHolder holder, int position) {
        if (assetInRoomList == null) {
            return;
        }
        boolean Flag_Check_TT = false;
        AssetInRoom assetInRoom = assetInRoomList.get(position);
        holder.TenTS.setText(assetInRoom.getTenTS());
        holder.TenNTS.setText(assetInRoom.getTenNTS());
        holder.SoLuong.setText("Số lượng: " + assetInRoom.getSoLuong());
        holder.btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterClick.onClickRCV(assetInRoom, "BTN_XEMCHITIET");
            }
        });
        holder.btnBaoHong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();

                Intent intent = new Intent(context, ReportAssetActivity.class);
                intent.putExtra("AssetInRoomInfo", gson.toJson(assetInRoom));
                context.startActivity(intent);
            }
        });



        if (assetInRoom.getMaBL() != 0 && assetInRoom.getTrangThai() <= 3) {
            if (assetInRoom.getTrangThai() == 1) {
                holder.TrangThai.setBackgroundColor(Color.parseColor("#5792B9"));
                holder.TrangThai.setText("Đã gửi báo hỏng tài sản");
                Flag_Check_TT = true;
            } else if (assetInRoom.getTrangThai() == 2) {
                holder.TrangThai.setBackgroundColor(Color.parseColor("#A4D83B"));
                holder.TrangThai.setText("Đã tiếp nhận báo hỏng");
                Flag_Check_TT = true;
            } else if (assetInRoom.getTrangThai() == 3) {
                holder.TrangThai.setBackgroundColor(Color.parseColor("#FC4A32"));
                holder.TrangThai.setText("Đang sữa chữa");
                Flag_Check_TT = true;
            } else if (assetInRoom.getTrangThai() == 4) {
                holder.TrangThai.setBackgroundColor(Color.parseColor("#00FF0A"));
                holder.TrangThai.setText("Hoạt động tốt");
                Flag_Check_TT = false;
            } else if (assetInRoom.getTrangThai() == 5) {
                holder.TrangThai.setBackgroundColor(Color.parseColor("#FF1E00"));
                holder.TrangThai.setText("Đang bị hư hỏng");
            } else {
                holder.TrangThai.setBackgroundColor(Color.parseColor("#AF6DCA"));
                holder.TrangThai.setText("Trạng thái không thể xác định");
                Flag_Check_TT = true;
            }
        }


        if (!Flag_Check_TT) {
            holder.TrangThai.setBackgroundColor(Color.parseColor("#00FF0A"));
            holder.TrangThai.setText("Hoạt động tốt");
            holder.btnBaoHong.setEnabled(true);
        } else {
            holder.btnBaoHong.setBackgroundColor(Color.parseColor("#AAB7B8"));
            holder.btnBaoHong.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        if (assetInRoomList == null) {
            return 0;
        }
        return assetInRoomList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchDataList(List<AssetInRoom> assetInRooms) {
        assetInRoomList = assetInRooms;
        notifyDataSetChanged();
    }
    public class PhanBoViewHolder extends RecyclerView.ViewHolder
    {
        TextView TenTS, TenNTS, SoLuong, TrangThai;
        Button btnXemChiTiet, btnBaoHong;

        public PhanBoViewHolder(@NonNull View itemView) {
            super(itemView);
            TenTS = itemView.findViewById(R.id.tvTenTS);
            TenNTS = itemView.findViewById(R.id.tvTenNTS);
            SoLuong = itemView.findViewById(R.id.tvCountSoLuongTS);
            TrangThai = itemView.findViewById(R.id.tvTrangThai);
            btnXemChiTiet = itemView.findViewById(R.id.btnXemChiTiet);
            btnBaoHong = itemView.findViewById(R.id.btnBaoHong);
        }
    }


}
