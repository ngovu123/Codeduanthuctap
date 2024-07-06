package com.example.baohongtaisantdmu_admin.Fragment.NguoiDung;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baohongtaisantdmu_admin.Adapter.NguoiDung.AdminChucDanhAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.NguoiDung.AdminDonViAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.NguoiDung.AdminNguoiDungAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.SpinnerAdapter.SpinnerChucDanhAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.SpinnerAdapter.SpinnerDonViAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.SpinnerAdapter.SpinnerPhanQuyenAdapter;
import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.DonVi;
import com.example.baohongtaisantdmu_admin.Model.NguoiDung;
import com.example.baohongtaisantdmu_admin.Model.PhanQuyen;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.NguoiDung.QLNguoiDungFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.NguoiDung.QLNguoiDungFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLNguoiDungBinding;

import java.util.ArrayList;
import java.util.List;


public class QLNguoiDungFragment extends Fragment implements QLNguoiDungFView {

    private QLNguoiDungFPresenter QLNguoiDungFPresenter;
    private FragmentQLNguoiDungBinding binding;
    private List<DonVi> donVis;
    private List<ChucDanh> chucDanhs;
    private List<NguoiDung> nguoiDungs;
    private List<PhanQuyen> phanQuyens;
    private AdminNguoiDungAdapter adminNguoiDungAdapter;
    private AdminChucDanhAdapter adminChucDanhAdapter;
    private AdminDonViAdapter adminDonViAdapter;
    private int MaDV = -1, MaCD = -1, MaPQ = -1;
    private int MaDV_ADD = -1, MaCD_ADD = -1, MaPQ_ADD = -1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nguoiDungs = new ArrayList<>();
        donVis = new ArrayList<>();
        chucDanhs = new ArrayList<>();
        phanQuyens = new ArrayList<>();
        QLNguoiDungFPresenter = new QLNguoiDungFPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLNguoiDungBinding.inflate(inflater, container, false);
        if (getContext() != null) {
            LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManagerND);
            QLNguoiDungFPresenter._Get_Data_NguoiDung();

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
                    QLNguoiDungFPresenter._Search_Data_NguoiDung(nguoiDungs, newText);
                    return false;
                }
            });

        }
        return binding.getRoot();
    }


    private void Load_Data_DonVi(Spinner spinner, List<DonVi> donViList, int MaDV) {
        SpinnerDonViAdapter spinnerDonViAdapter = new SpinnerDonViAdapter(requireContext(), R.layout.custom_spinner_selected, donViList);
        spinner.setAdapter(spinnerDonViAdapter);
        if (MaDV != -1) {
            for (int i = 0; i < donViList.size(); i++) {
                if (donViList.get(i).getMaDV() == MaDV) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    private void Load_Data_ChucDanh(Spinner spinner, List<ChucDanh> chucDanhList, int MaCD) {
        SpinnerChucDanhAdapter spinnerChucDanhAdapter = new SpinnerChucDanhAdapter(requireContext(), R.layout.custom_spinner_selected, chucDanhList);
        spinner.setAdapter(spinnerChucDanhAdapter);
        if (MaCD != -1) {
            for (int i = 0; i < chucDanhList.size(); i++) {
                if (chucDanhList.get(i).getMaCD() == MaCD) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    private void Load_Data_PhanQuyen(Spinner spinner, List<PhanQuyen> phanQuyenList, int MaPQ) {
        SpinnerPhanQuyenAdapter spinnerPhanQuyenAdapter = new SpinnerPhanQuyenAdapter(requireContext(), R.layout.custom_spinner_selected, phanQuyenList);
        spinner.setAdapter(spinnerPhanQuyenAdapter);
        if (MaPQ != -1) {
            for (int i = 0; i < phanQuyenList.size(); i++) {
                if (phanQuyenList.get(i).getMaPQ() == MaPQ) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }


    @Override
    public void _Get_List_Data_NguoiDung_Success(List<NguoiDung> nguoiDungList) {
        nguoiDungs = nguoiDungList;
        adminNguoiDungAdapter = new AdminNguoiDungAdapter(nguoiDungList, new RCVClickItem() {
            @Override
            public void onClickRCV(Object object, String CURD) {
                NguoiDung nguoiDung = (NguoiDung) object;
                if (CURD.equals("EDIT")) {
                    Open_Dialog_Edit(nguoiDung);
                }
            }
        });
        binding.rcv.setAdapter(adminNguoiDungAdapter);
        if (adminNguoiDungAdapter != null) QLNguoiDungFPresenter._Get_Data_ChucDanh();
    }

    @Override
    public void _Get_List_Data_ChucDanh_Success(List<ChucDanh> chucDanhList) {
        if (chucDanhList != null && adminNguoiDungAdapter != null) {
            chucDanhs = chucDanhList;
            QLNguoiDungFPresenter._Get_Data_DonVi();
        }
    }

    @Override
    public void _Get_List_Data_DonVi_Success(List<DonVi> donViList) {
        if (donViList != null && adminNguoiDungAdapter != null) {
            donVis = donViList;
            QLNguoiDungFPresenter._Get_Data_PhanQuyen();
        }
    }

    @Override
    public void _Get_List_Data_PhanQuyen_Success(List<PhanQuyen> phanQuyenList) {
        if (phanQuyenList != null && adminNguoiDungAdapter != null) {
            phanQuyens = phanQuyenList;
        }
    }

    @Override
    public void _Search_Data_NguoiDung_Success(List<NguoiDung> nguoiDungList) {
        if (adminNguoiDungAdapter != null)
        {
            adminNguoiDungAdapter.searchDataList(nguoiDungList);
        }
    }

    @Override
    public void _Add_Data_NguoiDung_In_Server_Success(String sEmail, String sMatKhau, Dialog dialog) {
        QLNguoiDungFPresenter._Add_Data_NguoiDung_In_FireBase(sEmail, sMatKhau, dialog);
    }

    @Override
    public void _Add_Data_NguoiDung_In_FireBase_Success(Dialog dialog) {
        Toast.makeText(getContext(), "Thêm mới người dùng thành công !!!", Toast.LENGTH_SHORT).show();
        QLNguoiDungFPresenter._Get_Data_NguoiDung();
        dialog.dismiss();

    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        QLNguoiDungFPresenter._Get_Data_NguoiDung();
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {

    }


    @SuppressLint("SetTextI18n")
    public void Open_Dialog_Edit(NguoiDung nd) {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_nguoidung_add_edit);

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

        TextView tv = dialog.findViewById(R.id.tvAdd_TenChucNangEdit);
        EditText txtinput = dialog.findViewById(R.id.txtHoVaTen_Add);
        EditText txtSDT = dialog.findViewById(R.id.txtSDT_Add);
        EditText txtEmail = dialog.findViewById(R.id.txtEmail_Add);
        EditText txtMatKhau = dialog.findViewById(R.id.txtMatKhau_Add);
        Button btnhuybo = dialog.findViewById(R.id.btnAdd_HuyBo);
        Button btnthem = dialog.findViewById(R.id.btnAdd);

        Spinner spnDV = dialog.findViewById(R.id.spnAdd_1);
        Spinner spnCD = dialog.findViewById(R.id.spnAdd_2);
        Spinner spnPQ = dialog.findViewById(R.id.spnAdd_3);

        btnthem.setText("Chỉnh sửa");
        tv.setText("Chỉnh sửa thông tin người dùng");

        Load_Data_DonVi(spnDV, donVis, nd.getMaDV());
        Load_Data_ChucDanh(spnCD, chucDanhs, nd.getMaCD());
        Load_Data_PhanQuyen(spnPQ, phanQuyens, nd.getMaPQ());
        txtinput.setText(nd.getHoVaTen());
        txtSDT.setText(nd.getSoDienThoai());
        txtEmail.setText(nd.getEmail());
        txtEmail.setEnabled(false);
        txtMatKhau.setText(nd.getMatKhau());

        spnDV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<DonVi> adapter = (ArrayAdapter<DonVi>) spnDV.getAdapter();
                DonVi selected = adapter.getItem(i);
                if (selected != null) {
                    MaDV = selected.getMaDV();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnCD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<ChucDanh> adapter = (ArrayAdapter<ChucDanh>) spnCD.getAdapter();
                ChucDanh selected = adapter.getItem(i);
                if (selected != null) {
                    MaCD = selected.getMaCD();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnPQ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<PhanQuyen> adapter = (ArrayAdapter<PhanQuyen>) spnPQ.getAdapter();
                PhanQuyen selected = adapter.getItem(i);
                if (selected != null) {
                    MaPQ = selected.getMaPQ();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MaCD == -1 ||
                        MaDV == -1 ||
                        MaPQ == -1 ||
                        TextUtils.isEmpty(txtinput.getText().toString()) ||
                        TextUtils.isEmpty(txtSDT.getText().toString()) ||
                        TextUtils.isEmpty(txtMatKhau.getText().toString())) {
                    Toast.makeText(requireContext(), "Dữ liệu sửa người dùng của bạn không đúng !!!", Toast.LENGTH_SHORT).show();
                } else {
                    QLNguoiDungFPresenter._Edit_Data_NguoiDung(nd.getMaND(), txtinput.getText().toString(), txtSDT.getText().toString(), txtMatKhau.getText().toString(), MaDV, MaCD, MaPQ);
                }
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


    @SuppressLint("SetTextI18n")
    private void Open_Dialog_Add() {
        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_nguoidung_add_edit);

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

        TextView tv = dialog.findViewById(R.id.tvAdd_TenChucNangEdit);
        EditText txtHoVaTen = dialog.findViewById(R.id.txtHoVaTen_Add);
        EditText txtSDT = dialog.findViewById(R.id.txtSDT_Add);
        EditText txtEmail = dialog.findViewById(R.id.txtEmail_Add);
        EditText txtMatKhau = dialog.findViewById(R.id.txtMatKhau_Add);
        Button btnhuybo = dialog.findViewById(R.id.btnAdd_HuyBo);
        Button btnthem = dialog.findViewById(R.id.btnAdd);

        Spinner spnDV = dialog.findViewById(R.id.spnAdd_1);
        Spinner spnCD = dialog.findViewById(R.id.spnAdd_2);
        Spinner spnPQ = dialog.findViewById(R.id.spnAdd_3);

        btnthem.setText("Thêm mới");
        tv.setText("Thêm mới thông tin người dùng");

        Load_Data_DonVi(spnDV, donVis,-1);
        Load_Data_ChucDanh(spnCD, chucDanhs, -1);
        Load_Data_PhanQuyen(spnPQ,phanQuyens, -1);


        spnDV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<DonVi> adapter = (ArrayAdapter<DonVi>) spnDV.getAdapter();
                DonVi selected = adapter.getItem(i);
                if (selected != null) {
                    MaDV_ADD = selected.getMaDV();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnCD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<ChucDanh> adapter = (ArrayAdapter<ChucDanh>) spnCD.getAdapter();
                ChucDanh selected = adapter.getItem(i);
                if (selected != null) {
                    MaCD_ADD = selected.getMaCD();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spnPQ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<PhanQuyen> adapter = (ArrayAdapter<PhanQuyen>) spnPQ.getAdapter();
                PhanQuyen selected = adapter.getItem(i);
                if (selected != null) {
                    MaPQ_ADD = selected.getMaPQ();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MaCD_ADD == -1 ||
                        MaDV_ADD == -1 ||
                        MaPQ_ADD == -1 ||
                        TextUtils.isEmpty(txtHoVaTen.getText().toString()) ||
                        TextUtils.isEmpty(txtSDT.getText().toString()) ||
                        TextUtils.isEmpty(txtEmail.getText().toString()) ||
                        TextUtils.isEmpty(txtMatKhau.getText().toString())) {
                    Toast.makeText(requireContext(), "Dữ liệu thêm mới người dùng của bạn không đúng !!!", Toast.LENGTH_SHORT).show();
                } else {
                    btnthem.setEnabled(false);
                    QLNguoiDungFPresenter._Add_Data_NguoiDung(txtHoVaTen.getText().toString(), txtSDT.getText().toString(), txtEmail.getText().toString(), txtMatKhau.getText().toString(), MaPQ_ADD, MaDV_ADD, MaCD_ADD, dialog);
                }
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

}