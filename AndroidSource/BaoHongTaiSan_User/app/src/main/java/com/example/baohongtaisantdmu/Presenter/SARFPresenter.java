package com.example.baohongtaisantdmu.Presenter;

import android.annotation.SuppressLint;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.Phong;
import com.example.baohongtaisantdmu.Model.ThongKe;
import com.example.baohongtaisantdmu.View.SARFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SARFPresenter {
    private SARFView sarfView;
    private List<Phong> phongList;

    public SARFPresenter(SARFView sarfView) {
        this.sarfView = sarfView;
        this.phongList = new ArrayList<>();
    }

    public void _GetData_Phong(){

        ApiServices.apiServices.get_data_phong().enqueue(new Callback<List<Phong>>() {
            @Override
            public void onResponse(Call<List<Phong>> call, Response<List<Phong>> response) {
                if (response.isSuccessful()){
                    phongList = response.body();
                    sarfView._GetData_Phong_Success(phongList);
                }
            }
            @Override
            public void onFailure(Call<List<Phong>> call, Throwable t) {
                sarfView._GetData_Phong_Error(t.getMessage().toString());
            }
        });
    }

    public void _SearchData_Phong(List<Phong> phongList, String sSearch){
        ArrayList<Phong> searchlist = new ArrayList<>();
        for (Phong phong : phongList) {
            if (phong.getTenP().toLowerCase().contains(sSearch.toLowerCase()) ||
                    phong.getTenLP().toLowerCase().contains(sSearch.toLowerCase()) ||
                    phong.getTenKVP().toLowerCase().contains(sSearch.toLowerCase())) {

                searchlist.add(phong);
            }
        }
        sarfView._SearchData_Phong_Success(searchlist);
    }

}
