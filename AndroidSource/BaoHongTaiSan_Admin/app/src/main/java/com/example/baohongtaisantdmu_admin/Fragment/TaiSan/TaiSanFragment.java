package com.example.baohongtaisantdmu_admin.Fragment.TaiSan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.TabLayout.TabLayoutNguoiDungAdapter;
import com.example.baohongtaisantdmu_admin.TabLayout.TabLayoutPhongAdapter;
import com.example.baohongtaisantdmu_admin.TabLayout.TabLayoutTaiSanAdapter;
import com.example.baohongtaisantdmu_admin.databinding.FragmentNguoiDungBinding;
import com.example.baohongtaisantdmu_admin.databinding.FragmentTaiSanBinding;


public class TaiSanFragment extends Fragment {


    private FragmentTaiSanBinding binding;
    public TaiSanFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaiSanBinding.inflate(inflater, container, false);
        if (getContext() != null){
            TabLayoutTaiSanAdapter tabLayoutTaiSanAdapter = new TabLayoutTaiSanAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            binding.viewPagerphong.setAdapter(tabLayoutTaiSanAdapter);
            binding.tabLayoutphong.setupWithViewPager(binding.viewPagerphong);
        }
        return binding.getRoot();
    }
}