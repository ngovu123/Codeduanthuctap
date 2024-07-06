package com.example.baohongtaisantdmu.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu.Adapter.AdapterHomePage;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.ThongKe;
import com.example.baohongtaisantdmu.Presenter.HomePageFPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.HomePageFView;
import com.example.baohongtaisantdmu.databinding.FragmentHomePageBinding;

import java.util.List;


public class HomePageFragment extends Fragment implements HomePageFView {

    private FragmentHomePageBinding binding;
    private HomePageFPresenter homePageFPresenter;
    private AdapterHomePage adapterHomePage;
    public HomePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePageFPresenter = new HomePageFPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomePageBinding.inflate(inflater, container, false);
        if (getContext() != null) {
            homePageFPresenter._GetSoLieu();
            homePageFPresenter._Get_Data_NguoiDung_Admin();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            binding.rcv.setLayoutManager(linearLayoutManager);
        }
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
    public void _Get_Data_Info_Admin_Success(List<NguoiDung> nguoiDungList) {
        if (getContext() != null){
            adapterHomePage = new AdapterHomePage(getContext(), nguoiDungList);
            binding.rcv.setAdapter(adapterHomePage);
        }
    }

    @Override
    public void _Get_Data_Info_Admin_Error() {

    }

    @Override
    public void _GetSoLieuError() {

    }
}