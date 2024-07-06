package com.example.baohongtaisantdmu.Presenter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.Phong;
import com.example.baohongtaisantdmu.View.AssetInRoomView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetInRoomPresenter {

    private AssetInRoomView assetInRoomView;


    public AssetInRoomPresenter(AssetInRoomView assetInRoomView) {
        this.assetInRoomView = assetInRoomView;
    }


    public void _GetData_AssetInRoom(int MaP){
        ApiServices.apiServices.get_data_asset_in_room(MaP).enqueue(new Callback<List<AssetInRoom>>() {
            @Override
            public void onResponse(Call<List<AssetInRoom>> call, Response<List<AssetInRoom>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() == 0){
                        assetInRoomView._GetData_AssetInRoom_Error("Phòng này chưa có thiết bị, tài sản !!!");
                    }else {
                        _AddData_Phong_In_FireBase(MaP);
                        assetInRoomView._GetData_AssetInRoom_Success(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<AssetInRoom>> call, Throwable t) {
                assetInRoomView._GetData_AssetInRoom_Error(t.getMessage().toString());
            }
        });
    }



    public void _SearchData_AssetInRoom(List<AssetInRoom> assetInRoomList, String sSearch, int ID_TrangThai, int MaNTS){
        ArrayList<AssetInRoom> searchlist = new ArrayList<>();
        if (ID_TrangThai == -1 && MaNTS == -1) {
            for (AssetInRoom assetInRoom : assetInRoomList) {
                if (assetInRoom.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(assetInRoom);
                }
            }
            assetInRoomView._SearchData_AssetInRoom_Success(searchlist);
        }else if (ID_TrangThai != -1 && MaNTS == -1)
        {
            for (AssetInRoom assetInRoom : assetInRoomList) {
                if (assetInRoom.getTrangThai() == ID_TrangThai && assetInRoom.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(assetInRoom);
                }
            }
            assetInRoomView._SearchData_AssetInRoom_Success(searchlist);
        }else if (ID_TrangThai == -1 && MaNTS != -1){
            for (AssetInRoom assetInRoom : assetInRoomList) {
                if (assetInRoom.getMaNTS() == MaNTS && assetInRoom.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(assetInRoom);
                }
            }
            assetInRoomView._SearchData_AssetInRoom_Success(searchlist);
        }else if (ID_TrangThai != -1 && MaNTS != -1){
            for (AssetInRoom assetInRoom : assetInRoomList) {
                if (assetInRoom.getMaNTS() == MaNTS && assetInRoom.getTrangThai() == ID_TrangThai && assetInRoom.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(assetInRoom);
                }
            }
            assetInRoomView._SearchData_AssetInRoom_Success(searchlist);
        }
    }


    private void _AddData_Phong_In_FireBase(int MaP){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("UserInRoom");
        NguoiDung nguoiDung = new NguoiDung(Logged.getInstance().getNguoiDung().getMaND(), Logged.getInstance().getNguoiDung().getHoVaTen());
        databaseReference.child(String.valueOf(MaP)).child(String.valueOf(nguoiDung.getMaND())).setValue(nguoiDung, new DatabaseReference.CompletionListener(){
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }

    public void _DeleteData_Phong_In_FireBase(int MaP){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("UserInRoom");
        NguoiDung nguoiDung = new NguoiDung(Logged.getInstance().getNguoiDung().getMaND(), Logged.getInstance().getNguoiDung().getHoVaTen());
        databaseReference.child(String.valueOf(MaP)).child(String.valueOf(nguoiDung.getMaND())).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

            }
        });
    }

    public void _GetData_User_In_Room(int MaP){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("UserInRoom");
        databaseReference.child(String.valueOf(MaP)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<NguoiDung> nguoiDungList = new ArrayList<>();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    NguoiDung nguoiDung = snapshot1.getValue(NguoiDung.class);
                    nguoiDungList.add(nguoiDung);
                }
                assetInRoomView._User_In_Room_DataChange(nguoiDungList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public List<AssetInRoom> _GetNhomTai_In_AssetInRoom(List<AssetInRoom> assetInRoomList){
        Map<Integer, AssetInRoom> uniqueAssetInRoomIDMap = new LinkedHashMap<>();
        for (AssetInRoom assetInRoom : assetInRoomList) {
            uniqueAssetInRoomIDMap.putIfAbsent(assetInRoom.getMaNTS(), assetInRoom);
        }
        return new ArrayList<>(uniqueAssetInRoomIDMap.values());
    }


}
