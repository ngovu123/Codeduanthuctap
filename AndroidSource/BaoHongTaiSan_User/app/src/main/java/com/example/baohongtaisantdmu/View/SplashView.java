package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.NguoiDung;

public interface SplashView {
    void ConnectedNetworkSuccess();
    void ConnectedNetworkError(String MessageError);

    void notLogin();

    void isLogin();

    void _Get_Token_Success(String sToken);

    void _Update_Token_To_Server_Success();

    void _Get_NguoiDung_byEmail_Success(NguoiDung nguoiDung);

    void _Update_Token_To_FireBase_Success(NguoiDung nguoiDung);


    void _Splash_Error(String MessageError);


}
