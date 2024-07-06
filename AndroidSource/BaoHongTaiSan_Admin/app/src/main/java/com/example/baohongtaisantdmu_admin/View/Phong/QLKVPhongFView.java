package com.example.baohongtaisantdmu_admin.View.Phong;

import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;

import java.util.List;

public interface QLKVPhongFView {

    void _Get_List_Data_KhuVucPhong_Success(List<KhuVucPhong> khuVucPhongList);

    void _Search_Data_KhuVucPhong_Success(List<KhuVucPhong> khuVucPhongList);

    void _Show_Success(String LoaiShow, String Message);

    void _Show_Error(String LoaiShow, String Message);
}
