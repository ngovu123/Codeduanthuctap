package com.example.baohongtaisantdmu_admin.Presenter;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.View.AssetInRoomFView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetInRoomFPresenter {
    private AssetInRoomFView assetInRoomFView;

    public AssetInRoomFPresenter(AssetInRoomFView assetInRoomFView) {
        this.assetInRoomFView = assetInRoomFView;
    }

    public void Get_List_Data_AssetInRoom(int MaP){
        ApiServices.apiServices.get_data_asset_in_room(MaP).enqueue(new Callback<List<PhanBo>>() {
            @Override
            public void onResponse(Call<List<PhanBo>> call, Response<List<PhanBo>> response) {
                if (response.isSuccessful() && response.body() != null){
                    assetInRoomFView._Get_List_Data_AssetInRoom_Success(response.body());
                }else {
                    assetInRoomFView._Show_Error("Get_List_Data_AssetInRoom","Lấy dữ liệu tài sản có trong phòng không thành công !!!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PhanBo>> call, @NonNull Throwable t) {
                assetInRoomFView._Show_Error("Get_List_Data_AssetInRoom", "Get_List_Data_AssetInRoom: " + t.getMessage());
            }
        });
    }

    public void _SearchData_AssetInRoom(List<PhanBo> phanBoList, String sSearch){
        ArrayList<PhanBo> searchlist = new ArrayList<>();
        for (PhanBo phanBo : phanBoList) {
            if (phanBo.getTenTS().toLowerCase().contains(sSearch.toLowerCase()) || phanBo.getTenLTS().toLowerCase().contains(sSearch.toLowerCase()) || phanBo.getTenNTS().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(phanBo);
            }
        }
        assetInRoomFView._Search_Data_AssetInRoom_Success(searchlist);
    }

    public void _SearchData_AssetInRoom(List<PhanBo> phanBoList, String sSearch, int MaLTS, int MaNTS){
        ArrayList<PhanBo> searchlist = new ArrayList<>();
        if (MaLTS == -1 && MaNTS == -1) {
            for (PhanBo phanBo : phanBoList) {
                if (phanBo.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(phanBo);
                }
            }
            assetInRoomFView._Search_Data_AssetInRoom_Success(searchlist);
        }else if (MaLTS != -1 && MaNTS == -1)
        {
            for (PhanBo phanBo : phanBoList) {
                if (phanBo.getMaLTS() == MaLTS && phanBo.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(phanBo);
                }
            }
            assetInRoomFView._Search_Data_AssetInRoom_Success(searchlist);
        }else if (MaLTS == -1 && MaNTS != -1){
            for (PhanBo phanBo : phanBoList) {
                if (phanBo.getMaNTS() == MaNTS && phanBo.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(phanBo);
                }
            }
            assetInRoomFView._Search_Data_AssetInRoom_Success(searchlist);
        }else if (MaLTS != -1 && MaNTS != -1){
            for (PhanBo phanBo : phanBoList) {
                if (phanBo.getMaNTS() == MaNTS && phanBo.getMaLTS() == MaLTS && phanBo.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(phanBo);
                }
            }
            assetInRoomFView._Search_Data_AssetInRoom_Success(searchlist);
        }
    }

    public List<PhanBo> _GetNhomTaiSan_In_AssetInRoom(List<PhanBo> phanBoList){
        Map<Integer, PhanBo> uniqueAssetInRoomIDMap = new LinkedHashMap<>();
        for (PhanBo phanBo : phanBoList) {
            uniqueAssetInRoomIDMap.putIfAbsent(phanBo.getMaNTS(), phanBo);
        }
        return new ArrayList<>(uniqueAssetInRoomIDMap.values());
    }

    public List<PhanBo> _GetLoaiTaiSan_In_AssetInRoom(List<PhanBo> phanBoList){
        Map<Integer, PhanBo> uniqueAssetInRoomIDMap = new LinkedHashMap<>();
        for (PhanBo phanBo : phanBoList) {
            uniqueAssetInRoomIDMap.putIfAbsent(phanBo.getMaLTS(), phanBo);
        }
        return new ArrayList<>(uniqueAssetInRoomIDMap.values());
    }
}
