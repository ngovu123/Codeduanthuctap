package com.example.baohongtaisantdmu_admin.View.Phong;

import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;

import java.util.List;

public interface QLLoaiPhongFView {

    void _Get_List_Data_LoaiPhong_Success(List<LoaiPhong> loaiPhongList);

    void _Search_Data_LoaiPhong_Success(List<LoaiPhong> loaiPhongList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);

}
