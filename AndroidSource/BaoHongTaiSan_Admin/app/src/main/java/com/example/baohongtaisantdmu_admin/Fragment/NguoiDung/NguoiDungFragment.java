package com.example.baohongtaisantdmu_admin.Fragment.NguoiDung;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.TabLayout.TabLayoutNguoiDungAdapter;
import com.example.baohongtaisantdmu_admin.databinding.FragmentHomePageBinding;
import com.example.baohongtaisantdmu_admin.databinding.FragmentNguoiDungBinding;


public class NguoiDungFragment extends Fragment {

    FragmentNguoiDungBinding binding;
    public NguoiDungFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNguoiDungBinding.inflate(inflater, container, false);
        if (getContext() != null){
            TabLayoutNguoiDungAdapter tabLayoutAdapter = new TabLayoutNguoiDungAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            binding.viewPagerND.setAdapter(tabLayoutAdapter);
            binding.tabLayoutND.setupWithViewPager(binding.viewPagerND);
        }
        return binding.getRoot();
    }
}