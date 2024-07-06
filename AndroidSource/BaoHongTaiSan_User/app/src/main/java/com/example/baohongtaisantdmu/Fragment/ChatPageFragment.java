package com.example.baohongtaisantdmu.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.baohongtaisantdmu.Activity.ChatActivity;
import com.example.baohongtaisantdmu.Adapter.AdapterChatPage;
import com.example.baohongtaisantdmu.Model.AdapterClick;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Presenter.ChatPageFPresenter;
import com.example.baohongtaisantdmu.View.ChatPageFView;
import com.example.baohongtaisantdmu.databinding.FragmentChatPageBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class ChatPageFragment extends Fragment implements ChatPageFView {

    FragmentChatPageBinding binding;
    private ChatPageFPresenter chatPageFPresenter;
    private AdapterChatPage adapterChatPage;
    private List<NguoiDung> nguoiDungs;

    public ChatPageFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatPageFPresenter = new ChatPageFPresenter(this);
        nguoiDungs = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatPageBinding.inflate(inflater, container, false);
        if (getContext() != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.rcv.setLayoutManager(linearLayoutManager);
            chatPageFPresenter._Get_Data_ListUser_Admin();
            binding.sv.clearFocus();
            binding.sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (getContext() != null && adapterChatPage != null){
                        chatPageFPresenter._SearchData_ChatPage(nguoiDungs, newText);
                    }
                    return false;
                }
            });

        }
        return binding.getRoot();
    }


    @Override
    public void _Get_Data_ListUser_Admin_Success(List<NguoiDung> nguoiDungList) {

        if (getContext() != null && nguoiDungList != null){
            nguoiDungs = nguoiDungList;
            adapterChatPage = new AdapterChatPage(nguoiDungList, new AdapterClick() {
                @Override
                public void onClickRCV(Object object, String CURD) {
                    NguoiDung nguoiDung1 = (NguoiDung) object;
                    if (CURD.equals("CHAT")){
                        Gson gson = new Gson();
                        Intent intent = new Intent(getContext(), ChatActivity.class);
                        intent.putExtra("NguoiDung", gson.toJson(nguoiDung1));
                        startActivity(intent);
                    }else if (CURD.equals("CALL")){
                        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions((Activity) requireContext(),new String[]{android.Manifest.permission.CALL_PHONE},100);
                        }else {
                            Intent i = new Intent(Intent.ACTION_CALL);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.setData(Uri.parse("tel:"+ nguoiDung1.getSoDienThoai()));
                            startActivity(i);
                        }
                    }
                }
            });
            binding.rcv.setAdapter(adapterChatPage);
        }
    }

    @Override
    public void _Search_Data_ChatPage_Success(List<NguoiDung> nguoiDungList) {
        if (getContext() != null && adapterChatPage != null){
            adapterChatPage.searchDataList(nguoiDungList);
        }
    }

    @Override
    public void _Get_Data_ListUser_Admin_Error(String Message) {

    }
}