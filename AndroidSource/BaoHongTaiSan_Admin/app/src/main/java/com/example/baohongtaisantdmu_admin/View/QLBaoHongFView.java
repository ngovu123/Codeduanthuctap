package com.example.baohongtaisantdmu_admin.View;

import com.example.baohongtaisantdmu_admin.Model.BaoHong;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;

import java.util.List;

public interface QLBaoHongFView {

    void _Get_List_Data_BaoHong_Success(List<BaoHong> baoHongList);

    void _Set_TrangThai_BaoHong_Success(BaoHong baoHong);

    void _Push_Notification_Success();

    void _Push_Notification_Error(String Message);

    void _Search_Data_TaiSan_Success(List<BaoHong> baoHongList);
    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);


}
