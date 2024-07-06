package com.example.baohongtaisantdmu_admin.Presenter.NguoiDung;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.DonVi;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.NguoiDung.QLDonViFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLDonViFPresenter {
    private QLDonViFView qlDonViFView;

    public QLDonViFPresenter(QLDonViFView qlDonViFView) {
        this.qlDonViFView = qlDonViFView;
    }

    public void _Get_Data_DonVi(){
        ApiServices.kkts_admin_api.get_list_donvi().enqueue(new Callback<List<DonVi>>() {
            @Override
            public void onResponse(Call<List<DonVi>> call, Response<List<DonVi>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlDonViFView._Get_List_Data_DonVi_Success(response.body());
                }else {
                    qlDonViFView._Show_Error("_Get_Data_DonVi","Lấy dữ liệu đơn vị không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<DonVi>> call, Throwable t) {
                qlDonViFView._Show_Error("_Get_Data_DonVi","_Get_Data_DonVi: " + t.getMessage());
            }
        });
    }

    public void _SearchData_DonVi(List<DonVi> donViList, String sSearch){
        ArrayList<DonVi> searchlist = new ArrayList<>();
        for (DonVi donVi : donViList) {
            if (donVi.getTenDV().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(donVi);
            }
        }
        qlDonViFView._Search_Data_DonVi_Success(searchlist);
    }



    public void _Add_New_Data_DonVi(String TenDV, String MoTaDV){
        ApiServices.kkts_admin_api.add_donvi(TenDV, MoTaDV).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlDonViFView._Show_Success("_Add_New_Data_DonVi", "Thêm mới đơn vị thành công !!!");
                    } else {
                        qlDonViFView._Show_Error("_Add_New_Data_DonVi", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlDonViFView._Show_Error("_Add_New_Data_DonVi", "Thêm mới đơn vị thất bại: " + t.getMessage());
            }
        });
    }


    public void _Edit_Data_DonVi(int MaDV, String TenDV, String MoTaDV){
        ApiServices.kkts_admin_api.edit_donvi(MaDV, TenDV, MoTaDV).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlDonViFView._Show_Success("_Edit_Data_DonVi", "Chỉnh sửa đơn vị thành công !!!");
                    } else {
                        qlDonViFView._Show_Error("_Edit_Data_DonVi", objectEdit.getMessage());
                    }
                }else {
                    qlDonViFView._Show_Error("_Edit_Data_DonVi", response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlDonViFView._Show_Error("_Edit_Data_DonVi", "Cập nhật thất bại: " + t.getMessage());
            }
        });
    }

    public void _Delete_Data_DonVi(int MaDV){
        ApiServices.kkts_admin_api.delete_donvi(MaDV).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlDonViFView._Show_Success("_Delete_Data_DonVi", "Xóa đơn vị thành công !!!");
                    } else {
                        qlDonViFView._Show_Error("_Delete_Data_DonVi", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlDonViFView._Show_Error("_Delete_Data", "Xóa chức danh thất bại !!!: " + t.getMessage());
            }
        });

    }


}
