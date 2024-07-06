package com.example.baohongtaisantdmu_admin.Presenter.NguoiDung;

import android.app.Dialog;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.DonVi;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.Model.PhanQuyen;
import com.example.baohongtaisantdmu_admin.View.NguoiDung.QLNguoiDungFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLNguoiDungFPresenter {
    private QLNguoiDungFView QLNguoiDungFView;

    public QLNguoiDungFPresenter(QLNguoiDungFView QLNguoiDungFView) {
        this.QLNguoiDungFView = QLNguoiDungFView;
    }


    public void _Get_Data_NguoiDung(){
        ApiServices.kkts_admin_api.get_list_nguoidung().enqueue(new Callback<List<NguoiDung>>() {
            @Override
            public void onResponse(Call<List<NguoiDung>> call, Response<List<NguoiDung>> response) {
                if (response.isSuccessful() && response.body() != null){
                    QLNguoiDungFView._Get_List_Data_NguoiDung_Success(response.body());
                }else {
                    QLNguoiDungFView._Show_Error("Get_Data_NguoiDung", "Lấy dữ liệu người dùng không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<NguoiDung>> call, Throwable t) {
                QLNguoiDungFView._Show_Error("Get_Data_NguoiDung",t.getMessage());
            }
        });
    }


    public void _Edit_Data_NguoiDung(int MaND, String HoVaTen, String SDT, String MatKhau, int MaDV, int MaCD, int MaPQ){
        ApiServices.kkts_admin_api.edit_data_nguoidung(MaND, HoVaTen, SDT, MatKhau, MaDV, MaCD, MaPQ).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        QLNguoiDungFView._Show_Success("_Edit_Data_NguoiDung", "Cập nhật người dùng thành công !!!");
                    } else {
                        QLNguoiDungFView._Show_Success("_Edit_Data_NguoiDung", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                QLNguoiDungFView._Show_Error("_Edit_Data_NguoiDung", "Cập nhật người dùng không thành công: " + t.getMessage());
            }
        });
    }



    public void _Add_Data_NguoiDung(String HoVaTen, String SDT, String Email, String MatKhau, int MaPQ, int MaDV, int MaCD, Dialog dialog){
        ApiServices.kkts_admin_api.add_new_nguoidung(HoVaTen, SDT, Email, MatKhau, MaPQ, MaDV, MaCD).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        QLNguoiDungFView._Add_Data_NguoiDung_In_Server_Success(Email, MatKhau, dialog);
                    } else {
                        QLNguoiDungFView._Show_Success("_Add_Data_NguoiDung", objectResponse.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                QLNguoiDungFView._Show_Error("_Add_Data_NguoiDung", "Thêm mới người dùng không thành công: " + t.getMessage());
            }
        });
    }

    public void _Add_Data_NguoiDung_In_FireBase(String sEmail, String sPassword, Dialog dialog){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(sEmail, sPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                QLNguoiDungFView._Add_Data_NguoiDung_In_FireBase_Success(dialog);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                QLNguoiDungFView._Show_Error("_Add_Data_NguoiDung_Firebase", "Thêm mới người dùng không thành công: " + e.getMessage());
            }
        });
    }


    public void _Search_Data_NguoiDung(List<NguoiDung> nguoiDungList, String sSearch){
        ArrayList<NguoiDung> searchlist = new ArrayList<>();
        for (NguoiDung nguoiDung : nguoiDungList) {
            if (nguoiDung.getHoVaTen().toLowerCase().contains(sSearch.toLowerCase()) ||
                    nguoiDung.getEmail().toLowerCase().contains(sSearch.toLowerCase())) {
                searchlist.add(nguoiDung);
            }
        }
        QLNguoiDungFView._Search_Data_NguoiDung_Success(searchlist);
    }

    public void _Get_Data_ChucDanh(){
        ApiServices.kkts_admin_api.get_list_chucdanh().enqueue(new Callback<List<ChucDanh>>() {
            @Override
            public void onResponse(Call<List<ChucDanh>> call, Response<List<ChucDanh>> response) {
                if (response.isSuccessful() && response.body() != null){
                    QLNguoiDungFView._Get_List_Data_ChucDanh_Success(response.body());
                }else {
                    QLNguoiDungFView._Show_Error("Get_Data_ChucDanh","Lấy dữ liệu chức danh không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<ChucDanh>> call, Throwable t) {
                QLNguoiDungFView._Show_Error("Get_Data_ChucDanh","Get_Data_ChucDanh" + t.getMessage());
            }
        });
    }


    public void _Get_Data_DonVi(){
        ApiServices.kkts_admin_api.get_list_donvi().enqueue(new Callback<List<DonVi>>() {
            @Override
            public void onResponse(Call<List<DonVi>> call, Response<List<DonVi>> response) {
                if (response.isSuccessful() && response.body() != null){
                    QLNguoiDungFView._Get_List_Data_DonVi_Success(response.body());
                }else {
                    QLNguoiDungFView._Show_Error("_Get_Data_DonVi","Lấy dữ liệu đơn vị không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<DonVi>> call, Throwable t) {
                QLNguoiDungFView._Show_Error("_Get_Data_DonVi","_Get_Data_DonVi: " + t.getMessage());
            }
        });
    }

    public void _Get_Data_PhanQuyen(){
        ApiServices.kkts_admin_api.get_list_phanquyen().enqueue(new Callback<List<PhanQuyen>>() {
            @Override
            public void onResponse(Call<List<PhanQuyen>> call, Response<List<PhanQuyen>> response) {
                if (response.isSuccessful() && response.body() != null){
                    QLNguoiDungFView._Get_List_Data_PhanQuyen_Success(response.body());
                }else {
                    QLNguoiDungFView._Show_Error("_Get_Data_PhanQuyen","Lấy dữ liệu phân quyền không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<PhanQuyen>> call, Throwable t) {
                QLNguoiDungFView._Show_Error("_Get_Data_PhanQuyen","_Get_Data_PhanQuyen: " + t.getMessage());
            }
        });
    }







}
