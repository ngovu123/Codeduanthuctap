package com.example.baohongtaisantdmu_admin.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.baohongtaisantdmu_admin.Fragment.PhanBoTaiSan.AssetInRoomFragment;
import com.example.baohongtaisantdmu_admin.Fragment.PhanBoTaiSan.PhanBoFragment;


public class TabLayoutPBTSAdapter extends FragmentStatePagerAdapter {

    private final int MaP;
    private final String TenP;

    public TabLayoutPBTSAdapter(@NonNull FragmentManager fm, int behavior, int MaP, String TenP) {
        super(fm, behavior);
        this.MaP = MaP;
        this.TenP = TenP;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new AssetInRoomFragment(MaP, TenP);
        }
        return new PhanBoFragment(MaP, TenP);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Phân bổ";
                break;
            case 1:
                title = "Danh sách tài sản có trong phòng này";
                break;
        }
        return title;
    }
}
