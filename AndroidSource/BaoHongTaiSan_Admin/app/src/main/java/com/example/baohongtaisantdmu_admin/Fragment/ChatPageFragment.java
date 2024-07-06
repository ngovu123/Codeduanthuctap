package com.example.baohongtaisantdmu_admin.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu_admin.Activity.ChatActivity;
import com.example.baohongtaisantdmu_admin.Adapter.AdapterChatPage;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.ChatPageFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.ChatPageFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentChatPageBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class ChatPageFragment extends Fragment implements ChatPageFView {


    private FragmentChatPageBinding binding;
    private ChatPageFPresenter chatPageFPresenter;
    private List<NguoiDung> nguoiDungs;
    private AdapterChatPage adapterChatPage;

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
            chatPageFPresenter._Get_Data_ListUser();

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
    public void _Get_Data_ListUser_Success(List<NguoiDung> nguoiDung) {
        if (nguoiDung != null && getContext() != null){
            nguoiDungs = nguoiDung;
            adapterChatPage = new AdapterChatPage(nguoiDung, new RCVClickItem() {
                @Override
                public void onClickRCV(Object object, String CURD) {
                    NguoiDung nguoiDung1 = (NguoiDung) object;
                    if (CURD.equals("CHAT")){
                        Gson gson = new Gson();
                        Intent intent = new Intent(getContext(), ChatActivity.class);
                        intent.putExtra("NguoiDung", gson.toJson(nguoiDung1));
                        startActivity(intent);
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
    public void _ChatPage_Error(String Type, String Message) {

    }
}