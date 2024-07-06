package com.example.baohongtaisantdmu_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Presenter.SplashPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.SplashView;
import com.example.baohongtaisantdmu_admin.databinding.ActivitySplashBinding;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements SplashView {

    ActivitySplashBinding binding;
    private SplashPresenter splashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        splashPresenter = new SplashPresenter(this, SplashActivity.this);
        new CountDownTimer(3000, 1000) {
            int count = 3;
            @Override
            public void onTick(long millisUntilFinished) {
                binding.tvCheck.setText(String.valueOf(count));
                count--;
            }
            @Override
            public void onFinish() {
                /* Kiểm tra kết nối interet */
                binding.tvCheck.setText("Đang kiểm tra kết nối mạng");
                splashPresenter._CheckConnectNetwork();
            }}.start();

    }


    @Override
    public void ConnectedNetworkSuccess() {
        binding.tvCheck.setText("Kiểm tra kết nối mạng kết thúc...");
        splashPresenter._CheckLogin();

    }

    @Override
    public void ConnectedNetworkError(String MessageError) {
        binding.tvCheck.setText(MessageError);
    }

    @Override
    public void isLogin() {
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 1");
        splashPresenter._Update_Token_To_Server();
    }

    @Override
    public void notLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }


    @Override
    public void _Update_Token_To_Server_Success() {
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 2");
        splashPresenter._Get_NguoiDung_byEmail();
    }

    @Override
    public void _Get_NguoiDung_byEmail_Success(NguoiDung nguoiDung) {
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 3");
        splashPresenter._Update_Token_To_FireBase(nguoiDung);
    }

    @Override
    public void _Update_Token_To_FireBase_Success(NguoiDung nguoiDung) {
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 4");
        Logged.getInstance().setLoggedInUser(nguoiDung);
        startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
    }

    @Override
    public void _Splash_Error(String Type, String MessageError) {
        Toast.makeText(this, MessageError, Toast.LENGTH_SHORT).show();
        FirebaseAuth auth =  FirebaseAuth.getInstance();
        auth.signOut();
    }

 /*   @Override
    public void _Get_NguoiDung_byEmail_Error(String Message) {
        Toast.makeText(this, "Lấy dữ liệu người dùng không thành công !!!", Toast.LENGTH_SHORT).show();
        FirebaseAuth auth =  FirebaseAuth.getInstance();
        auth.signOut();
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

    }

    @Override
    public void _Update_Token_Success() {
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 3");
        startActivity(new Intent(SplashActivity.this, HomePageActivity.class));

    }

    @Override
    public void _Update_Token_Error(String Message) {
        Toast.makeText(this, "Cập nhật dữ liệu người dùng không thành công !!!", Toast.LENGTH_SHORT).show();
        FirebaseAuth auth =  FirebaseAuth.getInstance();
        auth.signOut();
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }*/

}