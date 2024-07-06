package com.example.baohongtaisantdmu_admin.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.baohongtaisantdmu_admin.Fragment.ChatPageFragment;
import com.example.baohongtaisantdmu_admin.Fragment.HomePageFragment;
import com.example.baohongtaisantdmu_admin.Fragment.NguoiDung.NguoiDungFragment;
import com.example.baohongtaisantdmu_admin.Fragment.Phong.PhongFragment;
import com.example.baohongtaisantdmu_admin.Fragment.QLBaoHongFragment;
import com.example.baohongtaisantdmu_admin.Fragment.TaiSan.TaiSanFragment;
import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.Phong;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.databinding.ActivityHomePageBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomePageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ActivityHomePageBinding binding;
    private String currentFrament_NAV = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navView.setNavigationItemSelectedListener(this);

        setSupportActionBar(binding.toolbar);
        binding.Title.setText("Trang chủ");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open_nav, R.string.close_nav);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) { // Mở home trước
            Open_NAV_TrangChu(0);
            binding.navView.setCheckedItem(R.id.nav_admin_trangchu);
        }


        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_admin_trangchu) {
                    Open_NAV_TrangChu(1);
                } else if (item.getItemId() == R.id.bottom_admin_qlnguoidung) {
                    Open_NAV_QuanLy_NguoiDung(1);
                } else if (item.getItemId() == R.id.bottom_admin_qlphong) {
                    Open_NAV_QuanLy_Phong(1);
                } else if (item.getItemId() == R.id.bottom_admin_qltaisan) {
                    Open_NAV_QuanLy_TaiSan(1);
                } else if (item.getItemId() == R.id.bottom_admin_qlbaohong) {
                    Open_NAV_Quanly_BaoHong(1);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.nav_admin_trangchu) { // click trang chủ
            Open_NAV_TrangChu(0);
        } else if (item.getItemId() == R.id.nav_admin_qlnguoidung) { // click quản lý người dùng
            Open_NAV_QuanLy_NguoiDung(0);
        } else if (item.getItemId() == R.id.nav_admin_qlphong) { // click quản lý phòng
            Open_NAV_QuanLy_Phong(0);
        } else if (item.getItemId() == R.id.nav_admin_qltaisan) { // click quản lý tài sản
            Open_NAV_QuanLy_TaiSan(0);
        } else if (item.getItemId() == R.id.nav_admin_qlbaohong) { // click quản lý báo hỏng
            Open_NAV_Quanly_BaoHong(0);
        }else if (item.getItemId() == R.id.nav_admin_nhantin) {
            Open_NAV_NhanTin(0);
        }
        else if (item.getItemId() == R.id.nav_admin_dangxuat) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            GoogleSignInClient mGoogle = GoogleSignIn.getClient(HomePageActivity.this, Logged.getInstance().getGoogleSignInOptions());
            mGoogle.signOut();
            mAuth.signOut();
            Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void Open_NAV_TrangChu(int OptionNAV) {
        if (currentFrament_NAV != "nav_admin_trangchu") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomePageFragment()).commit();
            binding.Title.setText("Trang chủ");
            currentFrament_NAV = "nav_admin_trangchu";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_admin_trangchu).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_admin_trangchu);
            }
        }
    }

    private void Open_NAV_QuanLy_NguoiDung(int OptionNAV) {
        if (currentFrament_NAV != "nav_admin_qlnguoidung") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NguoiDungFragment()).commit();
            binding.Title.setText("Quản lí người dùng");
            currentFrament_NAV = "nav_admin_qlnguoidung";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_admin_qlnguoidung).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_admin_qlnguoidung);
            }
        }
    }

    private void Open_NAV_QuanLy_Phong(int OptionNAV) {
        if (currentFrament_NAV != "nav_admin_qlphong") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new PhongFragment()).commit();
            binding.Title.setText("Quản lí phòng");
            currentFrament_NAV = "nav_admin_qlphong";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_admin_qlphong).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_admin_qlphong);
            }
        }
    }

    private void Open_NAV_QuanLy_TaiSan(int OptionNAV) {
        if (currentFrament_NAV != "nav_admin_qltaisan") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new TaiSanFragment()).commit();
            binding.Title.setText("Quản lí tài sản");
            currentFrament_NAV = "nav_admin_qltaisan";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_admin_qltaisan).setChecked(true); // set menu bottom
            } else {
                binding.navView.setCheckedItem(R.id.nav_admin_qltaisan);
            }
        }
    }


    private void Open_NAV_Quanly_BaoHong(int OptionNAV) {
        if (currentFrament_NAV != "nav_admin_qlbaohong") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new QLBaoHongFragment()).commit();
            binding.Title.setText("Quản lí báo hỏng");
            currentFrament_NAV = "nav_admin_qlbaohong";
            if (OptionNAV == 0) {
                binding.bottomNav.getMenu().findItem(R.id.bottom_admin_qlbaohong).setChecked(true);
            } else {
                binding.navView.setCheckedItem(R.id.nav_admin_qlbaohong);
            }
        }
    }

    private void Open_NAV_NhanTin(int OptionNAV) {
        if (currentFrament_NAV != "nav_admin_nhantin") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ChatPageFragment()).commit();
            binding.Title.setText("Nhắn tin trực tuyến");
            currentFrament_NAV = "nav_admin_nhantin";
            binding.navView.setCheckedItem(R.id.nav_admin_nhantin);
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}