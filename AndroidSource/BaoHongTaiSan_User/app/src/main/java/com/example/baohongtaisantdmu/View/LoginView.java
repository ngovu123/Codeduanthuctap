package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginView {
    void _Login_Firebase_Success(GoogleSignInAccount account);

    void _Get_Token_Firebase_Success(NguoiDung nguoiDung);

    void _Insert_NguoiDung_To_Server_Sucess(NguoiDung nguoiDung);


    void _Insert_NguoiDung_To_Firebase_Sucess(NguoiDung nguoiDung);


    void _Get_Data_NguoiDung_Success(NguoiDung nguoiDung);

    void _Login_Error(String Message);


}
