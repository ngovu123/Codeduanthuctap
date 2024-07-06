package com.example.baohongtaisantdmu_admin.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu_admin.Model.ThongKe;
import com.example.baohongtaisantdmu_admin.Presenter.HomePageFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.HomePageFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentHomePageBinding;


public class HomePageFragment extends Fragment implements HomePageFView {


    private FragmentHomePageBinding binding;
    private HomePageFPresenter homePageFPresenter;
    public HomePageFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePageFPresenter = new HomePageFPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        if (getContext() != null) homePageFPresenter._GetSoLieu();
        return binding.getRoot();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void _GetSoLieuSuccess(ThongKe thongKe) {
        binding.tvCountNguoiDung.setText(thongKe.getTongSo_NguoiDung()+"");
        binding.tvCountPhong.setText(thongKe.getTongSo_PhongHoc()+"");
        binding.tvCountTaiSan.setText(thongKe.getTongSo_TaiSan()+"");
    }

    @Override
    public void _GetSoLieuError() {

    }
}