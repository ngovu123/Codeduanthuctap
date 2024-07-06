package com.example.baohongtaisantdmu_admin.Api;


import com.example.baohongtaisantdmu_admin.Model.BaoHong;
import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.DonVi;
import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NotificationData;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.PhanQuyen;
import com.example.baohongtaisantdmu_admin.Model.Phong;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.Model.ThongKe;
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

    ApiServices kkts_admin_api = new Retrofit.Builder()
            .baseUrl("https://kkts.vanduc.top/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices.class);

    ApiServices firebaseServices = new Retrofit.Builder()
            .baseUrl("https://fcm.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices.class);

    // đơn vị
    @GET("api/load_data_donvi.php")
    Call<List<DonVi>> get_list_donvi();

    @GET("api/edit_data_donvi.php")
    Call<ObjectResponse> edit_donvi(@Query("MaDV") int madv,
                                    @Query("TenDV") String tendv,
                                    @Query("MoTaDV") String motadv);

    @GET("api/add_data_donvi.php")
    Call<ObjectResponse> add_donvi(@Query("TenDV") String tendv,
                                  @Query("MoTaDV") String motadv);

    @GET("api/delete_data_donvi.php")
    Call<ObjectResponse> delete_donvi(@Query("MaDV") int madv);
    // end api dv


    // Chức danh
    @GET("api/load_data_chucdanh.php")
    Call<List<ChucDanh>> get_list_chucdanh();

    @GET("api/edit_data_chucdanh.php")
    Call<ObjectResponse> edit_chucdanh(@Query("MaCD") int macd,
                                      @Query("TenCD") String tencd,
                                      @Query("MoTaCD") String motacd);

    @GET("api/add_data_chucdanh.php")
    Call<ObjectResponse> add_chucdanh(@Query("TenCD") String tencd,
                                     @Query("MoTaCD") String motacd);

    @GET("api/delete_data_chucdanh.php")
    Call<ObjectResponse> delete_chucdanh(@Query("MaCD") int macd);

    // end api chức danh


    // Phòng
    @GET("api/load_data_phong.php")
    Call<List<Phong>> get_list_phong();

    @GET("api/add_data_phong.php")
    Call<ObjectResponse> add_phong(@Query("TenP") String tenp,
                                  @Query("MaKVP") int MaKVP,
                                  @Query("MaLP") int MaLP);

    @GET("api/edit_data_phong.php")
    Call<ObjectResponse> edit_phong(@Query("MaP") int MaP,
                                   @Query("TenP") String tenp,
                                   @Query("MaKVP") int MaKVP,
                                   @Query("MaLP") int MaLP);

    @GET("api/delete_data_phong.php")
    Call<ObjectResponse> delete_phong(@Query("MaP") int MaP);

    // End api phòng


    // Loại phòng
    @GET("api/load_data_loaiphong.php")
    Call<List<LoaiPhong>> get_list_loaiphong();

    @GET("api/edit_data_loaiphong.php")
    Call<ObjectResponse> edit_loaiphong(@Query("MaLP") int malp,
                                       @Query("TenLP") String tenlp);

    @GET("api/add_data_loaiphong.php")
    Call<ObjectResponse> add_loaiphong(@Query("TenLP") String tenlp);

    @GET("api/delete_data_loaiphong.php")
    Call<ObjectResponse> delete_loaiphong(@Query("MaLP") int malp);

    // End api loại phòng


    // Khu vực phòng
    @GET("api/load_data_khuvucphong.php")
    Call<List<KhuVucPhong>> get_list_khuvucphong();

    @GET("api/edit_data_khuvucphong.php")
    Call<ObjectResponse> edit_khuvucphong(@Query("MaKVP") int makvp,
                                         @Query("TenKVP") String tenkvp);

    @GET("api/add_data_khuvucphong.php")
    Call<ObjectResponse> add_khuvucphong(@Query("TenKVP") String tenkvp);

    @GET("api/delete_data_khuvucphong.php")
    Call<ObjectResponse> delete_khuvucphong(@Query("MaKVP") int makvp);

    // End api khu vực phòng


    //tai san
    @GET("api/load_data_taisan.php")
    Call<List<TaiSan>> get_list_taisan();

    @GET("api/add_data_taisan.php")
    Call<ObjectResponse> add_data_taisan(@Query("TenTS") String TenTS,
                                        @Query("MaNTS") int MaNTS,
                                        @Query("MaLTS") int MaLTS,
                                        @Query("GiaTri") int GiaTri,
                                        @Query("SoLuong") int SoLuong,
                                        @Query("HangSanXuat") String HSX,
                                        @Query("NuocSanXuat") String NSX,
                                        @Query("NamSanXuat") int NamSX,
                                        @Query("GhiChu") String GhiChu);


    // end api tai san


    //Nhóm tài sản

    @GET("api/load_data_nhomtaisan.php")
    Call<List<NhomTaiSan>> get_list_nhomtaisan();


    @GET("api/edit_data_nhomtaisan.php")
    Call<ObjectResponse> edit_nhomtaisan_byMaNTS(@Query("MaNTS") int MaNTS,
                                                @Query("TenNTS") String TenNTS);

    @GET("api/add_data_nhomtaisan.php")
    Call<ObjectResponse> add_nhomtaisan(@Query("TenNTS") String TenNTS);


    @GET("api/delete_data_nhomtaisan.php")
    Call<ObjectResponse> delete_nhomtaisan(@Query("MaNTS") int MaNTS);

    // End api nhóm tài sản


    //Loại tài sản

    @GET("api/load_data_loaitaisan.php")
    Call<List<LoaiTaiSan>> get_list_loaitaisan();

    @GET("api/edit_data_loaitaisan.php")
    Call<ObjectResponse> edit_data_loaitaisan_byMaLTS(@Query("MaLTS") int MaLTS,
                                                     @Query("TenLTS") String TenLTS);

    @GET("api/add_data_loaitaisan.php")
    Call<ObjectResponse> add_data_loaitaisan(@Query("TenLTS") String TenLTS);

    @GET("api/delete_data_loaitaisan.php")
    Call<ObjectResponse> delete_data_loaitaisan(@Query("MaLTS") int MaLTS);


    // End api loại tài sản

    @GET("api/load_data_phanbo_byMaP.php")
    Call<List<PhanBo>> get_list_phanbo_byMaP(@Query("MaP") int MaP);

    @GET("api/add_data_phanbo.php")
    Call<ObjectResponse> add_new_phanbo(
            @Query("MaTS") int MaTS,
            @Query("MaND") int MaND,
            @Query("MaP") int MaP,
            @Query("SoLuongCanThem") int SLCanThem,
            @Query("GhiChuThem") String GhiChu

    );


    @GET("api/load_data_nguoidung_byEmail.php")
    Call<NguoiDung> get_nguoidung_byEmail(@Query("Email") String Email);

    @GET("api/load_data_baoloi.php")
    Call<List<BaoHong>> get_list_baohong(@Query("cmd") String Loai);

    @GET("api/load_data_baoloi_byMaND.php")
    Call<List<BaoHong>> get_list_baohong_byMaND(@Query("MaND") int MaND);




    // Người dùng
    @GET("api/load_data_nguoidung.php")
    Call<List<NguoiDung>> get_list_nguoidung();


    @GET("api/edit_token_uid_nguoidung.php")
    Call<ObjectResponse> edit_token_uid_nguoidung(
            @Query("MaND") int MaND,
            @Query("UID") String UID,
            @Query("TOKEN") String TOKEN);


    @GET("api/edit_data_nguoidung.php")
    Call<ObjectResponse> edit_data_nguoidung(
            @Query("MaND") int MaND,
            @Query("HoVaTen") String HoVaTen,
            @Query("SoDienThoai") String SDT,
            @Query("MatKhau") String MatKhau,
            @Query("MaDV") int MaDV,
            @Query("MaCD") int MaCD,
            @Query("MaPQ") int MaPQ);


    @GET("api/add_data_nguoidung.php")
    Call<ObjectResponse> add_new_nguoidung(
            @Query("HoVaTen") String HoVaTen,
            @Query("Email") String Email,
            @Query("MaPQ") int MaPQ,
            @Query("MaDV") int MaDV,
            @Query("MaCD") int MaCD,
            @Query("UID") String UID,
            @Query("TOKEN") String TOKEN);

    @GET("api/add_data_nguoidung_2.php")
    Call<ObjectResponse> add_new_nguoidung(
            @Query("HoVaTen") String HoVaTen,
            @Query("SoDienThoai") String SDT,
            @Query("Email") String Email,
            @Query("MatKhau") String MatKhau,
            @Query("MaPQ") int MaPQ,
            @Query("MaDV") int MaDV,
            @Query("MaCD") int MaCD);

    // End api người dùng



    // phân quyền

    @GET("api/load_data_phanquyen.php")
    Call<List<PhanQuyen>> get_list_phanquyen();


    @GET("api/add_data_phanquyen.php")
    Call<ObjectResponse> add_data_phanquyen(@Query("TenPQ") String Ten,
                                  @Query("MoTaPQ") String MoTa);


    // end api phân quyền


    // báo lỗi
    @GET("api/add_data_baoloi.php")
    Call<ObjectResponse> add_new_baoloi(
            @Query("MaPB") int MaPB,
            @Query("MaND") int MaND,
            @Query("TinhTrang") int TinhTrang,
            @Query("MoTa") String MoTa,
            @Query("HinhAnh") String HinhAnh);

    @GET("api/edit_data_baoloi_byMaBL.php")
    Call<ObjectResponse> edit_data_trangthai_baoloi_byMaBL(
            @Query("MaBL") int MaBL,
            @Query("TrangThai") int TrangThai);


    // end api báo lỗi

    @GET("nguoidung_api.php?cmd=UPDATE_UID_TOKEN_NGUOIDUNG")
    Call<ObjectResponse> update_uid_token_nguoidung(
            @Query("Email") String Email,
            @Query("Uid") String UID,
            @Query("Token") String TOKEN);

    @GET("nguoidung_api.php?cmd=GET_DATA_NGUOIDUNG_BYEMAIL")
    Call<NguoiDung> get_data_nguoidung_byemail(
            @Query("Email") String Email);

    @GET("api_thongke.php")
    Call<ThongKe> get_thongke();

    @GET("phong_api.php?cmd=GET_DATA_TAISAN_BAOLOI_IN_PHONG")
    Call<List<PhanBo>> get_data_asset_in_room(@Query("MaP") int MaP);

    @GET("nguoidung_api.php?cmd=GET_LIST_DATA_NGUOIDUNG_USER")
    Call<List<NguoiDung>> get_list_data_nguoidung_user(@Query("MaND") int MaND);

    @POST("fcm/send")
    @Headers(
            {
                    "Content-Type: application/json",
                    "Authorization: key=AAAAWvAmL58:APA91bFFZKlE1DWmALVBzCE5h6Oj_8NAMjG4BdewwLxRsvV4Q1Cwkb1QciPin8I6ZUCrN2cT1-FmVMYNag0snE880ledRZE523FfjOSh8u3fUVFsu7g9W4s2-4nKPHIqS5OnQkJVGKgo"
            }
    )
    Call<NotificationData> _Send_Notification_To_Admin(@Body NotificationData notificationData);

}
