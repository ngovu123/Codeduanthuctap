package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.ThongKe;

import java.util.List;

public interface HomePageFView {
    void _GetSoLieuSuccess(ThongKe thongKe);

    void _Get_Data_Info_Admin_Success(List<NguoiDung> nguoiDungList);

    void _Get_Data_Info_Admin_Error();


    void _GetSoLieuError();
}
