package com.example.baohongtaisantdmu_admin.Presenter;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.BaoHong;
import com.example.baohongtaisantdmu_admin.Model.NotificationData;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.View.QLBaoHongFView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QLBaoHongFPresenter {
    private QLBaoHongFView qlBaoHongFView;

    public QLBaoHongFPresenter(QLBaoHongFView qlBaoHongFView) {
        this.qlBaoHongFView = qlBaoHongFView;
    }

    public void _Get_List_Data_BaoHong(){
        ApiServices.kkts_admin_api.get_list_baohong("Admin").enqueue(new Callback<List<BaoHong>>() {
            @Override
            public void onResponse(Call<List<BaoHong>> call, Response<List<BaoHong>> response) {
                if (response.isSuccessful() && response.body() != null){
                    qlBaoHongFView._Get_List_Data_BaoHong_Success(response.body());
                }else {
                    qlBaoHongFView._Show_Error("_Get_Data_TaiSan","Lấy dữ liệu tài sản không thành công !!!");
                }
            }

            @Override
            public void onFailure(Call<List<BaoHong>> call, Throwable t) {
                qlBaoHongFView._Show_Error("_Get_Data_TaiSan","_Get_Data_TaiSan: " + t.getMessage());
            }
        });
    }


    public void _Set_TrangThai(BaoHong baoHong, int TrangThai) {
        ApiServices.kkts_admin_api.edit_data_trangthai_baoloi_byMaBL(baoHong.getMaBL(), TrangThai).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(@NonNull Call<ObjectResponse> call, @NonNull Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ObjectResponse objectReponse = response.body();
                    if (objectReponse.getCode() == 1){
                        qlBaoHongFView._Set_TrangThai_BaoHong_Success(baoHong);
                    }else {
                        qlBaoHongFView._Show_Error("_Set_TrangThai", "_Set_TrangThai: " + objectReponse.getMessage());
                    }
                    //baoHong.setTrangThai(TrangThai);
                    //sendNotiToUser(baoHong.getMaND(), baoHong.getTenTS(), baoHong.getTenP(), TrangThai, baoHong.getTinhTrang(), baoHong.getMota(), baoHong.getToken());
                    //Toast.makeText(context, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                    //notifyDataSetChanged();
                } else {
                    qlBaoHongFView._Show_Error("_Set_TrangThai", "_Set_TrangThai: error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ObjectResponse> call, @NonNull Throwable t) {
                qlBaoHongFView._Show_Error("_Set_TrangThai", "_Set_TrangThai: " + t.getMessage());
            }
        });
    }


    public void _Push_Notification(NotificationData notificationData){
        ApiServices.firebaseServices._Send_Notification_To_Admin(notificationData).enqueue(new Callback<NotificationData>() {
            @Override
            public void onResponse(Call<NotificationData> call, Response<NotificationData> response) {
                if (response.isSuccessful()){
                    qlBaoHongFView._Push_Notification_Success();
                }
            }

            @Override
            public void onFailure(Call<NotificationData> call, Throwable t) {
                qlBaoHongFView._Push_Notification_Error(t.getMessage());
            }
        });
    }


 /*   private void _Push_Notification_To_User(int MaND, String TenTS, String TenP, int TrangThai, int TinhTrang, String MoTa, String Token) {
        NotificationDataBaoHong notificationDataBaoHong = new NotificationDataBaoHong(MaND, TenTS, TenP, TrangThai, TinhTrang, MoTa, "AdminToUser");
        NotificationSendData notificationSendData = new NotificationSendData(notificationDataBaoHong, Token);
        ApiServices.apiServices_Noti.sendNoti(notificationSendData).enqueue(new Callback<NotificationReponse>() {
            @Override
            public void onResponse(@NonNull Call<NotificationReponse> call, @NonNull Response<NotificationReponse> response) {
                // ok send noti ok
            }

            @Override
            public void onFailure(@NonNull Call<NotificationReponse> call, @NonNull Throwable t) {

            }
        });
    }*/



    public void _Search_Data_BaoHong(List<BaoHong> baoHongs, String sSearch, int TrangThai){
        ArrayList<BaoHong> searchlist = new ArrayList<>();
        if (TrangThai == -1) {
            for (BaoHong baoHong : baoHongs) {
                if (baoHong.getTenP().toLowerCase().contains(sSearch.toLowerCase()) ||
                        baoHong.getTenTS().toLowerCase().contains(sSearch.toLowerCase()) ||
                        baoHong.getHoVaTen().toLowerCase().contains(sSearch.toLowerCase()) ||
                        baoHong.getEmail().toLowerCase().contains(sSearch.toLowerCase())) {
                    searchlist.add(baoHong);
                }
            }
            qlBaoHongFView._Search_Data_TaiSan_Success(searchlist);
        }else {
            for (BaoHong baoHong : baoHongs) {
                if (baoHong.getTrangThai() == TrangThai && (baoHong.getTenP().toLowerCase().contains(sSearch.toLowerCase()) ||
                        baoHong.getTenTS().toLowerCase().contains(sSearch.toLowerCase()) ||
                        baoHong.getHoVaTen().toLowerCase().contains(sSearch.toLowerCase()) ||
                        baoHong.getEmail().toLowerCase().contains(sSearch.toLowerCase()))) {
                    searchlist.add(baoHong);
                }
            }
            qlBaoHongFView._Search_Data_TaiSan_Success(searchlist);
        }
    }

}
