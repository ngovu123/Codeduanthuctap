package com.example.baohongtaisantdmu_admin.View.TaiSan;

import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.Model.Phong;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;

import java.util.List;

public interface QLTaiSanFView {
    void _Get_List_Data_TaiSan_Success(List<TaiSan> taiSanList);
    void _Get_List_Data_NhomTaiSan_Success(List<NhomTaiSan> nhomTaiSanList);
    void _Get_List_Data_LoaiTaiSan_Success(List<LoaiTaiSan> loaiTaiSanList);


    void _Search_Data_TaiSan_Success(List<TaiSan> taiSanList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);
}
