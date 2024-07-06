package com.example.baohongtaisantdmu_admin.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.baohongtaisantdmu_admin.Fragment.NguoiDung.QLChucDanhFragment;
import com.example.baohongtaisantdmu_admin.Fragment.NguoiDung.QLDonViFragment;
import com.example.baohongtaisantdmu_admin.Fragment.NguoiDung.QLNguoiDungFragment;


public class TabLayoutNguoiDungAdapter extends FragmentStatePagerAdapter {
    public TabLayoutNguoiDungAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new QLDonViFragment();
            case 2:
                return new QLChucDanhFragment();
            default:
                return new QLNguoiDungFragment();
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
                title = "Đơn vị";
                break;
            case 2:
                title = "Chức danh";
                break;
        }
        return title;
    }
}
