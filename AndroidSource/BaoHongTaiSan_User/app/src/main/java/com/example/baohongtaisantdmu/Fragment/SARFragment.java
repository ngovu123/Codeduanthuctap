package com.example.baohongtaisantdmu.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu.Adapter.AdapterPhong;
import com.example.baohongtaisantdmu.Model.Phong;
import com.example.baohongtaisantdmu.Presenter.SARFPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.SARFView;
import com.example.baohongtaisantdmu.databinding.FragmentSARBinding;

import java.util.ArrayList;
import java.util.List;


public class SARFragment extends Fragment implements SARFView{

    private FragmentSARBinding binding;
    private SARFPresenter sarfPresenter;
    private AdapterPhong adapterPhong;
    private List<Phong> phongs;


    public SARFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sarfPresenter = new SARFPresenter(this);
        phongs = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSARBinding.inflate(inflater, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.rcv.setLayoutManager(linearLayoutManager);

        binding.sv.clearFocus();
        binding.sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sarfPresenter._SearchData_Phong(phongs, newText);
                return false;
            }
        });



        if (getContext() != null) sarfPresenter._GetData_Phong();

        return binding.getRoot();
    }


    @Override
    public void _GetData_Phong_Success(List<Phong> phongList) {
        phongs = phongList;
        adapterPhong = new AdapterPhong(getContext() ,phongList);
        binding.rcv.setAdapter(adapterPhong);
    }

    @Override
    public void _GetData_Phong_Error(String ErrorMessage) {

    }

    @Override
    public void _SearchData_Phong_Success(List<Phong> phongList) {
        adapterPhong.searchDataList(phongList);
    }

}