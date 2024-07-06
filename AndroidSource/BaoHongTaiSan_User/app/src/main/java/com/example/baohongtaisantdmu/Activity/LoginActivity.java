package com.example.baohongtaisantdmu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Presenter.LoginPresenter;
import com.example.baohongtaisantdmu.Presenter.UtillityPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.LoginView;
import com.example.baohongtaisantdmu.View.UtillityView;
import com.example.baohongtaisantdmu.databinding.ActivityLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements LoginView {


    ActivityLoginBinding binding;
    private GoogleSignInClient client;
    private LoginPresenter loginPresenter;
    private LoginView loginView;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginPresenter = new LoginPresenter(this);


        client = GoogleSignIn.getClient(LoginActivity.this, Logged.getInstance().getGoogleSignInOptions());

        binding.btnLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = client.getSignInIntent();
                startActivityForResult(intent, 99);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99) {
            loginPresenter._Login_Email_With_Firebase(data, account, client);
        }
    }

    @Override
    public void _Login_Firebase_Success(GoogleSignInAccount account) {
        loginPresenter._Get_Token_Firebase(account);
    }


    @Override
    public void _Get_Token_Firebase_Success(NguoiDung nguoiDung) {
        loginPresenter._Insert_NguoiDung_To_Server(nguoiDung, client);
    }


    @Override
    public void _Insert_NguoiDung_To_Server_Sucess(NguoiDung nguoiDung) {
        loginPresenter._Get_NguoiDung_byEmail(nguoiDung.getEmail());
    }


    @Override
    public void _Get_Data_NguoiDung_Success(NguoiDung nguoiDung) {
        loginPresenter._Insert_NguoiDung_To_Firebase(nguoiDung);
    }


    @Override
    public void _Insert_NguoiDung_To_Firebase_Sucess(NguoiDung nguoiDung) {
        Logged.getInstance().setLoggedInUser(nguoiDung);
        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
    }




    @Override
    public void _Login_Error(String Message) {
        DangXuat(Message);
    }



    public void DangXuat(String Message){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        GoogleSignInClient mGoogle = GoogleSignIn.getClient(LoginActivity.this, Logged.getInstance().getGoogleSignInOptions());
        mGoogle.signOut();
        mAuth.signOut();
        Toast.makeText(LoginActivity.this, Message, Toast.LENGTH_SHORT).show();
    }


}