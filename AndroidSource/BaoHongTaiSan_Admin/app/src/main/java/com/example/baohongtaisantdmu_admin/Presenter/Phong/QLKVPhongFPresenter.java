package com.example.baohongtaisantdmu_admin.Presenter.Phong;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.Phong.QLKVPhongFView;
import com.example.baohongtaisantdmu_admin.View.Phong.QLLoaiPhongFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLKVPhongFPresenter {

    private QLKVPhongFView qlkvPhongFView;

    public QLKVPhongFPresenter(QLKVPhongFView qlkvPhongFView) {
        this.qlkvPhongFView = qlkvPhongFView;
    }

    public void _Get_Data_KhuVucPhong(){
        ApiServices.kkts_admin_api.get_list_khuvucphong().enqueue(new Callback<List<KhuVucPhong>>() {
            @Override
            public void onResponse(Call<List<KhuVucPhong>> call, Response<List<KhuVucPhong>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlkvPhongFView._Get_List_Data_KhuVucPhong_Success(response.body());
                }else {
                    qlkvPhongFView._Show_Error("_Get_Data_KhuVucPhong","Lấy dữ liệu khu vực phòng không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<KhuVucPhong>> call, Throwable t) {
                qlkvPhongFView._Show_Error("_Get_Data_KhuVucPhong","_Get_Data_KhuVucPhong" + t.getMessage());
            }
        });
    }

    public void _SearchData_KhuVucPhong(List<KhuVucPhong> khuVucPhongList, String sSearch){
        ArrayList<KhuVucPhong> searchlist = new ArrayList<>();
        for (KhuVucPhong khuVucPhong : khuVucPhongList) {
            if (khuVucPhong.getTenKVP().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(khuVucPhong);
            }
        }
        qlkvPhongFView._Search_Data_KhuVucPhong_Success(searchlist);
    }

    public void _Add_New_Data_KhuVucPhong(String TenKVP){
        ApiServices.kkts_admin_api.add_khuvucphong(TenKVP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlkvPhongFView._Show_Success("_Add_New_Data_KhuVucPhong", "Thêm mới khu vực phòng thành công !!!");
                    } else {
                        qlkvPhongFView._Show_Error("_Add_New_Data_KhuVucPhong", objectEdit.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlkvPhongFView._Show_Error("_Add_New_Data_KhuVucPhong", "Thêm mới thất bại !!!: " + t.getMessage());
            }
        });
    }

    public void _Edit_Data_KhuVucPhong(int MaKVP, String TenKVP){
        ApiServices.kkts_admin_api.edit_khuvucphong(MaKVP, TenKVP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.body() != null && response.isSuccessful())
                {
                    ObjectResponse objectEdit = response.body();
                    if (objectEdit.getCode() == 1) {
                        qlkvPhongFView._Show_Success("_Edit_Data_KhuVucPhong", "Chỉnh sửa khu vực phòng thành công !!!");
                    } else {
                        qlkvPhongFView._Show_Error("_Edit_Data_KhuVucPhong", objectEdit.getMessage());
                    }
                }else {
                    qlkvPhongFView._Show_Error("_Edit_Data_KhuVucPhong", response.message());
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlkvPhongFView._Show_Error("_Edit_Data_KhuVucPhong", "Cập nhật thất bại !!!: " + t.getMessage());
            }
        });
    }

    public void _Delete_Data_KhuVucPhong(int MaKVP){
        ApiServices.kkts_admin_api.delete_khuvucphong(MaKVP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlkvPhongFView._Show_Success("_Delete_Data_KhuVucPhong", "Xóa khu vực phòng thành công !!!");
                    } else {
                        qlkvPhongFView._Show_Error("_Delete_Data_KhuVucPhong", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlkvPhongFView._Show_Error("_Delete_Data_KhuVucPhong", "Xóa loại phòng thất bại !!!: " + t.getMessage());
            }
        });

    }
}
