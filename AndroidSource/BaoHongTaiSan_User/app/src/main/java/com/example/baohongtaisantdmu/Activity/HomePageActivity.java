package com.example.baohongtaisantdmu.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.baohongtaisantdmu.Fragment.ChatPageFragment;
import com.example.baohongtaisantdmu.Fragment.HomePageFragment;
import com.example.baohongtaisantdmu.Fragment.ManageReportAssetFragment;
import com.example.baohongtaisantdmu.Fragment.ProfileFragment;
import com.example.baohongtaisantdmu.Fragment.SARFragment;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Presenter.HomePagePresenter;
import com.example.baohongtaisantdmu.Presenter.UtillityPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.HomePageView;
import com.example.baohongtaisantdmu.View.UtillityView;
import com.example.baohongtaisantdmu.databinding.ActivityHomePageBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomePageView {


    ActivityHomePageBinding binding;
    private String currentFrament_NAV = "";
    private HomePagePresenter homePagePresenter;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homePagePresenter = new HomePagePresenter(this);
        binding.navView.setNavigationItemSelectedListener(this);

        setSupportActionBar(binding.toolbar);
        binding.Title.setText("Trang chủ");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_nav, R.string.close_nav);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            Open_NAV_TrangChu(0);
            binding.navView.setCheckedItem(R.id.nav_trangchu);
        }

        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_trangchu) { // click Trang Chủ
                    Open_NAV_TrangChu(1);
                } else if (item.getItemId() == R.id.bottom_manager_baohong) { // Click Quản lý thông tin báo hỏng
                    Open_NAV_QuanLy_ThongBaoThietBi(1);
                } else if (item.getItemId() == R.id.bottom_baohong_tracuu) { // Click Tra cứu và báo hỏng
                    Open_NAV_TraCuu(1);
                } else if (item.getItemId() == R.id.bottom_nhantin) { // Click Nhắn tin
                    Open_NAV_NhanTin(1);
                } else if (item.getItemId() == R.id.bottom_manager_info) { // click quản lý thông tin cá nhân
                    Open_NAV_QuanLy_ThongTinCaNhan(1);
                }
                return true;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        ((TextView)binding.navView.getHeaderView(0).findViewById(R.id.tvDisplayName)).setText(Logged.getInstance().getNguoiDung().getHoVaTen());
        ((TextView)binding.navView.getHeaderView(0).findViewById(R.id.tvEmail)).setText(Logged.getInstance().getNguoiDung().getEmail());
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_trangchu) { // click trang chủ
            Open_NAV_TrangChu(0);
        } else if (item.getItemId() == R.id.nav_manager_info) { // click quản lý thông tin cá nhân
            Open_NAV_QuanLy_ThongTinCaNhan(0);
        } else if (item.getItemId() == R.id.nav_manager_baohong) { // click quản lý thông báo thiết bị
            Open_NAV_QuanLy_ThongBaoThietBi(0);
        } else if (item.getItemId() == R.id.nav_baohong_tracuu) { // click tra cứu và báo hỏng
            Open_NAV_TraCuu(0);
        } else if (item.getItemId() == R.id.nav_nhantin) { // click nhắn tin
            Open_NAV_NhanTin(0);
        } else if (item.getItemId() == R.id.nav_btnLogout) {
            DangXuat();
        }


        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void Open_NAV_TrangChu(int OptionNAV) {
        if (currentFrament_NAV != "nav_trangchu") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomePageFragment()).commit();
            binding.Title.setText("Trang chủ");
            currentFrament_NAV = "nav_trangchu";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_trangchu).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_trangchu);
            }
        }
    }

    private void Open_NAV_QuanLy_ThongTinCaNhan(int OptionNAV) {
        if (currentFrament_NAV != "nav_manager_info") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ProfileFragment()).commit();
            binding.Title.setText("Quản lý thông tin cá nhân");
            currentFrament_NAV = "nav_manager_info";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_manager_info).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_manager_info);
            }
        }
    }

    private void Open_NAV_QuanLy_ThongBaoThietBi(int OptionNAV) {
        if (currentFrament_NAV != "nav_manager_thietbi") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ManageReportAssetFragment()).commit();
            binding.Title.setText("Quản lý thông báo thiết bị");
            currentFrament_NAV = "nav_manager_thietbi";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_manager_baohong).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_manager_baohong);
            }
        }
    }


    private void Open_NAV_TraCuu(int OptionNAV) {
        if (currentFrament_NAV != "nav_tracuu") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SARFragment()).commit();
            binding.Title.setText("Tra cứu & Báo hỏng");
            currentFrament_NAV = "nav_tracuu";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_baohong_tracuu).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_baohong_tracuu);
            }
        }
    }

    private void Open_NAV_NhanTin(int OptionNAV) {
        if (currentFrament_NAV != "nav_nhantin") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ChatPageFragment()).commit();
            binding.Title.setText("Nhắn tin trực tuyến");
            currentFrament_NAV = "nav_nhantin";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_nhantin).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_nhantin);
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




    public void DangXuat(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        GoogleSignInClient mGoogle = GoogleSignIn.getClient(HomePageActivity.this, Logged.getInstance().getGoogleSignInOptions());
        mGoogle.signOut();
        mAuth.signOut();
        Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }

}