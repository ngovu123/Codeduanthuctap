package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.NguoiDung;

import java.util.List;

public interface ChatPageFView {
    void _Get_Data_ListUser_Admin_Success(List<NguoiDung> nguoiDung);

    void _Search_Data_ChatPage_Success(List<NguoiDung> nguoiDungList);


    void _Get_Data_ListUser_Admin_Error(String Message);

}
