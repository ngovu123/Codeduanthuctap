package com.example.baohongtaisantdmu_admin.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.R;

import java.util.List;

public class AdapterChatPage extends RecyclerView.Adapter<AdapterChatPage.ChatPageHolder>{

    private List<NguoiDung> nguoiDungList;
    private RCVClickItem adapterClick;


    public AdapterChatPage(List<NguoiDung> nguoiDungList, RCVClickItem adapterClick) {
        this.nguoiDungList = nguoiDungList;
        this.adapterClick = adapterClick;
    }

    @NonNull
    @Override
    public ChatPageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rcv_chatpage, parent, false);
        return new ChatPageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatPageHolder holder, int position) {
        if (nguoiDungList == null) {
            return;
        }
        NguoiDung nguoiDung = nguoiDungList.get(position);
        holder.tvName.setText(nguoiDung.getHoVaTen());
        if (nguoiDung.getStatus()){
            holder.tvStatus.setBackgroundColor(Color.parseColor("#13FF00"));
            holder.tvStatus.setText("Online");
        }else {
            holder.tvStatus.setText("Offline");
        }

        holder.imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterClick.onClickRCV(nguoiDung, "CHAT");
            }
        });

        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterClick.onClickRCV(nguoiDung, "CALL");
            }
        });
    }

    @Override
    public int getItemCount() {
        if (nguoiDungList.size() <= 0) {
            return 0;
        }
        return nguoiDungList.size();
    }

    public void searchDataList(List<NguoiDung> searchlist) {
        nguoiDungList = searchlist;
        notifyDataSetChanged();
    }

    public static class ChatPageHolder extends RecyclerView.ViewHolder
    {
        TextView tvName, tvStatus;
        ImageView imgChat, imgCall;

        public ChatPageHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            imgChat = itemView.findViewById(R.id.imgChat);
            imgCall = itemView.findViewById(R.id.imgCall);
        }
    }
}
