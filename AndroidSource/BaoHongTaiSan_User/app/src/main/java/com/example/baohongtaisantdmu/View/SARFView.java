package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.Phong;

import java.util.List;

public interface SARFView {
    void _GetData_Phong_Success(List<Phong> phongList);

    void _GetData_Phong_Error(String ErrorMessage);

    void _SearchData_Phong_Success(List<Phong> phongList);


}
