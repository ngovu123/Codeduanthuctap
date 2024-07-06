package com.example.baohongtaisantdmu_admin.Presenter;

import android.annotation.SuppressLint;


import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.ThongKe;
import com.example.baohongtaisantdmu_admin.View.HomePageFView;

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




}
