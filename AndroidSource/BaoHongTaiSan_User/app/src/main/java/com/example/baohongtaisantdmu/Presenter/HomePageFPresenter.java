package com.example.baohongtaisantdmu.Presenter;

import android.annotation.SuppressLint;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.Phong;
import com.example.baohongtaisantdmu.Model.ThongKe;
import com.example.baohongtaisantdmu.View.HomePageFView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFPresenter {
    private HomePageFView homePageFView;


    public HomePageFPresenter(HomePageFView homePageFView) {
        this.homePageFView = homePageFView;
    }


    public void _GetSoLieu(){
        ApiServices.apiServices.get_thongke().enqueue(new Callback<ThongKe>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ThongKe> call, Response<ThongKe> response) {
                ThongKe thongKe = response.body();
                if (thongKe != null) {
                    homePageFView._GetSoLieuSuccess(thongKe);
                }
            }

            @Override
            public void onFailure(Call<ThongKe> call, Throwable t) {
                homePageFView._GetSoLieuError();
            }
        });
    }

    public void _Get_Data_NguoiDung_Admin(){
        ApiServices.apiServices.get_list_data_nguoidung_admin(Logged.getInstance().getNguoiDung().getMaND()).enqueue(new Callback<List<NguoiDung>>() {
            @Override
            public void onResponse(Call<List<NguoiDung>> call, Response<List<NguoiDung>> response) {
                if (response.isSuccessful() && response.body() != null){
                    homePageFView._Get_Data_Info_Admin_Success(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<NguoiDung>> call, Throwable t) {
                homePageFView._Get_Data_Info_Admin_Error();
            }
        });
    }




}
