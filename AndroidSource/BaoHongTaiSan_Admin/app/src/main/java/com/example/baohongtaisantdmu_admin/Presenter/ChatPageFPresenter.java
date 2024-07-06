package com.example.baohongtaisantdmu_admin.Presenter;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.View.ChatPageFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatPageFPresenter {

    private ChatPageFView chatPageFView;

    public ChatPageFPresenter(ChatPageFView chatPageFView) {
        this.chatPageFView = chatPageFView;
    }

    public void _Get_Data_ListUser(){
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
        });
        */

        ApiServices.apiServices.get_list_data_nguoidung_user(Logged.getInstance().getNguoiDung().getMaND()).enqueue(new Callback<List<NguoiDung>>() {
            @Override
            public void onResponse(Call<List<NguoiDung>> call, Response<List<NguoiDung>> response) {
                if (response.isSuccessful() && response.body() != null){
                    chatPageFView._Get_Data_ListUser_Success(response.body());
                }else {
                    chatPageFView._ChatPage_Error("_Get_Data_ListUser","Get list data không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<NguoiDung>> call, Throwable t) {
                chatPageFView._ChatPage_Error("_Get_Data_ListUser","Get list data không thành công !!!. Error: " + t.getMessage());
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
