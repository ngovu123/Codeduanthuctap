package com.example.baohongtaisantdmu_admin.Fragment.PhanBoTaiSan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Adapter.PhanBoTaiSan.AssetInRoomAdapter;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Presenter.AssetInRoomFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.AssetInRoomFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentAssetInRoomBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AssetInRoomFragment extends Fragment implements AssetInRoomFView {


    private FragmentAssetInRoomBinding binding;
    private List<PhanBo> phanBos;
    private AssetInRoomFPresenter assetInRoomFPresenter;
    private AssetInRoomAdapter assetInRoomAdapter;

    private int MaP;
    private String TenP;
    private int ID_LTS = -1, ID_NTS = -1;
    private String sSearch_Text = "";

    public AssetInRoomFragment(int MaP, String TenP) {
        this.MaP = MaP;
        this.TenP = TenP;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phanBos = new ArrayList<>();
        assetInRoomFPresenter = new AssetInRoomFPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAssetInRoomBinding.inflate(inflater, container, false);
        if (getContext() != null)
        {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManager);
            assetInRoomFPresenter.Get_List_Data_AssetInRoom(MaP);

            binding.sv.clearFocus();
            binding.sv.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    if (assetInRoomAdapter != null)
                    {
                        assetInRoomFPresenter._SearchData_AssetInRoom(phanBos, s, ID_LTS, ID_NTS);
                    }
                    return false;
                }
            });

            binding.btnTimNTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _Show_BottomSheet_NhomTaiSan(phanBos);
                }
            });

            binding.btnTimLTS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _Show_BottomSheet_LoaiTaiSan(phanBos);
                }
            });

        }
        return binding.getRoot();
    }

    @Override
    public void _Get_List_Data_AssetInRoom_Success(List<PhanBo> phanBoList) {
        if (getContext() != null && phanBoList != null)
        {
            phanBos = phanBoList;
            assetInRoomAdapter = new AssetInRoomAdapter(phanBoList);
            binding.rcv.setAdapter(assetInRoomAdapter);
        }
    }

    @Override
    public void _Search_Data_AssetInRoom_Success(List<PhanBo> phanBoList) {
        if (assetInRoomAdapter != null && getContext() != null)
        {
            assetInRoomAdapter.searchDataList(phanBoList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
            assetInRoomFPresenter.Get_List_Data_AssetInRoom(MaP);
        }
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        assetInRoomFPresenter.Get_List_Data_AssetInRoom(MaP);
    }

    public void _Show_BottomSheet_NhomTaiSan(List<PhanBo> phanBoList){
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

        for(PhanBo phanBo: assetInRoomFPresenter._GetNhomTaiSan_In_AssetInRoom(phanBoList)){
            button = new RadioButton(requireContext());
            button.setText(phanBo.getTenNTS());
            params.setMargins(0, 10, 0, 0);
            button.setLayoutParams(params);
            button.setId(phanBo.getMaNTS());
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
                    assetInRoomFPresenter._SearchData_AssetInRoom(phanBoList, sSearch_Text, ID_LTS, ID_NTS);
                    bottomSheetDialog.dismiss();
                }
            }
        });


    }


    public void _Show_BottomSheet_LoaiTaiSan(List<PhanBo> phanBoList){
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

        for(PhanBo phanBo: assetInRoomFPresenter._GetLoaiTaiSan_In_AssetInRoom(phanBoList)){
            button = new RadioButton(requireContext());
            button.setText(phanBo.getTenLTS());
            params.setMargins(0, 10, 0, 0);
            button.setLayoutParams(params);
            button.setId(phanBo.getMaLTS());
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
                    assetInRoomFPresenter._SearchData_AssetInRoom(phanBoList, sSearch_Text, ID_LTS, ID_NTS);
                    bottomSheetDialog.dismiss();
                }
            }
        });


    }



}