package com.example.baohongtaisantdmu_admin.Fragment.Phong;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.TabLayout.TabLayoutPhongAdapter;
import com.example.baohongtaisantdmu_admin.databinding.FragmentPhongBinding;

public class PhongFragment extends Fragment {


    private FragmentPhongBinding binding;
    public PhongFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPhongBinding.inflate(inflater, container, false);
        if (getContext() != null){
            TabLayoutPhongAdapter tabLayoutAdapter = new TabLayoutPhongAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            binding.viewPager.setAdapter(tabLayoutAdapter);
            binding.tabLayout.setupWithViewPager(binding.viewPager);
        }
        return binding.getRoot();
    }
}