package com.example.baohongtaisantdmu_admin.View;

import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;

import java.util.List;

public interface ChatPageFView {
    void _Get_Data_ListUser_Success(List<NguoiDung> nguoiDung);

    void _Search_Data_ChatPage_Success(List<NguoiDung> nguoiDungList);

    void _ChatPage_Error(String Type, String Message);
}
