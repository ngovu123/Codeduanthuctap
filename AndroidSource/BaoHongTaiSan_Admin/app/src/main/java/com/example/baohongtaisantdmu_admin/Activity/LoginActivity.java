package com.example.baohongtaisantdmu_admin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Presenter.LoginPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.LoginView;
import com.example.baohongtaisantdmu_admin.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements LoginView {

    ActivityLoginBinding binding;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginPresenter = new LoginPresenter(this);

        binding.btnLoginUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.txtUsername.getText().toString().isEmpty() || binding.txtPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin đăng nhập !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginPresenter._Login_With_Server(binding.txtUsername.getText().toString(), binding.txtPassword.getText().toString());
            }
        });
    }

    @Override
    public void Login_Server_Success(NguoiDung nguoiDung) {
        //Logged.getInstance().setLoggedInUser(nguoiDung);
        loginPresenter._Login_With_Firebase(binding.txtUsername.getText().toString().trim(), binding.txtPassword.getText().toString().trim(), nguoiDung);
    }


    @Override
    public void Login_Firebase_Success(NguoiDung nguoiDung) {
        loginPresenter._Update_Token_To_Server(nguoiDung);
    }

    @Override
    public void _Update_Token_To_Server_Success(NguoiDung nguoiDung) {
        loginPresenter._Insert_NguoiDung_To_Firebase(nguoiDung);
    }

    @Override
    public void _Update_Token_To_Firebase_Success(NguoiDung nguoiDung) {
        Logged.getInstance().setLoggedInUser(nguoiDung);
        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
    }



    @Override
    public void _Login_Error(String Type, String Message) {
        if (Type.equals("DangXuat")){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            GoogleSignInClient mGoogle = GoogleSignIn.getClient(LoginActivity.this, Logged.getInstance().getGoogleSignInOptions());
            mGoogle.signOut();
            mAuth.signOut();
            Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        }else {
            System.out.println(Message);
            Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        }
    }


}