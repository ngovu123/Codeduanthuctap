package com.example.baohongtaisantdmu_admin.Presenter.TaiSan;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.TaiSan.QLNhomTaiSanFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLNhomTaiSanFPresenter {
    private QLNhomTaiSanFView qlNhomTaiSanFView;

    public QLNhomTaiSanFPresenter(QLNhomTaiSanFView qlNhomTaiSanFView) {
        this.qlNhomTaiSanFView = qlNhomTaiSanFView;
    }


    public void _Get_Data_NhomTaiSan(){
        ApiServices.kkts_admin_api.get_list_nhomtaisan().enqueue(new Callback<List<NhomTaiSan>>() {
            @Override
            public void onResponse(Call<List<NhomTaiSan>> call, Response<List<NhomTaiSan>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlNhomTaiSanFView._Get_List_Data_NhomTaiSan_Success(response.body());
                }else {
                    qlNhomTaiSanFView._Show_Error("_Get_Data_NhomTaiSan","Lấy dữ liệu nhóm tài sản không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<NhomTaiSan>> call, Throwable t) {
                qlNhomTaiSanFView._Show_Error("_Get_Data_NhomTaiSan","_Get_Data_NhomTaiSan: " + t.getMessage());
            }
        });
    }

    public void _SearchData_NhomTaiSan(List<NhomTaiSan> nhomTaiSanList, String sSearch){
        ArrayList<NhomTaiSan> searchlist = new ArrayList<>();
        for (NhomTaiSan nhomTaiSan : nhomTaiSanList) {
            if (nhomTaiSan.getTenNTS().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(nhomTaiSan);
            }
        }
        qlNhomTaiSanFView._Search_Data_NhomTaiSan_Success(searchlist);
    }

    public void _Add_New_Data_NhomTaiSan(String TenNTS){
        ApiServices.kkts_admin_api.add_nhomtaisan(TenNTS).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlNhomTaiSanFView._Show_Success("_Add_New_Data_NhomTaiSan", "Thêm mới nhóm tài sản thành công !!!");
                    } else {
                        qlNhomTaiSanFView._Show_Error("_Add_New_Data_NhomTaiSan", objectEdit.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlNhomTaiSanFView._Show_Error("_Add_New_Data_NhomTaiSan", "_Add_New_Data_NhomTaiSan: " + t.getMessage());
            }
        });
    }

    public void _Edit_Data_NhomTaiSan(int MaNTS, String TenNTS){
        ApiServices.kkts_admin_api.edit_nhomtaisan_byMaNTS(MaNTS, TenNTS).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlNhomTaiSanFView._Show_Success("_Edit_Data_NhomTaiSan", "Chỉnh sửa nhóm tài sản thành công !!!");
                    } else {
                        qlNhomTaiSanFView._Show_Error("_Edit_Data_NhomTaiSan", objectEdit.getMessage());
                    }
                }else {
                    qlNhomTaiSanFView._Show_Error("_Edit_Data_NhomTaiSan", response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlNhomTaiSanFView._Show_Error("_Edit_Data_NhomTaiSan", "_Edit_Data_NhomTaiSan: " + t.getMessage());
            }
        });
    }

    public void _Delete_Data_NhomTaiSan(int MaNTS){
        ApiServices.kkts_admin_api.delete_nhomtaisan(MaNTS).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlNhomTaiSanFView._Show_Success("_Delete_Data_NhomTaiSan", "Xóa nhóm tài sản thành công !!!");
                    } else {
                        qlNhomTaiSanFView._Show_Error("_Delete_Data_NhomTaiSan", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlNhomTaiSanFView._Show_Error("_Delete_Data_NhomTaiSan", "_Delete_Data_NhomTaiSan: " + t.getMessage());
            }
        });

    }
}
