package com.example.baohongtaisantdmu_admin.Presenter.TaiSan;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.Phong;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.View.TaiSan.QLTaiSanFView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class QLTaiSanFPresenter {
    private QLTaiSanFView qlTaiSanFView;

    public QLTaiSanFPresenter(QLTaiSanFView qlTaiSanFView) {
        this.qlTaiSanFView = qlTaiSanFView;
    }

    public void _Get_Data_TaiSan(){
        ApiServices.kkts_admin_api.get_list_taisan().enqueue(new Callback<List<TaiSan>>() {
            @Override
            public void onResponse(Call<List<TaiSan>> call, Response<List<TaiSan>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlTaiSanFView._Get_List_Data_TaiSan_Success(response.body());
                }else {
                    qlTaiSanFView._Show_Error("_Get_Data_TaiSan","Lấy dữ liệu tài sản không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<TaiSan>> call, Throwable t) {
                qlTaiSanFView._Show_Error("_Get_Data_TaiSan","_Get_Data_TaiSan: " + t.getMessage());
            }
        });
    }

    public void _Get_Data_LoaiTaiSan(){
        ApiServices.kkts_admin_api.get_list_loaitaisan().enqueue(new Callback<List<LoaiTaiSan>>() {
            @Override
            public void onResponse(@NonNull Call<List<LoaiTaiSan>> call, @NonNull Response<List<LoaiTaiSan>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlTaiSanFView._Get_List_Data_LoaiTaiSan_Success(response.body());
                }else {
                    qlTaiSanFView._Show_Error("_Get_Data_LoaiTaiSan","Lấy dữ liệu loại tài sản không thành công !!!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LoaiTaiSan>> call, Throwable t) {
                qlTaiSanFView._Show_Error("_Get_Data_LoaiTaiSan","_Get_Data_LoaiTaiSan: " + t.getMessage());
            }
        });
    }

    public void _Get_Data_NhomTaiSan(){
        ApiServices.kkts_admin_api.get_list_nhomtaisan().enqueue(new Callback<List<NhomTaiSan>>() {
            @Override
            public void onResponse(Call<List<NhomTaiSan>> call, Response<List<NhomTaiSan>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlTaiSanFView._Get_List_Data_NhomTaiSan_Success(response.body());
                }else {
                    qlTaiSanFView._Show_Error("_Get_Data_NhomTaiSan","Lấy dữ liệu nhóm tài sản không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<NhomTaiSan>> call, Throwable t) {
                qlTaiSanFView._Show_Error("_Get_Data_NhomTaiSan","_Get_Data_NhomTaiSan: " + t.getMessage());
            }
        });
    }


    public void _Search_Data_TaiSan(List<TaiSan> taiSanList, String sSearch, int MaLTS, int MaNTS){
        ArrayList<TaiSan> searchlist = new ArrayList<>();
        if (MaLTS == -1 && MaNTS == -1) {
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            qlTaiSanFView._Search_Data_TaiSan_Success(searchlist);
        }else if (MaLTS != -1 && MaNTS == -1)
        {
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getMaLTS() == MaLTS && taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            qlTaiSanFView._Search_Data_TaiSan_Success(searchlist);
        }else if (MaLTS == -1 && MaNTS != -1){
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getMaNTS() == MaNTS && taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            qlTaiSanFView._Search_Data_TaiSan_Success(searchlist);
        }else if (MaLTS != -1 && MaNTS != -1){
            for (TaiSan taiSan : taiSanList) {
                if (taiSan.getMaNTS() == MaNTS && taiSan.getMaLTS() == MaLTS && taiSan.getTenTS().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(taiSan);
                }
            }
            qlTaiSanFView._Search_Data_TaiSan_Success(searchlist);
        }
    }


    public void _Add_New_Data_TaiSan(String TenTS, int MaNTS, int MaLTS, int GiaTri, int SoLuong, String HSX, String NSX, int NamSX, String GhiChu){
        ApiServices.kkts_admin_api.add_data_taisan(TenTS, MaNTS, MaLTS, GiaTri, SoLuong, HSX, NSX, NamSX, GhiChu).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlTaiSanFView._Show_Success("_Add_New_Data_TaiSan", "Thêm mới tài sản thành công !!!");
                    } else {
                        qlTaiSanFView._Show_Error("_Add_New_Data_TaiSan", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlTaiSanFView._Show_Error("_Add_New_Data_TaiSan", "_Add_New_Data_TaiSan: " + t.getMessage());
            }
        });
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
