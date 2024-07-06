package com.example.baohongtaisantdmu_admin.Fragment.Phong;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Adapter.Phong.AdminPhongAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.SpinnerAdapter.SpinnerKhuVucPhongAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.SpinnerAdapter.SpinnerLoaiPhong_Adapter;
import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.Phong;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.Phong.QLPhongFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.Phong.QLPhongFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLPhongBinding;

import java.util.ArrayList;
import java.util.List;


public class QLPhongFragment extends Fragment implements QLPhongFView {

    private FragmentQLPhongBinding binding;
    private QLPhongFPresenter qlPhongFPresenter;
    private AdminPhongAdapter phongAdapter;
    private List<KhuVucPhong> khuVucPhongs;
    private List<LoaiPhong> loaiPhongs;
    private List<Phong> phongs;
    private int MaLP_Add = -1, MaKVP_Add = -1;
    private int MaLP_Edit = -1, MaKVP_Edit = -1;
    public QLPhongFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qlPhongFPresenter = new QLPhongFPresenter(this);
        khuVucPhongs = new ArrayList<>();
        loaiPhongs = new ArrayList<>();
        phongs = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLPhongBinding.inflate(inflater, container, false);
        if (getContext() != null){
            LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManagerND);
            qlPhongFPresenter._Get_Data_KhuVucPhong();

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
                    qlPhongFPresenter._Search_Data_Phong(phongs, newText);
                    return false;
                }
            });
        }

        return binding.getRoot();
    }


    private void Load_Data_LoaiPhong(Spinner spinner, int MaLP) {
        SpinnerLoaiPhong_Adapter spinnerLoaiPhongAdapter = new SpinnerLoaiPhong_Adapter(getContext(), R.layout.custom_spinner_selected, loaiPhongs);
        spinner.setAdapter(spinnerLoaiPhongAdapter);
        if (MaLP != -1) {
            for (int i = 0; i < loaiPhongs.size(); i++) {
                if (loaiPhongs.get(i).getMaLP() == MaLP) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    private void Load_Data_KhuVucPhong(Spinner spinner, int MaKVP) {
        SpinnerKhuVucPhongAdapter spinner_2 = new SpinnerKhuVucPhongAdapter(getContext(), R.layout.custom_spinner_selected, khuVucPhongs);
        spinner.setAdapter(spinner_2);
        if (MaKVP != -1) {
            for (int i = 0; i < khuVucPhongs.size(); i++) {
                if (khuVucPhongs.get(i).getMaKVP() == MaKVP) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }


    @Override
    public void _Get_List_Data_Phong_Success(List<Phong> phongList) {
        if (phongList != null)
        {
            phongs = phongList;
            phongAdapter = new AdminPhongAdapter(phongList, new RCVClickItem() {
                @Override
                public void onClickRCV(Object object, String CURD) {
                    Phong phong = (Phong) object;
                    if (CURD.equals("EDIT")) {
                        Open_Dialog_Edit(phong);
                    } else if (CURD.equals("DELETE")) {
                        Open_Dialog_Delete(phong);
                    }
                }
            }, getContext());
            binding.rcv.setAdapter(phongAdapter);
        }
    }

    @Override
    public void _Get_List_Data_KhuVucPhong_Success(List<KhuVucPhong> khuVucPhongList) {
        if (khuVucPhongList != null) {
            khuVucPhongs = khuVucPhongList;
            qlPhongFPresenter._Get_Data_LoaiPhong();
        }
    }

    @Override
    public void _Get_List_Data_LoaiPhong_Success(List<LoaiPhong> loaiPhongList) {
        if (loaiPhongList != null) {
            loaiPhongs = loaiPhongList;
            qlPhongFPresenter._Get_Data_Phong();
        }
    }

    @Override
    public void _Search_Data_Phong_Success(List<Phong> phongList) {
        if (phongAdapter != null)
        {
            phongAdapter.searchDataList(phongList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        qlPhongFPresenter._Get_Data_KhuVucPhong();
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
    }

    public void Open_Dialog_Add() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_phong_add_edit);

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
        EditText txtinput = dialog.findViewById(R.id.txtAdd_Input);
        Button btnhuybo = dialog.findViewById(R.id.btnAdd_HuyBo);
        Button btnthem = dialog.findViewById(R.id.btnAdd);
        Spinner spnLP = dialog.findViewById(R.id.spnAdd_1);
        Spinner spnKVP = dialog.findViewById(R.id.spnAdd_2);
        btnthem.setText("Thêm mới");
        tv.setText("Thêm mới Phòng");
        Load_Data_LoaiPhong(spnLP, -1);
        Load_Data_KhuVucPhong(spnKVP, -1);
        txtinput.setHint("Nhập tên Phòng");


        spnLP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<LoaiPhong> adapter = (ArrayAdapter<LoaiPhong>) spnLP.getAdapter();
                LoaiPhong selected = adapter.getItem(i);
                if (selected != null) {
                    MaLP_Add = selected.getMaLP();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spnKVP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<KhuVucPhong> adapter = (ArrayAdapter<KhuVucPhong>) spnKVP.getAdapter();
                KhuVucPhong selected = adapter.getItem(i);
                if (selected != null) {
                    MaKVP_Add = selected.getMaKVP();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MaLP_Add == -1 || MaKVP_Add == -1){
                    Toast.makeText(getContext(), "Vui lòng chọn và nhập đầy đủ thông tin !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                qlPhongFPresenter._Add_Data_Phong(txtinput.getText().toString(), MaKVP_Add, MaLP_Add);
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

    public void Open_Dialog_Edit(Phong phong) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_phong_add_edit);

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
        EditText txtinput = dialog.findViewById(R.id.txtAdd_Input);
        Button btnhuybo = dialog.findViewById(R.id.btnAdd_HuyBo);
        Button btnthem = dialog.findViewById(R.id.btnAdd);
        Spinner spnLP = dialog.findViewById(R.id.spnAdd_1);
        Spinner spnKVP = dialog.findViewById(R.id.spnAdd_2);
        btnthem.setText("Chỉnh sửa");
        tv.setText("Chỉnh sửa thông tin Phòng");
        Load_Data_LoaiPhong(spnLP, phong.getMaLP());
        Load_Data_KhuVucPhong(spnKVP, phong.getMaKVP());
        txtinput.setText(phong.getTenP());


        spnLP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<LoaiPhong> adapter = (ArrayAdapter<LoaiPhong>) spnLP.getAdapter();
                LoaiPhong selected = adapter.getItem(i);
                if (selected != null) {
                    MaLP_Edit = selected.getMaLP();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spnKVP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<KhuVucPhong> adapter = (ArrayAdapter<KhuVucPhong>) spnKVP.getAdapter();
                KhuVucPhong selected = adapter.getItem(i);
                if (selected != null) {
                    MaKVP_Edit = selected.getMaKVP();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MaLP_Edit == -1 || MaKVP_Edit == -1) {
                    Toast.makeText(getContext(), "Vui lòng chọn và nhập đầy đủ thông tin !!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                qlPhongFPresenter._Edit_Data_Phong(phong.getMaP(), txtinput.getText().toString(), MaKVP_Edit, MaLP_Edit);
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

    public void Open_Dialog_Delete(Phong p) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Bạn có chắc chắn muốn xóa không ?");
        builder.setMessage("Dữ liệu đã xóa không thể khôi phục ! ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                qlPhongFPresenter._Delete_Data_Phong(p.getMaP());
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(requireContext(), "Đã hủy", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}