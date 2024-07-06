package com.example.baohongtaisantdmu.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baohongtaisantdmu.Activity.AssetInRoomActivity;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.Phong;
import com.example.baohongtaisantdmu.R;

import java.util.List;

public class AdapterHomePage extends RecyclerView.Adapter<AdapterHomePage.HomePageViewHolder>{
    private List<NguoiDung> nguoiDungList;
    private Context context;
    public AdapterHomePage(Context context, List<NguoiDung> nguoiDungList) {
        this.context = context;
        this.nguoiDungList = nguoiDungList;
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rcv_homepage, parent, false);
        return new AdapterHomePage.HomePageViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, int position) {
        if (nguoiDungList == null) {
            return;
        }
        NguoiDung nguoiDung = nguoiDungList.get(position);
        holder.tvName.setText(nguoiDung.getHoVaTen());
        holder.tvChucDanh.setText(nguoiDung.getTenPQ());
        holder.tvEmail.setText(nguoiDung.getEmail());
        holder.tvSDT.setText("Số điện thoai: "+nguoiDung.getSoDienThoai());

    }

    @Override
    public int getItemCount() {
        if (nguoiDungList == null) {
            return 0;
        }
        return nguoiDungList.size();
    }


    public static class HomePageViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmail, tvSDT, tvChucDanh, tvName;
        public HomePageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            tvChucDanh = itemView.findViewById(R.id.tvChucDanh);
            tvName = itemView.findViewById(R.id.tvTen);
        }
    }
}
