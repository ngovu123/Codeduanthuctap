package com.example.baohongtaisantdmu.Presenter;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.ObjectResponse;
import com.example.baohongtaisantdmu.Model.ThongKe;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView loginView;





    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }


    public void _Login_Email_With_Firebase(@Nullable Intent data, GoogleSignInAccount account, GoogleSignInClient client){

        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            account = task.getResult(ApiException.class);
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            _Login_Email_With_Firebase_Step_2(credential, account, client);

        } catch (ApiException e) {
            loginView._Login_Error(e.getMessage());
        }

    }


    private void _Login_Email_With_Firebase_Step_2(AuthCredential credential, GoogleSignInAccount account, GoogleSignInClient client){
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful()) {
                    loginView._Login_Error("Không thể lấy dữ liệu - Đăng nhập thất bại !!!");
                    return;
                }

                if (!Objects.requireNonNull(account.getEmail()).contains("@student.tdmu.edu.vn")) {
                    loginView._Login_Error("Bạn phải đăng nhập bằng email của trường cấp !!!");
                    client.signOut();
                    FirebaseAuth.getInstance().signOut();
                    return;
                }

                loginView._Login_Firebase_Success(account);


            }

        });
    }


    public void _Get_Token_Firebase(GoogleSignInAccount account){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                NguoiDung nguoiDung = new NguoiDung(5, 7, 2, account.getDisplayName(), account.getEmail(), FirebaseAuth.getInstance().getUid(), task.getResult());
                loginView._Get_Token_Firebase_Success(nguoiDung);
            }
        });
    }

    public void _Insert_NguoiDung_To_Server(NguoiDung nguoiDung, GoogleSignInClient client) {
        ApiServices.apiServices.add_new_nguoidung(nguoiDung.getHoVaTen(), nguoiDung.getEmail(), nguoiDung.getUid(), nguoiDung.getToken(), nguoiDung.getMaPQ(), nguoiDung.getMaDV(), nguoiDung.getMaCD()).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 0){
                        loginView._Login_Error("Thêm người dùng lên server có lỗi: " + objectResponse.getMessage());
                        client.signOut();
                        FirebaseAuth.getInstance().signOut();
                    }else {
                        loginView._Insert_NguoiDung_To_Server_Sucess(nguoiDung);
                    }
                }else {
                    loginView._Login_Error("Có lỗi khi thêm người dùng lên server");
                    client.signOut();
                    FirebaseAuth.getInstance().signOut();
                }

            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                loginView._Login_Error("Có lỗi khi thêm người dùng lên server: " + t.getMessage().toString());
                client.signOut();
                FirebaseAuth.getInstance().signOut();
            }
        });
    }

    public void _Insert_NguoiDung_To_Firebase(NguoiDung nguoiDung){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.child(nguoiDung.getMaND()+"").limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    HashMap<String, Object> result_nd = new HashMap<>();
                    result_nd.put("token", nguoiDung.getToken());
                    reference.child(nguoiDung.getMaND()+"").updateChildren(result_nd, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            loginView._Insert_NguoiDung_To_Firebase_Sucess(nguoiDung);
                        }
                    });
                }else {
                    reference.child(nguoiDung.getMaND()+"").setValue(nguoiDung).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            loginView._Insert_NguoiDung_To_Firebase_Sucess(nguoiDung);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            loginView._Login_Error("Thêm người dùng lên firebase error: " + e.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loginView._Login_Error("Thêm người dùng lên firebase error: " + error.getMessage());
            }
        });

    }



    public void _Get_NguoiDung_byEmail(String sEmail){
        ApiServices.apiServices.get_data_nguoidung_byemail(sEmail).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful() && response.body() != null){
                    loginView._Get_Data_NguoiDung_Success(response.body());
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable t) {
                loginView._Login_Error(t.getMessage());
            }
        });
    }








}
