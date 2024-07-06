package com.example.baohongtaisantdmu_admin.Fragment.TaiSan;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Adapter.SpinnerAdapter.SpinnerLoaiTaiSanAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.SpinnerAdapter.SpinnerNhomTaiSanAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.TaiSan.AdminTaiSanAdapter;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.Model.PhanBo;
import com.example.baohongtaisantdmu_admin.Model.TaiSan;
import com.example.baohongtaisantdmu_admin.Presenter.TaiSan.QLTaiSanFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.TaiSan.QLTaiSanFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLTaiSanBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QLTaiSanFragment extends Fragment implements QLTaiSanFView {


    private FragmentQLTaiSanBinding binding;
    private List<LoaiTaiSan> loaiTaiSans;
    private List<NhomTaiSan> nhomTaiSans;
    private List<TaiSan> taiSans;
    private AdminTaiSanAdapter taiSanAdapter;
    private QLTaiSanFPresenter qlTaiSanFPresenter;
    private int MaNTS_Add = -1, MaLTS_Add = -1;

    private int ID_LTS = -1, ID_NTS = -1;
    private String sSearch_Text = "";

    public QLTaiSanFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taiSans = new ArrayList<>();
        loaiTaiSans = new ArrayList<>();
        nhomTaiSans = new ArrayList<>();
        qlTaiSanFPresenter = new QLTaiSanFPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLTaiSanBinding.inflate(inflater, container, false);
        LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
        binding.rcv.setLayoutManager(linearLayoutManagerND);
        qlTaiSanFPresenter._Get_Data_NhomTaiSan();

        binding.btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open_Dialog_Add();
            }
        });

        binding.rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) binding.btnADD.hide();
                else binding.btnADD.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.sv.clearFocus();
        binding.sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (taiSanAdapter != null && getContext() != null)
                {
                    qlTaiSanFPresenter._Search_Data_TaiSan(taiSans, newText, ID_LTS, ID_NTS);
                }
                return false;
            }
        });

        binding.btnTimNTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Show_BottomSheet_NhomTaiSan(taiSans);
            }
        });

        binding.btnTimLTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _Show_BottomSheet_LoaiTaiSan(taiSans);
            }
        });
        return binding.getRoot();
    }


    private void Load_Data_NhomTS(Spinner spinner, int MaNTS) {
        SpinnerNhomTaiSanAdapter spinnerNhomTaiSanAdapter = new SpinnerNhomTaiSanAdapter(getContext(), R.layout.custom_spinner_selected, nhomTaiSans);
        spinner.setAdapter(spinnerNhomTaiSanAdapter);
        if (MaNTS != -1) {
            for (int i = 0; i < nhomTaiSans.size(); i++) {
                if (nhomTaiSans.get(i).getMaNTS() == MaNTS) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    private void Load_Data_LoaiTS(Spinner spinner, int MaLTS) {
        SpinnerLoaiTaiSanAdapter spinner_2 = new SpinnerLoaiTaiSanAdapter(getContext(), R.layout.custom_spinner_selected, loaiTaiSans);
        spinner.setAdapter(spinner_2);
        if (MaLTS != -1) {
            for (int i = 0; i < loaiTaiSans.size(); i++) {
                if (loaiTaiSans.get(i).getMaLTS() == MaLTS) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }


    @Override
    public void _Get_List_Data_TaiSan_Success(List<TaiSan> taiSanList) {
        if (taiSanList != null && getContext() != null)
        {
            taiSans = taiSanList;
            taiSanAdapter = new AdminTaiSanAdapter(taiSanList);
            binding.rcv.setAdapter(taiSanAdapter);
        }
    }

    @Override
    public void _Get_List_Data_NhomTaiSan_Success(List<NhomTaiSan> nhomTaiSanList) {
        if (nhomTaiSanList != null)
        {
            nhomTaiSans = nhomTaiSanList;
            qlTaiSanFPresenter._Get_Data_LoaiTaiSan();
        }
    }

    @Override
    public void _Get_List_Data_LoaiTaiSan_Success(List<LoaiTaiSan> loaiTaiSanList) {
        if (loaiTaiSanList != null)
        {
            loaiTaiSans = loaiTaiSanList;
            qlTaiSanFPresenter._Get_Data_TaiSan();
        }
    }

    @Override
    public void _Search_Data_TaiSan_Success(List<TaiSan> taiSanList) {
        if (taiSanAdapter != null && getContext() != null)
        {
            taiSanAdapter.searchDataList(taiSanList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
            qlTaiSanFPresenter._Get_Data_NhomTaiSan();
        }
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        }
    }


    public void Open_Dialog_Add() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_taisan_add_edit);

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

        EditText txtinput = dialog.findViewById(R.id.txtAdd_Input);
        EditText txtgiatri = dialog.findViewById(R.id.txtAdd_GiaTri);
        EditText txtsoluong = dialog.findViewById(R.id.txtAdd_SoLuong);
        EditText txthangsx = dialog.findViewById(R.id.txtAdd_HangSX);
        EditText txtnuocsx = dialog.findViewById(R.id.txtAdd_NuocSX);
        EditText txtnamsx = dialog.findViewById(R.id.txtAdd_NamSX);
        EditText txtghichu = dialog.findViewById(R.id.txtAdd_GhiChu);


        Button btnhuybo = dialog.findViewById(R.id.btnAdd_HuyBo);
        Button btnthem = dialog.findViewById(R.id.btnAdd);
        Spinner spnNTS = dialog.findViewById(R.id.spnAdd_1);
        Spinner spnLTS = dialog.findViewById(R.id.spnAdd_2);

        Load_Data_NhomTS(spnNTS, -1);
        Load_Data_LoaiTS(spnLTS, -1);


        spnNTS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<NhomTaiSan> adapter = (ArrayAdapter<NhomTaiSan>) spnNTS.getAdapter();
                NhomTaiSan selected = adapter.getItem(i);
                if (selected != null) {
                    MaNTS_Add = selected.getMaNTS();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spnLTS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<LoaiTaiSan> adapter = (ArrayAdapter<LoaiTaiSan>) spnLTS.getAdapter();
                LoaiTaiSan selected = adapter.getItem(i);
                if (selected != null) {
                    MaLTS_Add = selected.getMaLTS();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MaNTS_Add == -1 || MaLTS_Add == -1 ) return;
                int giatriValue, soluongValue, namsxValue;

                try {
                    giatriValue = Integer.parseInt(txtgiatri.getText().toString());
                    soluongValue = Integer.parseInt(txtsoluong.getText().toString());
                    namsxValue = Integer.parseInt(txtnamsx.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Chưa nhập đủ thông tin cần thiết.", Toast.LENGTH_SHORT).show();
                    return;
                }
                qlTaiSanFPresenter._Add_New_Data_TaiSan(txtinput.getText().toString(), MaNTS_Add, MaLTS_Add, giatriValue, soluongValue, txthangsx.getText().toString(), txtnuocsx.getText().toString(), namsxValue, txtghichu.getText().toString());
                dialog.dismiss();
            }
        });


        btnhuybo.setOnClickListener(new View.OnClickListener() {
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

        for(TaiSan taiSan: qlTaiSanFPresenter._GetNhomTaiSan_In_AssetInRoom(taiSanList)){
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
                    qlTaiSanFPresenter._Search_Data_TaiSan(taiSanList, sSearch_Text, ID_LTS, ID_NTS);
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

        for(TaiSan taiSan: qlTaiSanFPresenter._GetLoaiTaiSan_In_AssetInRoom(taiSanList)){
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
                    qlTaiSanFPresenter._Search_Data_TaiSan(taiSanList, sSearch_Text, ID_LTS, ID_NTS);
                    bottomSheetDialog.dismiss();
                }
            }
        });


    }

}