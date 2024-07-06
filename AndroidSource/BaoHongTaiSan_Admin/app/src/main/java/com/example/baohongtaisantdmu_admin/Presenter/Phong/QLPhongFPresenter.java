package com.example.baohongtaisantdmu_admin.Presenter.Phong;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.Model.Phong;
import com.example.baohongtaisantdmu_admin.View.Phong.QLPhongFView;
import com.google.protobuf.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLPhongFPresenter {
    private QLPhongFView qlPhongFView;

    public QLPhongFPresenter(QLPhongFView qlPhongFView) {
        this.qlPhongFView = qlPhongFView;
    }



    public void _Get_Data_Phong(){
        ApiServices.kkts_admin_api.get_list_phong().enqueue(new Callback<List<Phong>>() {
            @Override
            public void onResponse(Call<List<Phong>> call, Response<List<Phong>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlPhongFView._Get_List_Data_Phong_Success(response.body());
                }else {
                    qlPhongFView._Show_Error("_Get_Data_Phong","Lấy dữ liệu phòng không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<Phong>> call, Throwable t) {
                qlPhongFView._Show_Error("_Get_Data_Phong","_Get_Data_Phong: " + t.getMessage());
            }
        });
    }


    public void _Get_Data_KhuVucPhong(){
        ApiServices.kkts_admin_api.get_list_khuvucphong().enqueue(new Callback<List<KhuVucPhong>>() {
            @Override
            public void onResponse(Call<List<KhuVucPhong>> call, Response<List<KhuVucPhong>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlPhongFView._Get_List_Data_KhuVucPhong_Success(response.body());
                }else {
                    qlPhongFView._Show_Error("_Get_Data_KhuVucPhong","Lấy dữ liệu khu vực phòng không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<KhuVucPhong>> call, Throwable t) {
                qlPhongFView._Show_Error("_Get_Data_KhuVucPhong","_Get_Data_KhuVucPhong: " + t.getMessage());
            }
        });
    }

    public void _Get_Data_LoaiPhong(){
        ApiServices.kkts_admin_api.get_list_loaiphong().enqueue(new Callback<List<LoaiPhong>>() {
            @Override
            public void onResponse(Call<List<LoaiPhong>> call, Response<List<LoaiPhong>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlPhongFView._Get_List_Data_LoaiPhong_Success(response.body());
                }else {
                    qlPhongFView._Show_Error("_Get_Data_LoaiPhong","Lấy dữ liệu loại phòng không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<LoaiPhong>> call, Throwable t) {
                qlPhongFView._Show_Error("_Get_Data_LoaiPhong","_Get_Data_LoaiPhong: " + t.getMessage());
            }
        });
    }


    public void _Search_Data_Phong(List<Phong> phongList, String sSearch) {
        ArrayList<Phong> searchlist = new ArrayList<>();
        for (Phong phong : phongList) {
            if (phong.getTenP().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(phong);
            }
        }
        qlPhongFView._Search_Data_Phong_Success(searchlist);
    }

    public void _Add_Data_Phong(String TenP, int MaKVP, int MaLP){
        ApiServices.kkts_admin_api.add_phong(TenP, MaKVP, MaLP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlPhongFView._Show_Success("_Add_Data_Phong", "Thêm mới phòng thành công !!!");
                    } else {
                        qlPhongFView._Show_Success("_Add_Data_Phong", objectResponse.getMessage());
                    }
                }else {
                    qlPhongFView._Show_Success("_Add_Data_Phong", "Thua luôn - API sai rồi !!!");
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlPhongFView._Show_Success("_Add_Data_Phong", "_Add_Data_Phong: " + t.getMessage());
            }
        });
    }

    public void _Edit_Data_Phong(int MaP, String TenP, int MaKVP, int MaLP){
        ApiServices.kkts_admin_api.edit_phong(MaP, TenP, MaKVP, MaLP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlPhongFView._Show_Success("_Edit_Data_Phong", "Sửa thông tin phòng thành công !!!");
                    } else {
                        qlPhongFView._Show_Success("_Edit_Data_Phong", objectResponse.getMessage());
                    }
                }else {
                    qlPhongFView._Show_Success("_Edit_Data_Phong", "Thua luôn - API sai rồi !!!");
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlPhongFView._Show_Success("_Edit_Data_Phong", "_Edit_Data_Phong: " + t.getMessage());
            }
        });
    }

    public void _Delete_Data_Phong(int MaP){
        ApiServices.kkts_admin_api.delete_phong(MaP).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        qlPhongFView._Show_Success("_Delete_Data_Phong", "Xóa phòng thành công !!!");
                    } else {
                        qlPhongFView._Show_Success("_Delete_Data_Phong", objectResponse.getMessage());
                    }
                }else {
                    qlPhongFView._Show_Success("_Delete_Data_Phong", "Thua luôn - API sai rồi !!!");
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                qlPhongFView._Show_Success("_Delete_Data_Phong", "_Delete_Data_Phong: " + t.getMessage());
            }
        });
    }



}
