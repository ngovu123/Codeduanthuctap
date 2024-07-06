package com.example.baohongtaisantdmu_admin.Presenter.Phong;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.Phong.QLLoaiPhongFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLLoaiPhongFPresenter {

    private QLLoaiPhongFView qlLoaiPhongFView;

    public QLLoaiPhongFPresenter(QLLoaiPhongFView qlLoaiPhongFView) {
        this.qlLoaiPhongFView = qlLoaiPhongFView;
    }

    public void _Get_Data_LoaiPhong(){
        ApiServices.kkts_admin_api.get_list_loaiphong().enqueue(new Callback<List<LoaiPhong>>() {
            @Override
            public void onResponse(Call<List<LoaiPhong>> call, Response<List<LoaiPhong>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlLoaiPhongFView._Get_List_Data_LoaiPhong_Success(response.body());
                }else {
                    qlLoaiPhongFView._Show_Error("_Get_Data_LoaiPhong","Lấy dữ liệu loại phòng không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<LoaiPhong>> call, Throwable t) {
                qlLoaiPhongFView._Show_Error("_Get_Data_LoaiPhong","_Get_Data_LoaiPhong" + t.getMessage());
            }
        });
    }

    public void _SearchData_LoaiPhong(List<LoaiPhong> loaiPhongList, String sSearch){
        ArrayList<LoaiPhong> searchlist = new ArrayList<>();
        for (LoaiPhong loaiPhong : loaiPhongList) {
            if (loaiPhong.getTenLP().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(loaiPhong);
            }
        }
        qlLoaiPhongFView._Search_Data_LoaiPhong_Success(searchlist);
    }

    public void _Add_New_Data_LoaiPhong(String TenLP){
        ApiServices.kkts_admin_api.add_loaiphong(TenLP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlLoaiPhongFView._Show_Success("_Add_New_Data_LoaiPhong", "Thêm mới loại phòng thành công !!!");
                    } else {
                        qlLoaiPhongFView._Show_Error("_Add_New_Data_LoaiPhong", objectEdit.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlLoaiPhongFView._Show_Error("_Add_New_Data_LoaiPhong", "Thêm mới thất bại !!!: " + t.getMessage());
            }
        });
    }

    public void _Edit_Data_LoaiPhong(int MaLP, String TenLP){
        ApiServices.kkts_admin_api.edit_loaiphong(MaLP, TenLP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlLoaiPhongFView._Show_Success("_Edit_Data_LoaiPhong", "Chỉnh sửa loại phòng thành công !!!");
                    } else {
                        qlLoaiPhongFView._Show_Error("_Edit_Data_LoaiPhong", objectEdit.getMessage());
                    }
                }else {
                    qlLoaiPhongFView._Show_Error("_Edit_Data_LoaiPhong", response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlLoaiPhongFView._Show_Error("_Edit_Data_LoaiPhong", "Cập nhật thất bại !!!: " + t.getMessage());
            }
        });
    }

    public void _Delete_Data_LoaiPhong(int MaLP){
        ApiServices.kkts_admin_api.delete_loaiphong(MaLP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlLoaiPhongFView._Show_Success("_Delete_Data_LoaiPhong", "Xóa loại phòng thành công !!!");
                    } else {
                        qlLoaiPhongFView._Show_Error("_Delete_Data_LoaiPhong", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlLoaiPhongFView._Show_Error("_Delete_Data_LoaiPhong", "Xóa loại phòng thất bại !!!: " + t.getMessage());
            }
        });

    }
}
