package com.example.baohongtaisantdmu_admin.Presenter.NguoiDung;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.NguoiDung.QLChucDanhFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLChucDanhFPresenter {
    private QLChucDanhFView qlChucDanhFView;


    public QLChucDanhFPresenter(QLChucDanhFView qlChucDanhFView) {
        this.qlChucDanhFView = qlChucDanhFView;
    }

    public void _Get_Data_ChucDanh(){
        ApiServices.kkts_admin_api.get_list_chucdanh().enqueue(new Callback<List<ChucDanh>>() {
            @Override
            public void onResponse(Call<List<ChucDanh>> call, Response<List<ChucDanh>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlChucDanhFView._Get_List_Data_ChucDanh_Success(response.body());
                }else {
                    qlChucDanhFView._Show_Error("Get_Data_ChucDanh","Lấy dữ liệu đơn vị không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<ChucDanh>> call, Throwable t) {
                qlChucDanhFView._Show_Error("Get_Data_ChucDanh","Get_Data_ChucDanh" + t.getMessage());
            }
        });
    }

    public void _SearchData_ChucDanh(List<ChucDanh> chucDanhList, String sSearch){
        ArrayList<ChucDanh> searchlist = new ArrayList<>();
        for (ChucDanh chucDanh : chucDanhList) {
            if (chucDanh.getTenCD().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(chucDanh);
            }
        }
        qlChucDanhFView._Search_Data_ChucDanh_Success(searchlist);
    }

    public void _Add_New_Data(String TenCD, String MoTaCD){
        ApiServices.kkts_admin_api.add_chucdanh(TenCD, MoTaCD).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlChucDanhFView._Show_Success("_Add_New_Data", "Thêm mới chức danh thành công !!!");
                    } else {
                        qlChucDanhFView._Show_Error("_Add_New_Data", objectEdit.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlChucDanhFView._Show_Error("_Add_New_Data", "Thêm mới thất bại !!!: " + t.getMessage());
            }
        });
    }

    public void _Edit_Data(int MaCD, String TenCD, String MoTaCD){
        ApiServices.kkts_admin_api.edit_chucdanh(MaCD, TenCD, MoTaCD).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlChucDanhFView._Show_Success("_Edit_Data", "Chỉnh sửa chức danh thành công !!!");
                    } else {
                        qlChucDanhFView._Show_Error("_Edit_Data", objectEdit.getMessage());
                    }
                }else {
                    qlChucDanhFView._Show_Error("_Edit_Data", response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlChucDanhFView._Show_Error("_Edit_Data", "Cập nhật thất bại !!!: " + t.getMessage());
            }
        });
    }

    public void _Delete_Data(int MaCD){
        ApiServices.kkts_admin_api.delete_chucdanh(MaCD).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlChucDanhFView._Show_Success("_Delete_Data", "Xóa chức danh thành công !!!");
                    } else {
                        qlChucDanhFView._Show_Error("_Delete_Data", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlChucDanhFView._Show_Error("_Delete_Data", "Xóa chức danh thất bại !!!: " + t.getMessage());
            }
        });

    }

}
