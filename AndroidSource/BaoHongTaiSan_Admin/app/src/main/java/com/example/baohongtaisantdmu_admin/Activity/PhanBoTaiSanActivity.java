package com.example.baohongtaisantdmu_admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.baohongtaisantdmu_admin.Fragment.PhanBoTaiSan.AssetInRoomFragment;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.TabLayout.TabLayoutPBTSAdapter;
import com.example.baohongtaisantdmu_admin.databinding.ActivityPhanBoTaiSanBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class PhanBoTaiSanActivity extends AppCompatActivity {

    private ActivityPhanBoTaiSanBinding binding;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhanBoTaiSanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        TabLayoutPBTSAdapter tabLayoutPBTSAdapter = new TabLayoutPBTSAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, intent.getIntExtra("MaP", -1), intent.getStringExtra("TenP"));
        binding.viewpagerPbts.setAdapter(tabLayoutPBTSAdapter);
        binding.tablayoutPbts.setupWithViewPager(binding.viewpagerPbts);

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        binding.Title.setText("Phân bổ: " + intent.getStringExtra("TenP") + "");

        binding.tablayoutPbts.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    String tag = "android:switcher:" + binding.viewpagerPbts.getId() + ":" + tab.getPosition();
                    Fragment f = getSupportFragmentManager().findFragmentByTag(tag);
                    if (f instanceof AssetInRoomFragment) {
                        ((AssetInRoomFragment) f).onResume();
                    }
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}