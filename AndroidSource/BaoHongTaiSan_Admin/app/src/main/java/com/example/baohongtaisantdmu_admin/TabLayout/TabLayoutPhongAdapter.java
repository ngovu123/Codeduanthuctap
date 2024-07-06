package com.example.baohongtaisantdmu_admin.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.baohongtaisantdmu_admin.Fragment.Phong.QLKVPhongFragment;
import com.example.baohongtaisantdmu_admin.Fragment.Phong.QLLoaiPhongFragment;
import com.example.baohongtaisantdmu_admin.Fragment.Phong.QLPhongFragment;

public class TabLayoutPhongAdapter extends FragmentStatePagerAdapter {
    public TabLayoutPhongAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new QLPhongFragment();
            case 1:
                return new QLKVPhongFragment();
            case 2:
                return new QLLoaiPhongFragment();
            default:
                return new QLPhongFragment();
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
                title = "Danh sách";
                break;
            case 1:
                title = "Khu vực phòng";
                break;
            case 2:
                title = "Loại phòng";
                break;
        }
        return title;
    }
}
