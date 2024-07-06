package com.example.baohongtaisantdmu_admin.View.NguoiDung;

import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;

import java.util.List;

public interface QLChucDanhFView {
    void _Get_List_Data_ChucDanh_Success(List<ChucDanh> chucDanhList);

    void _Search_Data_ChucDanh_Success(List<ChucDanh> chucDanhList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);


}
