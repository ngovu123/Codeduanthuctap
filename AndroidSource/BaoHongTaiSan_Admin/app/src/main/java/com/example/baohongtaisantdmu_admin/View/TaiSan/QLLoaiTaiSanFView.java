package com.example.baohongtaisantdmu_admin.View.TaiSan;

import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;

import java.util.List;

public interface QLLoaiTaiSanFView {
    void _Get_List_Data_LoaiTaiSan_Success(List<LoaiTaiSan> loaiTaiSanList);

    void _Search_Data_LoaiTaiSan_Success(List<LoaiTaiSan> loaiTaiSanList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);

}
