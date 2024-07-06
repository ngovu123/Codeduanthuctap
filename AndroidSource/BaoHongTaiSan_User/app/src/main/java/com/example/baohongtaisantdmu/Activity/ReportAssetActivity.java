package com.example.baohongtaisantdmu.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Model.NotificationData;
import com.example.baohongtaisantdmu.Model.NotificationDetailsData;
import com.example.baohongtaisantdmu.Presenter.ReportAssetPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.ReportAssetView;
import com.example.baohongtaisantdmu.databinding.ActivityReportAssetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

public class ReportAssetActivity extends AppCompatActivity implements ReportAssetView {

    private ActivityReportAssetBinding binding;
    private int TinhTrang = -1;
    private String ThongBao = "";
    private Uri mImageUri;
    private ReportAssetPresenter reportAssetPresenter;
    private AssetInRoom assetInRoom;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportAssetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Gson gson = new Gson();
        assetInRoom = gson.fromJson(getIntent().getStringExtra("AssetInRoomInfo"), AssetInRoom.class);
        reportAssetPresenter = new ReportAssetPresenter(this, ReportAssetActivity.this);


        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);


        binding.Title.setText("Báo hỏng tài sản \n " + assetInRoom.getTenTS());

        binding.BHTvTenTS.setText("Tên tài sản: " + assetInRoom.getTenTS());
        binding.BHTvTenLTS.setText("Tên loại tài sản: " + assetInRoom.getTenLTS());
        binding.BHTvTenPhong.setText("Tên phòng: " + assetInRoom.getTenP());
        binding.BHTvTenNTS.setText("Tên nhóm tài sản: " + assetInRoom.getTenNTS());


        binding.BaoHongBtnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.BaoHongTxtTinhTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Show_Bottom_Sheet_TinhTrang();
            }
        });

        binding.BaoHongBtnChonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _OpenFileChooserIMG();
            }
        });

        binding.BaoHongBtnGuiBaoHong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.BaoHongTxtMoTaHuHong.getText().toString())) {
                    Toast.makeText(ReportAssetActivity.this, "Bạn chưa nhập mô tả hư hỏng của thiết bị tài sản!!!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (mImageUri == null){
                    Toast.makeText(ReportAssetActivity.this, "Bạn chưa chọn hình ảnh mô tả hư hỏng của thiết bị tài sản!!!", Toast.LENGTH_SHORT).show();
                    return;
                }else if (TinhTrang == -1) {
                    Toast.makeText(ReportAssetActivity.this, "Bạn chưa chọn tình trạng hư hỏng của thiết bị tài sản!!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                binding.BaoHongBtnGuiBaoHong.setEnabled(false);
                binding.BaoHongBtnGuiBaoHong.setText("Đang gửi... bạn vui lòng chờ trong giây lát!!!");
                binding.BaoHongBtnGuiBaoHong.setBackgroundColor(Color.parseColor("#AAB7B8"));
                reportAssetPresenter._Upload_IMG_To_Firebase(mImageUri, binding.progressBar);
            }
        });



    }



    public void _Show_Bottom_Sheet_TinhTrang(){
        View view = getLayoutInflater().inflate(R.layout.custom_bottomsheet_tinhtrang, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);

        RadioGroup rg = (RadioGroup) view.findViewById(R.id.rdBaoHong);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rdSTT1) {
                    binding.BaoHongTxtTinhTrang.setText("Hư hỏng nhẹ (Minor)");
                    TinhTrang = 1;
                    bottomSheetDialog.dismiss();
                }else if (i == R.id.rdSTT2){
                    binding.BaoHongTxtTinhTrang.setText("Hư hỏng trung bình (Moderate)");
                    TinhTrang = 2;
                    bottomSheetDialog.dismiss();
                }else if (i == R.id.rdSTT3){
                    binding.BaoHongTxtTinhTrang.setText("Hư hỏng nghiêm trọng (Severe)");
                    TinhTrang = 3;
                    bottomSheetDialog.dismiss();
                }else if (i == R.id.rdSTT4){
                    binding.BaoHongTxtTinhTrang.setText("Hư hỏng hoàn toàn (Critical)");
                    TinhTrang = 4;
                    bottomSheetDialog.dismiss();
                }


            }
        });
    }



    public void _OpenFileChooserIMG() {
        Intent intent1 = new Intent();
        intent1.setType("image/*");
        intent1.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent1, 1);
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            binding.BaoHongImgView.setVisibility(View.VISIBLE);
            Glide.with(this).load(mImageUri).into(binding.BaoHongImgView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public void _Upload_Img_To_FireBase_Success(String URL_IMG) {
        reportAssetPresenter._Upload_ReportAsset_To_API(assetInRoom.getMaPB(), TinhTrang, binding.BaoHongTxtMoTaHuHong.getText().toString(), URL_IMG);
    }

    @Override
    public void _Upload_Img_To_FireBase_Error(String Message) {
        Toast.makeText(this, "Upload hình ảnh thất bại !!!. Error: " + Message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void _Upload_ReportAsset_To_API_Success(String Message) {
        ThongBao = Message;
        reportAssetPresenter._Get_Data_NguoiDung_Admin();
    }

    @Override
    public void _Upload_ReportAsset_To_API_Error(String Message) {
        Toast.makeText(this, "Gửi báo hỏng cho thiết bị này thất bại !!!. Error: " + Message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void _Get_Data_NguoiDungAdmin_Success(List<NguoiDung> nguoiDungList) {
        if (nguoiDungList != null)
        {
            NotificationDetailsData notificationDetailsData = new NotificationDetailsData(assetInRoom.getMaND(), assetInRoom.getTenTS(), assetInRoom.getTenP(), 1, TinhTrang, binding.BaoHongTxtMoTaHuHong.getText().toString(), "UserToAdmin");
            System.out.println(notificationDetailsData);
            for (int i = 0; i < nguoiDungList.size(); i++)
            {
                reportAssetPresenter._Push_Notification(new NotificationData(notificationDetailsData, nguoiDungList.get(i).getToken()));
            }
        }
    }

    @Override
    public void _Get_Data_NguoiDungAdmin_Error(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void _Push_Notification_Success() {
        Toast.makeText(this, ThongBao, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void _Push_Notification_Error(String Message) {
        System.out.println(Message);
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
        finish();
    }
}