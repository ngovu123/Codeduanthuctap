package com.example.baohongtaisantdmu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baohongtaisantdmu.Model.Chat;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.ChatView;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterChat extends RecyclerView.Adapter<AdapterChat.ChatViewHolder>{
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private List<Chat> chatList;
    private Context conText;

    public AdapterChat(List<Chat> chatList, Context conText) {
        this.chatList = chatList;
        this.conText = conText;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_RIGHT){
            view= LayoutInflater.from(conText).inflate(R.layout.custom_chat_item_right, parent, false);
        }else {
            view = LayoutInflater.from(conText).inflate(R.layout.custom_chat_item_left, parent, false);
        }
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        if (chatList == null) {
            return;
        }
        Chat chat = chatList.get(position);
        holder.show_message.setText(chat.getMessage());
    }

    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getSender() == Logged.getInstance().getNguoiDung().getMaND()){
            return MSG_TYPE_RIGHT;
        }else {
            return MSG_TYPE_LEFT;
        }

    }

    @Override
    public int getItemCount() {
        if (chatList.size() <= 0)
        {
            return 0;
        }
        return chatList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder
    {
        TextView show_message;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.tvMess);
        }
    }


}
