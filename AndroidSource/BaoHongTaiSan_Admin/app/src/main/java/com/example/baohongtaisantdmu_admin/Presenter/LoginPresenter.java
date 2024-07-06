package com.example.baohongtaisantdmu_admin.Presenter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }


    public void _Login_With_Server(String sEmail, String sPassword){
        ApiServices.apiServices.get_data_nguoidung_byemail(sEmail.trim()).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    if (response.body().getMatKhau().trim().equals(sPassword.trim())){
                        if (!response.body().getTenPQ().equals("Admin")){
                            loginView._Login_Error("DangXuat", "Bạn không phải là quản trị viên !!!");
                        }else {
                            loginView.Login_Server_Success(response.body());
                        }
                    }else {
                        loginView._Login_Error("Login_With_Server", "Email hoặc mật khẩu không chính xác.");
                    }
                }else {
                    loginView._Login_Error("Login_With_Server", "Email này không tồn tại trong hệ thống.");
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable t) {
                loginView._Login_Error("Login_With_Server", t.getMessage());
            }
        });
    }


    public void _Login_With_Firebase(String sEmail, String sPassword, NguoiDung nguoiDung){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(sEmail.trim(), sPassword.trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                loginView.Login_Firebase_Success(nguoiDung);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginView._Login_Error("_Login_With_Firebase", e.getMessage());
            }
        });
    }


    public void _Update_Token_To_Server(NguoiDung nguoiDung){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                nguoiDung.setToken(task.getResult());
                /////
                ApiServices.apiServices.update_uid_token_nguoidung(nguoiDung.getEmail().trim(), "uid", task.getResult()).enqueue(new Callback<ObjectResponse>() {
                    @Override
                    public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                        if (response.isSuccessful()){
                            System.out.println(response.body().getMessage());

                            loginView._Update_Token_To_Server_Success(nguoiDung);
                        }else {
                            loginView._Login_Error("DangXuat","Update token to server error");
                        }
                    }

                    @Override
                    public void onFailure(Call<ObjectResponse> call, Throwable t) {
                        loginView._Login_Error("DangXuat",t.getMessage());
                    }
                });
                //////
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginView._Login_Error("DangXuat", e.getMessage());
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
                            loginView._Update_Token_To_Firebase_Success(nguoiDung);
                        }
                    });
                }else {
                    reference.child(nguoiDung.getMaND()+"").setValue(nguoiDung).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            loginView._Update_Token_To_Firebase_Success(nguoiDung);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            loginView._Login_Error("DangXuat" ,"Thêm người dùng lên firebase error: " + e.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loginView._Login_Error("DangXuat" ,"Thêm người dùng lên firebase error: " + error.getMessage());
            }
        });

    }







}
