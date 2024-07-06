package com.example.baohongtaisantdmu_admin.Fragment.PhanBoTaiSan;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Adapter.PhanBoTaiSan.AdminPhanBoAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.PhanBoTaiSan.AssetInRoomAdapter;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.Presenter.PhanBoFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.PhanBoFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentPhanBoBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhanBoFragment extends Fragment implements PhanBoFView {


    private int MaP;
    private String TenP;
    private FragmentPhanBoBinding binding;
    private List<TaiSan> taiSans;
    private AdminPhanBoAdapter phanBoAdapter;
    private PhanBoFPresenter phanBoFPresenter;
    private int ID_LTS = -1, ID_NTS = -1;
    private String sSearch_Text = "";

    public PhanBoFragment(int MaP, String TenP) {
        this.MaP = MaP;
        this.TenP = TenP;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taiSans = new ArrayList<>();
        phanBoFPresenter = new PhanBoFPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPhanBoBinding.inflate(inflater, container, false);
        if (getContext() != null)
        {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManager);
            phanBoFPresenter._Get_Data_TaiSan();

            binding.sv.clearFocus();
            binding.sv.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if (phanBoAdapter != null)
                    {
                        phanBoFPresenter._Search_Data_PhanBo(taiSans, s, ID_LTS, ID_NTS);
                    }
                    return false;
                }
            });

            binding.btnTimLTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _Show_BottomSheet_LoaiTaiSan(taiSans);
                }
            });

            binding.btnTimNTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _Show_BottomSheet_NhomTaiSan(taiSans);
                }
            });

        }
        return binding.getRoot();
    }

    @Override
    public void _Get_List_Data_TaiSan_Success(List<TaiSan> taiSanList) {
        if (getContext() != null && taiSanList != null)
        {
            taiSans = taiSanList;
            phanBoAdapter = new AdminPhanBoAdapter(taiSanList, new RCVClickItem() {
                @Override
                public void onClickRCV(Object object, String CURD) {
                    TaiSan taiSan = (TaiSan) object;
                    if (CURD.equals("ADD")) {
                        Open_Dialog_Add(taiSan);
                    }
                }
            });
            binding.rcv.setAdapter(phanBoAdapter);
        }
    }

    @Override
    public void _Search_Data_PhanBo_Success(List<TaiSan> taiSanList) {
        if (phanBoAdapter != null && getContext() != null)
        {
            phanBoAdapter.searchDataList(taiSanList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        phanBoFPresenter._Get_Data_TaiSan();
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
    }

    private void Open_Dialog_Add(TaiSan taiSan) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_add_phanbo);

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

        EditText txtSoLuong = dialog.findViewById(R.id.txtSoLuongThemPhanBo);
        EditText txtGhiChu = dialog.findViewById(R.id.txtGhiChuPhanBo);
        TextView tvShowInfo = dialog.findViewById(R.id.tvShowInfoAdd);
        Button btnHuyBo = dialog.findViewById(R.id.btnHuyBoPhanBo);
        Button btnThem = dialog.findViewById(R.id.btnAddPhanBo);

        tvShowInfo.setText("Bạn đang thêm \n" + taiSan.getTenTS() + " \n vào \n" + TenP);

        btnThem.setOnClickListener(view -> {
            phanBoFPresenter._Add_TaiSan_To_Room(taiSan.getMaTS(), MaP, Integer.parseInt(txtSoLuong.getText().toString()), txtGhiChu.getText().toString());
            dialog.dismiss();
        });

        btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void _Show_BottomSheet_NhomTaiSan(List<TaiSan> taiSanList){
        View view = getLayoutInflater().inflate(R.layout.custom_bottomsheet_search_nhomtaisan, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);

        RadioGroup rg = (RadioGroup) view.findViewById(R.id.radiogroup);
        RadioButton button;


        button = new RadioButton(requireContext());
        button.setText("Tất cả");
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 0);

        button.setLayoutParams(params);
        button.setId(0);
        rg.addView(button);

        for(TaiSan taiSan: phanBoFPresenter._GetNhomTaiSan_In_AssetInRoom(taiSanList)){
            button = new RadioButton(requireContext());
            button.setText(taiSan.getTenNTS());
            params.setMargins(0, 10, 0, 0);
            button.setLayoutParams(params);
            button.setId(taiSan.getMaNTS());
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
                    phanBoFPresenter._Search_Data_PhanBo(taiSanList, sSearch_Text, ID_LTS, ID_NTS);
                    bottomSheetDialog.dismiss();
                }
            }
        });


    }


    public void _Show_BottomSheet_LoaiTaiSan(List<TaiSan> taiSanList){
        View view = getLayoutInflater().inflate(R.layout.custom_bottomsheet_search_nhomtaisan, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();
        bottomSheetDialog.setCancelable(true);

        RadioGroup rg = (RadioGroup) view.findViewById(R.id.radiogroup);
        RadioButton button;


        button = new RadioButton(requireContext());
        button.setText("Tất cả");
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 0);

        button.setLayoutParams(params);
        button.setId(0);
        rg.addView(button);

        for(TaiSan taiSan: phanBoFPresenter._GetLoaiTaiSan_In_AssetInRoom(taiSanList)){
            button = new RadioButton(requireContext());
            button.setText(taiSan.getTenLTS());
            params.setMargins(0, 10, 0, 0);
            button.setLayoutParams(params);
            button.setId(taiSan.getMaLTS());
            rg.addView(button);
        }


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = view.findViewById(i);
                if (checkedRadioButton != null)
                {
                    ID_LTS = checkedRadioButton.getId();
                    if (ID_LTS == 0)
                    {
                        binding.btnTimLTS.setText("Loại tài sản");
                        ID_LTS = -1;
                    }
                    if (ID_LTS != -1) binding.btnTimLTS.setText(checkedRadioButton.getText());
                    phanBoFPresenter._Search_Data_PhanBo(taiSanList, sSearch_Text, ID_LTS, ID_NTS);
                    bottomSheetDialog.dismiss();
                }
            }
        });


    }

}