package com.example.baohongtaisantdmu.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.baohongtaisantdmu.Adapter.AdapterAssetInRoom;
import com.example.baohongtaisantdmu.Model.AdapterClick;
import com.example.baohongtaisantdmu.Model.AssetInRoom;
import com.example.baohongtaisantdmu.Model.NguoiDung;
import com.example.baohongtaisantdmu.Presenter.AssetInRoomPresenter;
import com.example.baohongtaisantdmu.R;
import com.example.baohongtaisantdmu.View.AssetInRoomView;
import com.example.baohongtaisantdmu.databinding.ActivityAssetInRoomBinding;
import com.example.baohongtaisantdmu.databinding.CustomDialogChitietPhanboBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetInRoomActivity extends AppCompatActivity implements AssetInRoomView {


    private ActivityAssetInRoomBinding binding;
    private List<AssetInRoom> assetInRooms;
    private AssetInRoomPresenter assetInRoomPresenter;
    private int MaP = -1;
    private AdapterAssetInRoom adapterAssetInRoom;

    private int ID_TrangThai = -1, ID_NTS = -1;
    private String sSearch_Text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssetInRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        MaP = intent.getIntExtra("MaP", -1);

        assetInRooms = new ArrayList<>();
        List<NguoiDung> nguoiDungList = new ArrayList<>();
        assetInRoomPresenter = new AssetInRoomPresenter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AssetInRoomActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.rcv.setLayoutManager(linearLayoutManager);


        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        binding.sv.clearFocus();
        binding.sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sSearch_Text = newText;
                assetInRoomPresenter._SearchData_AssetInRoom(assetInRooms, newText, ID_TrangThai, ID_NTS);
                return false;
            }
        });

        binding.btnTimKiemTrangThai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Show_BottomSheet_TrangThai(assetInRooms);
            }
        });

        binding.btnTimNTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Show_BottomSheet_NhomTaiSan(assetInRooms);
            }
        });

        assetInRoomPresenter._GetData_AssetInRoom(MaP);


    }

    @Override
    public void _GetData_AssetInRoom_Success(List<AssetInRoom> assetInRoomList) {
        assetInRooms = assetInRoomList;
        adapterAssetInRoom = new AdapterAssetInRoom(assetInRoomList, AssetInRoomActivity.this,new AdapterClick() {
            @Override
            public void onClickRCV(Object object, String CURD) {
                AssetInRoom assetInRoom = (AssetInRoom) object;
                if (CURD.equals("BTN_XEMCHITIET")) {
                    Show_Dialog_ChiTiet_PhanBo(assetInRoom);
                }
            }
        });
        binding.rcv.setAdapter(adapterAssetInRoom);
        binding.titleToolbar.setText("Danh sách tài sản: " + assetInRoomList.get(0).getTenP());
        assetInRoomPresenter._GetData_User_In_Room(MaP);
    }

    @Override
    public void _GetData_AssetInRoom_Error(String ErrorMessage) {
        Toast.makeText(this, ErrorMessage, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void _SearchData_AssetInRoom_Success(List<AssetInRoom> assetInRoomList) {
        adapterAssetInRoom.searchDataList(assetInRoomList);
    }

    @Override
    public void _User_In_Room_DataChange(List<NguoiDung> nguoiDungList) {
        StringBuilder str = new StringBuilder("Những người đang tra cứu và báo hỏng cùng bạn: ");
        binding.tvInfoXem.setText(null);
        if (nguoiDungList.size() <= 3)
        {
            for (int i = 0; i < nguoiDungList.size(); i++)
            {
                str.append(nguoiDungList.get(i).getHoVaTen()).append(", ");
            }

            if (str.length() > 0) {
                str.deleteCharAt(str.length() - 1);
                str.deleteCharAt(str.length() - 1);
            }
            binding.tvInfoXem.setText(str.append("."));
        }



    }



    @Override
    protected void onResume() {
        super.onResume();
        assetInRoomPresenter._GetData_AssetInRoom(MaP);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        assetInRoomPresenter._DeleteData_Phong_In_FireBase(MaP);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        assetInRoomPresenter._DeleteData_Phong_In_FireBase(MaP);
    }

    public void _Show_BottomSheet_NhomTaiSan(List<AssetInRoom> assetInRoomList){
        View view = getLayoutInflater().inflate(R.layout.custom_bottomsheet_search_nhomtaisan, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);

        RadioGroup rg = (RadioGroup) view.findViewById(R.id.radiogroup);
        RadioButton button;


        button = new RadioButton(this);
        button.setText("Tất cả");
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 0);

        button.setLayoutParams(params);
        button.setId(0);
        rg.addView(button);

        for(AssetInRoom assetInRoom: assetInRoomPresenter._GetNhomTai_In_AssetInRoom(assetInRoomList)){
            button = new RadioButton(this);
            button.setText(assetInRoom.getTenNTS());
            params.setMargins(0, 10, 0, 0);
            button.setLayoutParams(params);
            button.setId(assetInRoom.getMaNTS());
            rg.addView(button);
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = view.findViewById(i);
                if (checkedRadioButton != null)
                {
                    ID_NTS = checkedRadioButton.getId();
                    if (ID_NTS == 0)
                    {
                        binding.btnTimNTS.setText("Nhóm tài sản");
                        ID_NTS = -1;
                    }
                    if (ID_NTS != -1) binding.btnTimNTS.setText(checkedRadioButton.getText());
                    assetInRoomPresenter._SearchData_AssetInRoom(assetInRoomList, sSearch_Text, ID_TrangThai, ID_NTS);
                    bottomSheetDialog.dismiss();
                }
            }
        });


    }

    public void _Show_BottomSheet_TrangThai(List<AssetInRoom> assetInRoomList){
        View view = getLayoutInflater().inflate(R.layout.custom_bottomsheet_search_trangthai, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);

        RadioGroup rg = (RadioGroup) view.findViewById(R.id.radiogroup_trangthai);



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rdSTT1){
                    binding.btnTimKiemTrangThai.setText("Đã gửi báo hỏng");
                    ID_TrangThai = 1;
                }else if (i == R.id.rdSTT2){
                    binding.btnTimKiemTrangThai.setText("Đã tiếp nhận");
                    ID_TrangThai = 2;
                }else if (i == R.id.rdSTT3){
                    binding.btnTimKiemTrangThai.setText("Đang sửa chữa");
                    ID_TrangThai = 3;
                }else if (i == R.id.rdSTT4){
                    binding.btnTimKiemTrangThai.setText("Sửa không thành công");
                    ID_TrangThai = 5;
                }else if (i == R.id.rdSTT5){
                    binding.btnTimKiemTrangThai.setText("Hoạt động tốt");
                    ID_TrangThai = 4;
                }else if (i == R.id.rdSTTALL)
                {
                    binding.btnTimKiemTrangThai.setText("Trạng thái");
                    ID_TrangThai = -1;
                }
                assetInRoomPresenter._SearchData_AssetInRoom(assetInRoomList, sSearch_Text, ID_TrangThai, ID_NTS);
                bottomSheetDialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void Show_Dialog_ChiTiet_PhanBo(AssetInRoom assetInRoom) {

        com.example.baohongtaisantdmu.databinding.CustomDialogChitietPhanboBinding customDialogChitietPhanboBinding = CustomDialogChitietPhanboBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(AssetInRoomActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(customDialogChitietPhanboBinding.getRoot());
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtt = window.getAttributes();
        windowAtt.gravity = Gravity.CENTER;
        window.setAttributes(windowAtt);
        dialog.setCancelable(true);

        customDialogChitietPhanboBinding.tvTenP.setText(assetInRoom.getTenP());
        customDialogChitietPhanboBinding.tvTenTS.setText(assetInRoom.getTenTS());
        customDialogChitietPhanboBinding.tvTenNTS.setText(assetInRoom.getTenNTS());
        customDialogChitietPhanboBinding.tvTenLTS.setText(assetInRoom.getTenLTS());
        customDialogChitietPhanboBinding.tvSoLuong.setText(assetInRoom.getSoLuong()+"");
        customDialogChitietPhanboBinding.tvHangSanXuat.setText(assetInRoom.getHangSanXuat());
        customDialogChitietPhanboBinding.tvNuocSanXuat.setText(assetInRoom.getNuocSanXuat());
        customDialogChitietPhanboBinding.tvNamSanXuat.setText(assetInRoom.getNamSanXuat()+"");




        customDialogChitietPhanboBinding.btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}