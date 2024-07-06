package com.example.baohongtaisantdmu.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.databinding.FragmentManageReportAssetBinding;
import com.example.baohongtaisantdmu.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {


    FragmentProfileBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        if (getContext() != null){
            Glide.with(this).load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).error(R.drawable.logo_tdmu_2).into(binding.imgProfile);
            binding.ProfileTxtEmailEdit.setText(Logged.getInstance().getNguoiDung().getEmail());
            binding.ProfileTxtSDTEdit.setText(Logged.getInstance().getNguoiDung().getSoDienThoai());
            binding.ProfileTxtTenDangNhapEdit.setText(Logged.getInstance().getNguoiDung().getEmail());
            binding.ProfileTxtFullnameEdit.setText(Logged.getInstance().getNguoiDung().getHoVaTen());
        }
        return binding.getRoot();
    }
}