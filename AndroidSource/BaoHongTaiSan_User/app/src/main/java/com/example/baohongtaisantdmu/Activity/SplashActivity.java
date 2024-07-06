package com.example.baohongtaisantdmu.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.ThongKe;
import com.example.baohongtaisantdmu.Presenter.SplashPresenter;
import com.example.baohongtaisantdmu.Presenter.UtillityPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.SplashView;
import com.example.baohongtaisantdmu.View.UtillityView;
import com.example.baohongtaisantdmu.databinding.ActivitySplashBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements SplashView {


    ActivitySplashBinding binding;
    private SplashPresenter splashPresenter;
    @SuppressLint("SetTextI18n")
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


    @SuppressLint("SetTextI18n")
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
        splashPresenter._Get_Token(); // do đã login sẵn, nên chỉ càn gọi hàm get token

    }

    @Override
    public void _Get_Token_Success(String sToken){
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 2");
        splashPresenter._Update_Token_To_Server(sToken);
    }

    @Override
    public void _Update_Token_To_Server_Success() {
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 3");
        splashPresenter._Get_NguoiDung_byEmail();
    }

    @Override
    public void _Get_NguoiDung_byEmail_Success(NguoiDung nguoiDung) {
        binding.tvCheck.setText("Đã đăng nhập - Tiến hành lấy dữ liệu người dùng.... - Bước 4");
        splashPresenter._Update_Token_To_FireBase(nguoiDung);
    }

    @Override
    public void _Update_Token_To_FireBase_Success(NguoiDung nguoiDung) {
        Logged.getInstance().setLoggedInUser(nguoiDung);
        startActivity(new Intent(SplashActivity.this, HomePageActivity.class));
    }

    @Override
    public void _Splash_Error(String MessageError) {
        DangXuat();
    }



    public void DangXuat(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        GoogleSignInClient mGoogle = GoogleSignIn.getClient(SplashActivity.this, Logged.getInstance().getGoogleSignInOptions());
        mGoogle.signOut();
        mAuth.signOut();
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }

    @Override
    public void notLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    }
}