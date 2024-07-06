package com.example.baohongtaisantdmu_admin.View.NguoiDung;

import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.DonVi;

import java.util.List;

public interface QLDonViFView {
    void _Get_List_Data_DonVi_Success(List<DonVi> donViList);

    void _Search_Data_DonVi_Success(List<DonVi> donViList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);
}
