package com.example.baohongtaisantdmu_admin.View;

import com.example.baohongtaisantdmu_admin.Model.NguoiDung;

public interface LoginView {
    void Login_Server_Success(NguoiDung nguoiDung);


    void Login_Firebase_Success(NguoiDung nguoiDung);

    void _Update_Token_To_Server_Success(NguoiDung nguoiDung);

    void _Update_Token_To_Firebase_Success(NguoiDung nguoiDung);




    void _Login_Error(String Type, String Message);


}
