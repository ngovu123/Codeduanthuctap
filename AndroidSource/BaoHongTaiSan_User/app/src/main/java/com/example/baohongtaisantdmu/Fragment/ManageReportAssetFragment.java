package com.example.baohongtaisantdmu.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu.Adapter.AdapterManageReportAsset;
import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Presenter.ManageReportAssetFPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.ManageReportAssetFView;
import com.example.baohongtaisantdmu.databinding.FragmentHomePageBinding;
import com.example.baohongtaisantdmu.databinding.FragmentManageReportAssetBinding;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;


public class ManageReportAssetFragment extends Fragment implements ManageReportAssetFView {


    FragmentManageReportAssetBinding binding;
    private ManageReportAssetFPresenter manageReportAssetFPresenter;
    private AdapterManageReportAsset adapterManageReportAsset;
    public ManageReportAssetFragment() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manageReportAssetFPresenter = new ManageReportAssetFPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManageReportAssetBinding.inflate(inflater, container, false);
        if (getContext() != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.rcv.setLayoutManager(linearLayoutManager);
            manageReportAssetFPresenter._Get_ReportAsset();
        }
        return binding.getRoot();
    }

    @Override
    public void _Get_ReportAsset_Success(List<AssetInRoom> assetInRoomList) {
        adapterManageReportAsset = new AdapterManageReportAsset(assetInRoomList, getContext());
        binding.rcv.setAdapter(adapterManageReportAsset);
    }

    @Override
    public void _Get_ReportAsset_Error() {

    }
}