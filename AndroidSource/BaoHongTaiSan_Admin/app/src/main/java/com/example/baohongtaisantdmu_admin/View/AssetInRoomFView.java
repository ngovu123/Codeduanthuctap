package com.example.baohongtaisantdmu_admin.View;

import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;

import java.util.List;

public interface AssetInRoomFView {

    void _Get_List_Data_AssetInRoom_Success(List<PhanBo> phanBoList);

    void _Search_Data_AssetInRoom_Success(List<PhanBo> phanBoList);


    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);

}
