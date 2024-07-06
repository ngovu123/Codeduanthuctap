package com.example.baohongtaisantdmu.View;

import com.example.baohongtaisantdmu.Model.Chat;

import java.util.List;

public interface ChatView {
    void _Send_Message_Success();

    void _Send_Message_Error(String Message);

    void _Get_Data_Chat_Success(List<Chat> chatList);

    void _Get_Data_Chat_Error(String Message);
}
