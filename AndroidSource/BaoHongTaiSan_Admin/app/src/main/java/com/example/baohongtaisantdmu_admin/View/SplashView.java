package com.example.baohongtaisantdmu_admin.View;

import com.example.baohongtaisantdmu_admin.Model.NguoiDung;

public interface SplashView {

    void ConnectedNetworkSuccess();
    void ConnectedNetworkError(String MessageError);

    void isLogin();

    void notLogin();

    void _Update_Token_To_Server_Success();

    void _Get_NguoiDung_byEmail_Success(NguoiDung nguoiDung);



    void _Update_Token_To_FireBase_Success(NguoiDung nguoiDung);

    void _Splash_Error(String Type, String MessageError);


}
