package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.NguoiDung;

import java.util.List;

public interface AssetInRoomView {
    void _GetData_AssetInRoom_Success(List<AssetInRoom> assetInRoomList);
    void _GetData_AssetInRoom_Error(String ErrorMessage);
    void _SearchData_AssetInRoom_Success(List<AssetInRoom> assetInRoomList);

    void _User_In_Room_DataChange(List<NguoiDung> nguoiDungList);



}
