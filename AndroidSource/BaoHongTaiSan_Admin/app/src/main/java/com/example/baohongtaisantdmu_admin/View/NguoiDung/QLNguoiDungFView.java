package com.example.baohongtaisantdmu_admin.View.NguoiDung;

import android.app.Dialog;

import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.DonVi;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.PhanQuyen;

import java.util.List;

public interface QLNguoiDungFView {
    void _Get_List_Data_NguoiDung_Success(List<NguoiDung> nguoiDungList);
    void _Get_List_Data_ChucDanh_Success(List<ChucDanh> chucDanhList);
    void _Get_List_Data_DonVi_Success(List<DonVi> donViList);
    void _Get_List_Data_PhanQuyen_Success(List<PhanQuyen> phanQuyens);

    void _Search_Data_NguoiDung_Success(List<NguoiDung> nguoiDungList);


    void _Add_Data_NguoiDung_In_Server_Success(String sEmail, String sMatKhau, Dialog dialog);

    void _Add_Data_NguoiDung_In_FireBase_Success(Dialog dialog);


    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);

}
