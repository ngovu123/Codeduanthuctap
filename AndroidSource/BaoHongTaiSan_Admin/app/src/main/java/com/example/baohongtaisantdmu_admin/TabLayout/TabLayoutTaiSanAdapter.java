package com.example.baohongtaisantdmu_admin.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.baohongtaisantdmu_admin.Fragment.TaiSan.QLLoaiTaiSanFragment;
import com.example.baohongtaisantdmu_admin.Fragment.TaiSan.QLNhomTaiSanFragment;
import com.example.baohongtaisantdmu_admin.Fragment.TaiSan.QLTaiSanFragment;


public class TabLayoutTaiSanAdapter extends FragmentStatePagerAdapter {
    public TabLayoutTaiSanAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new QLTaiSanFragment();
            case 1:
                return new QLNhomTaiSanFragment();
            case 2:
                return new QLLoaiTaiSanFragment();
            default:
                return new QLTaiSanFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Tài sản";
                break;
            case 1:
                title = "Nhóm tài sản";
                break;
            case 2:
                title = "Loại tài sản";
                break;
        }
        return title;
    }
}
