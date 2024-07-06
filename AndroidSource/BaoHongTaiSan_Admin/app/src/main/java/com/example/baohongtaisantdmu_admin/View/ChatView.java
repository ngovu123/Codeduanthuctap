package com.example.baohongtaisantdmu_admin.View;

import com.example.baohongtaisantdmu_admin.Model.Chat;

import java.util.List;

public interface ChatView {
    void _Send_Message_Success();

    void _Send_Message_Error(String Message);

    void _Get_Data_Chat_Success(List<Chat> chatList);

    void _Get_Data_Chat_Error(String Message);

}
