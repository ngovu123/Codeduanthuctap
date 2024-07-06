package com.example.baohongtaisantdmu.Api;

import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.NotificationData;
import com.example.baohongtaisantdmu.Model.ObjectResponse;
import com.example.baohongtaisantdmu.Model.Phong;
import com.example.baohongtaisantdmu.Model.ThongKe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiServices apiServices = new Retrofit.Builder()
            .baseUrl("https://kkts.vanduc.top/api_tdmu/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices.class);

    ApiServices firebaseServices = new Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices.class);

    // add nguoi dung va update token
    @GET("nguoidung_api.php?cmd=ADD_NEW_NGUOIDUNG")
    Call<ObjectResponse> add_new_nguoidung(
            @Query("HoVaTen") String HoVaTen,
            @Query("Email") String Email,
            @Query("Uid") String UID,
            @Query("Token") String TOKEN,
            @Query("MaPQ") int MaPQ,
            @Query("MaDV") int MaDV,
            @Query("MaCD") int MaCD);

    @GET("nguoidung_api.php?cmd=UPDATE_UID_TOKEN_NGUOIDUNG")
    Call<ObjectResponse> update_uid_token_nguoidung(
            @Query("Email") String Email,
            @Query("Uid") String UID,
            @Query("Token") String TOKEN);

    @GET("nguoidung_api.php?cmd=GET_DATA_NGUOIDUNG_BYEMAIL")
    Call<NguoiDung> get_data_nguoidung_byemail(
            @Query("Email") String Email);


    @GET("nguoidung_api.php?cmd=GET_LIST_DATA_NGUOIDUNG_ADMIN")
    Call<List<NguoiDung>> get_list_data_nguoidung_admin(@Query("MaND") int MaND);

    @GET("api_thongke.php")
    Call<ThongKe> get_thongke();

    @GET("phong_api.php?cmd=GET_DATA_PHONG")
    Call<List<Phong>> get_data_phong();


    @GET("phong_api.php?cmd=GET_DATA_TAISAN_BAOLOI_IN_PHONG")
    Call<List<AssetInRoom>> get_data_asset_in_room(@Query("MaP") int MaP);


    @GET("baohong_api.php?cmd=GET_DATA_BAOHONG_BY_MAND")
    Call<List<AssetInRoom>> get_data_report_asset_by_mand(@Query("MaND") int MaND);

    @GET("baohong_api.php?cmd=ADD_NEW_BAOHONG")
    Call<ObjectResponse> add_new_baoloi(
            @Query("MaPB") int MaPB,
            @Query("MaND") int MaND,
            @Query("TinhTrang") int TinhTrang,
            @Query("MoTa") String MoTa,
            @Query("HinhAnh") String HinhAnh);


    @POST("fcm/send")
    @Headers(
            {
                    "Content-Type: application/json",
                    "Authorization: key=AAAAWvAmL58:APA91bFFZKlE1DWmALVBzCE5h6Oj_8NAMjG4BdewwLxRsvV4Q1Cwkb1QciPin8I6ZUCrN2cT1-FmVMYNag0snE880ledRZE523FfjOSh8u3fUVFsu7g9W4s2-4nKPHIqS5OnQkJVGKgo"
            }
    )
    Call<NotificationData> _Send_Notification_To_Admin(@Body NotificationData notificationData);

}
