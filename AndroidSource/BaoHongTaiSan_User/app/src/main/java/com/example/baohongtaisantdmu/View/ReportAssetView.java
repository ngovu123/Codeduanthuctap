package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.NguoiDung;

import java.util.List;

public interface ReportAssetView {
    void _Upload_Img_To_FireBase_Success(String URL_IMG);
    void _Upload_Img_To_FireBase_Error(String Message);
    void _Upload_ReportAsset_To_API_Success(String Message);
    void _Upload_ReportAsset_To_API_Error(String Message);
    void _Get_Data_NguoiDungAdmin_Success(List<NguoiDung> nguoiDungList);
    void _Get_Data_NguoiDungAdmin_Error(String Message);

    void _Push_Notification_Success();
    void _Push_Notification_Error(String Message);


}
