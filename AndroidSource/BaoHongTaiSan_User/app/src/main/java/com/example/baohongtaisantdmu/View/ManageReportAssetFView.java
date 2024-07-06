package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.ThongKe;

import java.util.List;

public interface ManageReportAssetFView {
    void _Get_ReportAsset_Success(List<AssetInRoom> assetInRoomList);
    void _Get_ReportAsset_Error();
}
