package com.example.baohongtaisantdmu.Presenter;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.Chat;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.View.ChatPageFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatPageFPresenter {
    private ChatPageFView chatPageFView;
    private List<NguoiDung> nguoiDungs;

    public ChatPageFPresenter(ChatPageFView chatPageFView) {
        this.chatPageFView = chatPageFView;
        nguoiDungs = new ArrayList<>();
    }

    public void _Get_Data_ListUser_Admin(){
        /*
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nguoiDungs.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    NguoiDung nguoiDung = snapshot1.getValue(NguoiDung.class);
                    if (nguoiDung.getMaPQ() == 1 && nguoiDung.getMaND() != Logged.getInstance().getNguoiDung().getMaND()){
                        nguoiDungs.add(nguoiDung);
                    }
                }
                chatPageFView._Get_Data_ListUser_Admin_Success(nguoiDungs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                chatPageFView._Get_Data_ListUser_Admin_Error(error.getMessage());
            }
        });*/

        ApiServices.apiServices.get_list_data_nguoidung_admin(Logged.getInstance().getNguoiDung().getMaND()).enqueue(new Callback<List<NguoiDung>>() {
            @Override
            public void onResponse(Call<List<NguoiDung>> call, Response<List<NguoiDung>> response) {
                if (response.isSuccessful() && response.body() != null){
                    chatPageFView._Get_Data_ListUser_Admin_Success(response.body());
                }else {
                    chatPageFView._Get_Data_ListUser_Admin_Error("Get list data không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<NguoiDung>> call, Throwable t) {
                chatPageFView._Get_Data_ListUser_Admin_Error("Get list data không thành công !!!. Error: " + t.getMessage());
            }
        });
    }


    public void _SearchData_ChatPage(List<NguoiDung> nguoiDungList, String sSearch){
        ArrayList<NguoiDung> searchlist = new ArrayList<>();
        for (NguoiDung nguoiDung : nguoiDungList) {
            if (nguoiDung.getEmail().toLowerCase().contains(sSearch.toLowerCase()) || nguoiDung.getHoVaTen().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(nguoiDung);
            }
        }
        chatPageFView._Search_Data_ChatPage_Success(searchlist);
    }

    public void _Get_Status_User_In_FireBase(){

    }



}
