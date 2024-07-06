package com.example.baohongtaisantdmu.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.baohongtaisantdmu.Adapter.AdapterChat;
import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.Chat;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Presenter.ChatPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.ChatView;
import com.example.baohongtaisantdmu.databinding.ActivityChatBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity implements ChatView {

    NguoiDung nguoiDung;
    ActivityChatBinding binding;
    private AdapterChat adapterChat;
    private ChatPresenter chatPresenter;
    private List<Chat> chatList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        chatPresenter = new ChatPresenter(this);
        Gson gson = new Gson();
        nguoiDung = gson.fromJson(getIntent().getStringExtra("NguoiDung"), NguoiDung.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        binding.rcv.setLayoutManager(linearLayoutManager);

        chatPresenter._Get_Data_Chat(nguoiDung);
        binding.tvDisplayName.setText(nguoiDung.getHoVaTen());
        binding.edtMess.setEnabled(false);
        binding.edtMess.setEnabled(false);

        binding.btnSendMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtMess.getText().toString().isEmpty()){
                    Toast.makeText(ChatActivity.this, "Vui lòng nhập tin nhắn...", Toast.LENGTH_SHORT).show();
                    return;
                }
                chatPresenter._Send_Message(nguoiDung.getMaND(), binding.edtMess.getText().toString());
            }
        });
    }



    @Override
    public void _Send_Message_Success() {
        binding.edtMess.setText("");
    }

    @Override
    public void _Send_Message_Error(String Message) {
        Toast.makeText(ChatActivity.this, "Có lỗi khi gửi tin nhắn: " + Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void _Get_Data_Chat_Success(List<Chat> chatList) {
        adapterChat = new AdapterChat(chatList, getApplicationContext());
        binding.rcv.setAdapter(adapterChat);
        binding.edtMess.setEnabled(true);
        binding.edtMess.setEnabled(true);
    }

    @Override
    public void _Get_Data_Chat_Error(String Message) {
        Toast.makeText(ChatActivity.this, "Có lỗi khi lấy dữ liệu tin nhắn: " + Message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}