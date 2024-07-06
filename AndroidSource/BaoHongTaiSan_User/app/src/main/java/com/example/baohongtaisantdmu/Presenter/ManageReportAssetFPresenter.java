package com.example.baohongtaisantdmu.Presenter;

import android.annotation.SuppressLint;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.ThongKe;
import com.example.baohongtaisantdmu.View.ManageReportAssetFView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageReportAssetFPresenter {
    ManageReportAssetFView manageReportAssetFView;

    public ManageReportAssetFPresenter(ManageReportAssetFView manageReportAssetFView) {
        this.manageReportAssetFView = manageReportAssetFView;
    }


    public void _Get_ReportAsset(){
        ApiServices.apiServices.get_data_report_asset_by_mand(Logged.getInstance().getNguoiDung().getMaND()).enqueue(new Callback<List<AssetInRoom>>() {
            @Override
            public void onResponse(Call<List<AssetInRoom>> call, Response<List<AssetInRoom>> response) {
                if (response.isSuccessful()){
                    if (response.body().size() > 0)
                    {
                        manageReportAssetFView._Get_ReportAsset_Success(response.body());
                    }else {
                        manageReportAssetFView._Get_ReportAsset_Error();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AssetInRoom>> call, Throwable t) {
                manageReportAssetFView._Get_ReportAsset_Error();
            }
        });
    }

}
