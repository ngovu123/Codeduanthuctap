package com.example.baohongtaisantdmu_admin.View.Phong;

import android.app.Dialog;

import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.DonVi;
import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.PhanQuyen;
import com.example.baohongtaisantdmu_admin.Model.Phong;

import java.util.List;

public interface QLPhongFView {

    void _Get_List_Data_Phong_Success(List<Phong> phongList);
    void _Get_List_Data_KhuVucPhong_Success(List<KhuVucPhong> khuVucPhongList);
    void _Get_List_Data_LoaiPhong_Success(List<LoaiPhong> loaiPhongList);


    void _Search_Data_Phong_Success(List<Phong> phongList);




    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);


}
