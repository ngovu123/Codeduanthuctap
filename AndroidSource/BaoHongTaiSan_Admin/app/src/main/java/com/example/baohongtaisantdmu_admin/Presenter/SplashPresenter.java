package com.example.baohongtaisantdmu_admin.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baohongtaisantdmu_admin.Api.ApiServices;
import com.example.baohongtaisantdmu_admin.Model.Logged;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.ObjectResponse;
import com.example.baohongtaisantdmu_admin.View.SplashView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter {
    private SplashView splashView;
    private Context context;

    public SplashPresenter(SplashView splashView, Context context) {
        this.splashView = splashView;
        this.context = context;
    }


    public void _CheckConnectNetwork(){
        if (context == null){
            splashView.ConnectedNetworkError("Error: Context Null");
            return;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null){
            splashView.ConnectedNetworkError("Error: ConnectivityManager Null");
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                Network network = connectivityManager.getActiveNetwork();
                if (network == null){
                    splashView.ConnectedNetworkError("Error: Network Null (API > 29)");
                }
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
                if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))){
                    splashView.ConnectedNetworkSuccess();
                }else {
                    splashView.ConnectedNetworkError("Error: No Internet");
                }
            }else {
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()){
                    splashView.ConnectedNetworkSuccess();
                }else {
                    splashView.ConnectedNetworkError("Error: No Internet");
                }
            }
        }
    }


    public void _CheckLogin(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            splashView.notLogin();
        }else {
            splashView.isLogin();
        }
    }


    public void _Update_Token_To_Server(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isComplete()) {
                    /////
                    ApiServices.apiServices.update_uid_token_nguoidung(Objects.requireNonNull(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail()).trim(), "uid", task.getResult()).enqueue(new Callback<ObjectResponse>() {
                        @Override
                        public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                            if (response.isSuccessful()){
                                splashView._Update_Token_To_Server_Success();
                            }else {
                                splashView._Splash_Error("_Update_Token_To_Server","Update token to server error");
                            }
                        }

                        @Override
                        public void onFailure(Call<ObjectResponse> call, Throwable t) {
                            splashView._Splash_Error("_Update_Token_To_Server", t.getMessage());
                        }
                    });
                    //////
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                splashView._Splash_Error("_Update_Token_To_Server", e.getMessage());
            }
        });

    }

    public void _Update_Token_To_FireBase(NguoiDung nguoiDung){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        HashMap<String, Object> result_nd = new HashMap<>();
        result_nd.put("token", nguoiDung.getToken());
        reference.child(nguoiDung.getMaND()+"").updateChildren(result_nd, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                splashView._Update_Token_To_FireBase_Success(nguoiDung);
            }
        });
    }

    public void _Get_NguoiDung_byEmail(){
        ApiServices.apiServices.get_data_nguoidung_byemail(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getEmail()).trim()).enqueue(new Callback<NguoiDung>() {
            @Override
            public void onResponse(Call<NguoiDung> call, Response<NguoiDung> response) {
                if (response.isSuccessful() && response.body() != null){
                    splashView._Get_NguoiDung_byEmail_Success(response.body());
                }else {
                    splashView._Splash_Error("_Get_NguoiDung_byEmail","Có lỗi khi lấy dữ liệu người dùng.");
                }
            }

            @Override
            public void onFailure(Call<NguoiDung> call, Throwable t) {
                splashView._Splash_Error("_Get_NguoiDung_byEmail",t.getMessage());
            }
        });
    }

}
