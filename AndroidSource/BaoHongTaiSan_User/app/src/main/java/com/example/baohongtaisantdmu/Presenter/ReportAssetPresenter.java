package com.example.baohongtaisantdmu.Presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.example.baohongtaisantdmu.Api.ApiServices;
import com.example.baohongtaisantdmu.Model.Logged;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.NotificationData;
import com.example.baohongtaisantdmu.Model.ObjectResponse;
import com.example.baohongtaisantdmu.View.ReportAssetView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportAssetPresenter {

    private ReportAssetView reportAssetView;
    private Context context;


    public ReportAssetPresenter(ReportAssetView reportAssetView, Context context) {
        this.reportAssetView = reportAssetView;
        this.context = context;
    }



    public void _Upload_IMG_To_Firebase(Uri mImageUri, ProgressBar mProgressBar){
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("BaoHong");
        StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
        fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // chờ 500ms sau đó thực hiện đưa progessbar về 0
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(0);
                    }
                }, 500);

                // Lấy link hình ảnh từ firebase
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        reportAssetView._Upload_Img_To_FireBase_Success(uri.toString());
                    }
                });
                // end lấy

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                reportAssetView._Upload_Img_To_FireBase_Error(e.getMessage().toString());
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                // tương tác với Progessbar
                double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                mProgressBar.setProgress((int) progress);
            }
        });
    }


    public void _Upload_ReportAsset_To_API(int MaPB, int TinhTrang, String MoTa, String HinhAnh){
        ApiServices.apiServices.add_new_baoloi(MaPB, Logged.getInstance().getNguoiDung().getMaND(), TinhTrang, MoTa, HinhAnh).enqueue(new Callback<ObjectResponse>() {
            @Override
            public void onResponse(Call<ObjectResponse> call, Response<ObjectResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    ObjectResponse objectResponse = response.body();
                    if (objectResponse.getCode() == 1) {
                        reportAssetView._Upload_ReportAsset_To_API_Success(objectResponse.getMessage());
                    }else {
                        reportAssetView._Upload_ReportAsset_To_API_Error(objectResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse> call, Throwable t) {
                reportAssetView._Upload_ReportAsset_To_API_Error(t.getMessage());
            }
        });
    }

    public void _Push_Notification(NotificationData notificationData){
        ApiServices.firebaseServices._Send_Notification_To_Admin(notificationData).enqueue(new Callback<NotificationData>() {
            @Override
            public void onResponse(Call<NotificationData> call, Response<NotificationData> response) {
                if (response.isSuccessful()){
                    reportAssetView._Push_Notification_Success();
                }
            }

            @Override
            public void onFailure(Call<NotificationData> call, Throwable t) {
                reportAssetView._Push_Notification_Error(t.getMessage());
            }
        });
    }

    public void _Get_Data_NguoiDung_Admin(){
        ApiServices.apiServices.get_list_data_nguoidung_admin(Logged.getInstance().getNguoiDung().getMaND()).enqueue(new Callback<List<NguoiDung>>() {
            @Override
            public void onResponse(Call<List<NguoiDung>> call, Response<List<NguoiDung>> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    reportAssetView._Get_Data_NguoiDungAdmin_Success(response.body());
                }else {
                    reportAssetView._Get_Data_NguoiDungAdmin_Error("Có lỗi khi lấy dữ liệu người dùng");
                }
            }

            @Override
            public void onFailure(Call<List<NguoiDung>> call, Throwable t) {
                reportAssetView._Get_Data_NguoiDungAdmin_Error("Có lỗi khi lấy dữ liệu người dùng");
            }
        });
    }


    public String getFileExtension(Uri uri) {
        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


}
