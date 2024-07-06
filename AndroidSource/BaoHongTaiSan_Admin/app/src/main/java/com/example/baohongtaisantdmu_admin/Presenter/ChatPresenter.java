package com.example.baohongtaisantdmu_admin.Presenter;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Model.Chat;
import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.View.ChatView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatPresenter {

    private ChatView chatView;
    private List<Chat> chatList;

    public ChatPresenter(ChatView chatView) {
        this.chatView = chatView;
        chatList = new ArrayList<>();
    }

    public void _Send_Message(int MaND, String body){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", Logged.getInstance().getNguoiDung().getMaND());
        hashMap.put("receiver", MaND);
        hashMap.put("message", body);
        reference.child("Chats").push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                chatView._Send_Message_Success();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                chatView._Send_Message_Error(e.getMessage());
            }
        });

    }

    public void _Get_Data_Chat(NguoiDung nguoiDung){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Chat chat = snapshot1.getValue(Chat.class);
                    if ((chat.getReceiver() == nguoiDung.getMaND() && chat.getSender() == Logged.getInstance().getNguoiDung().getMaND() )||
                            (chat.getReceiver() == Logged.getInstance().getNguoiDung().getMaND() && chat.getSender() == nguoiDung.getMaND())){
                        chatList.add(chat);
                    }
                }
                chatView._Get_Data_Chat_Success(chatList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                chatView._Get_Data_Chat_Error(error.getMessage());
            }
        });
    }


}
