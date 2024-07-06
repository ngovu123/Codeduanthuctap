package com.example.baohongtaisantdmu_admin.Presenter.TaiSan;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.TaiSan.QLLoaiTaiSanFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLLoaiTaiSanFPresenter {
    private QLLoaiTaiSanFView qlLoaiTaiSanFView;

    public QLLoaiTaiSanFPresenter(QLLoaiTaiSanFView qlLoaiTaiSanFView) {
        this.qlLoaiTaiSanFView = qlLoaiTaiSanFView;
    }


    public void _Get_Data_LoaiTaiSan(){
        ApiServices.kkts_admin_api.get_list_loaitaisan().enqueue(new Callback<List<LoaiTaiSan>>() {
            @Override
            public void onResponse(Call<List<LoaiTaiSan>> call, Response<List<LoaiTaiSan>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlLoaiTaiSanFView._Get_List_Data_LoaiTaiSan_Success(response.body());
                }else {
                    qlLoaiTaiSanFView._Show_Error("_Get_Data_LoaiTaiSan","Lấy dữ liệu loại tài sản không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<LoaiTaiSan>> call, Throwable t) {
                qlLoaiTaiSanFView._Show_Error("_Get_Data_LoaiTaiSan","_Get_Data_LoaiTaiSan: " + t.getMessage());
            }
        });
    }

    public void _SearchData_LoaiPhong(List<LoaiTaiSan> loaiTaiSanList, String sSearch){
        ArrayList<LoaiTaiSan> searchlist = new ArrayList<>();
        for (LoaiTaiSan loaiTaiSan : loaiTaiSanList) {
            if (loaiTaiSan.getTenLTS().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(loaiTaiSan);
            }
        }
        qlLoaiTaiSanFView._Search_Data_LoaiTaiSan_Success(searchlist);
    }

    public void _Add_New_Data_LoaiTaiSan(String TenLTS){
        ApiServices.kkts_admin_api.add_data_loaitaisan(TenLTS).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlLoaiTaiSanFView._Show_Success("_Add_New_Data_LoaiTaiSan", "Thêm mới loại tài sản thành công !!!");
                    } else {
                        qlLoaiTaiSanFView._Show_Error("_Add_New_Data_LoaiTaiSan", objectEdit.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlLoaiTaiSanFView._Show_Error("_Add_New_Data_LoaiTaiSan", "_Add_New_Data_LoaiTaiSan: " + t.getMessage());
            }
        });
    }

    public void _Edit_Data_LoaiTaiSan(int MaLTS, String TenLTS){
        ApiServices.kkts_admin_api.edit_data_loaitaisan_byMaLTS(MaLTS, TenLTS).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlLoaiTaiSanFView._Show_Success("_Edit_Data_TaiSan", "Chỉnh sửa loại tài sản thành công !!!");
                    } else {
                        qlLoaiTaiSanFView._Show_Error("_Edit_Data_TaiSan", objectEdit.getMessage());
                    }
                }else {
                    qlLoaiTaiSanFView._Show_Error("_Edit_Data_TaiSan", response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlLoaiTaiSanFView._Show_Error("_Edit_Data_TaiSan", "_Edit_Data_TaiSan: " + t.getMessage());
            }
        });
    }

    public void _Delete_Data_LoaiTaiSan(int MaLTS){
        ApiServices.kkts_admin_api.delete_data_loaitaisan(MaLTS).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlLoaiTaiSanFView._Show_Success("_Delete_Data_LoaiTaiSan", "Xóa loại tài sản thành công !!!");
                    } else {
                        qlLoaiTaiSanFView._Show_Error("_Delete_Data_LoaiTaiSan", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlLoaiTaiSanFView._Show_Error("_Delete_Data_LoaiTaiSan", "_Delete_Data_LoaiTaiSan: " + t.getMessage());
            }
        });

    }
}
