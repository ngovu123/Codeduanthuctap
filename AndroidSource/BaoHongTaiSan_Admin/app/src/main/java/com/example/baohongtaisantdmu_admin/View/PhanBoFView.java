package com.example.baohongtaisantdmu_admin.View;

import com.example.baohongtaisantdmu_admin.Model.TaiSan;

import java.util.List;

public interface PhanBoFView {

    void _Get_List_Data_TaiSan_Success(List<TaiSan> taiSanList);

    void _Search_Data_PhanBo_Success(List<TaiSan> taiSanList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);

}
