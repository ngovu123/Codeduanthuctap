package com.example.baohongtaisantdmu_admin.Presenter;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.View.PhanBoFView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhanBoFPresenter {
    private PhanBoFView phanBoFView;

    public PhanBoFPresenter(PhanBoFView phanBoFView) {
        this.phanBoFView = phanBoFView;
    }

    public void _Get_Data_TaiSan(){
        ApiServices.kkts_admin_api.get_list_taisan().enqueue(new Callback<List<TaiSan>>() {
            @Override
            public void onResponse(Call<List<TaiSan>> call, Response<List<TaiSan>> response) {
                if (response.isSuccessful() && response.body() != null){
                    phanBoFView._Get_List_Data_TaiSan_Success(response.body());
                }else {
                    phanBoFView._Show_Error("_Get_Data_TaiSan","Lấy dữ liệu tài sản không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<TaiSan>> call, Throwable t) {
                phanBoFView._Show_Error("_Get_Data_TaiSan","_Get_Data_TaiSan: " + t.getMessage());
            }
        });
    }

    public void _Add_TaiSan_To_Room(int MaTS, int MaP, int SoLuong, String GhiChu){
        ApiServices.kkts_admin_api.add_new_phanbo(MaTS, Logged.getInstance().getNguoiDung().getMaND(), MaP, SoLuong, GhiChu).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        phanBoFView._Show_Success("_Add_TaiSan_To_Room", "Thêm tài sản vào phòng thành công");
                    } else {
                        phanBoFView._Show_Error("_Add_TaiSan_To_Room", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                phanBoFView._Show_Error("_Add_TaiSan_To_Room", "_Add_TaiSan_To_Room: " + t.getMessage());
            }
        });
    }

    public void _Search_Data_PhanBo(List<TaiSan> taiSanList, String sSearch, int MaLTS, int MaNTS){
        ArrayList<TaiSan> searchlist = new ArrayList<>();
        if (MaLTS == -1 && MaNTS == -1) {
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            phanBoFView._Search_Data_PhanBo_Success(searchlist);
        }else if (MaLTS != -1 && MaNTS == -1)
        {
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getMaLTS() == MaLTS && taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            phanBoFView._Search_Data_PhanBo_Success(searchlist);
        }else if (MaLTS == -1 && MaNTS != -1){
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getMaNTS() == MaNTS && taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            phanBoFView._Search_Data_PhanBo_Success(searchlist);
        }else if (MaLTS != -1 && MaNTS != -1){
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getMaNTS() == MaNTS && taiSan.getMaLTS() == MaLTS && taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            phanBoFView._Search_Data_PhanBo_Success(searchlist);
        }
    }

    public List<TaiSan> _GetNhomTaiSan_In_AssetInRoom(List<TaiSan> taiSanList){
        Map<Integer, TaiSan> uniqueAssetInRoomIDMap = new LinkedHashMap<>();
        for (TaiSan taiSan : taiSanList) {
            uniqueAssetInRoomIDMap.putIfAbsent(taiSan.getMaNTS(), taiSan);
        }
        return new ArrayList<>(uniqueAssetInRoomIDMap.values());
    }

    public List<TaiSan> _GetLoaiTaiSan_In_AssetInRoom(List<TaiSan> taiSanList){
        Map<Integer, TaiSan> uniqueAssetInRoomIDMap = new LinkedHashMap<>();
        for (TaiSan taiSan : taiSanList) {
            uniqueAssetInRoomIDMap.putIfAbsent(taiSan.getMaLTS(), taiSan);
        }
        return new ArrayList<>(uniqueAssetInRoomIDMap.values());
    }


}
