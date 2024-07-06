package com.example.baohongtaisantdmu_admin.View.TaiSan;

import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;

import java.util.List;

public interface QLNhomTaiSanFView {
    void _Get_List_Data_NhomTaiSan_Success(List<NhomTaiSan> nhomTaiSanList);

    void _Search_Data_NhomTaiSan_Success(List<NhomTaiSan> nhomTaiSanList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);
}
