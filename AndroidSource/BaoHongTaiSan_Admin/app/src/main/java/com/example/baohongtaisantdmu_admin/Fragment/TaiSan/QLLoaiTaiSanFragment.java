package com.example.baohongtaisantdmu_admin.Fragment.TaiSan;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Adapter.TaiSan.AdminLoaiTaiSanAdapter;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.TaiSan.QLLoaiTaiSanFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.TaiSan.QLLoaiTaiSanFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLLoaiTaiSanBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QLLoaiTaiSanFragment extends Fragment implements QLLoaiTaiSanFView {

    private FragmentQLLoaiTaiSanBinding binding;
    private List<LoaiTaiSan> loaiTaiSans;
    private QLLoaiTaiSanFPresenter qlLoaiTaiSanFPresenter;
    private AdminLoaiTaiSanAdapter loaiTaiSanAdapter;
    public QLLoaiTaiSanFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qlLoaiTaiSanFPresenter = new QLLoaiTaiSanFPresenter(this);
        loaiTaiSans = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= FragmentQLLoaiTaiSanBinding.inflate(inflater, container, false);
        if (getContext() != null)
        {
            LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManagerND);
            qlLoaiTaiSanFPresenter._Get_Data_LoaiTaiSan();
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
                    qlLoaiTaiSanFPresenter._SearchData_LoaiPhong(loaiTaiSans, newText);
                    return false;
                }
            });

            binding.btnADD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Open_Dialog_Add();
                }
            });

        }
        return binding.getRoot();
    }

    @Override
    public void _Get_List_Data_LoaiTaiSan_Success(List<LoaiTaiSan> loaiTaiSanList) {
        if (loaiTaiSanList != null){
            loaiTaiSans = loaiTaiSanList;
            loaiTaiSanAdapter = new AdminLoaiTaiSanAdapter(loaiTaiSanList, new RCVClickItem() {
                @Override
                public void onClickRCV(Object object, String CURD) {
                    LoaiTaiSan loaiTaiSan = (LoaiTaiSan) object;
                    if (CURD.equals("EDIT")) {
                        Open_Dialog_Edit(loaiTaiSan);
                    } else if (CURD.equals("DELETE")) {
                        Open_Dialog_Delete(loaiTaiSan);
                    }
                }
            });
            binding.rcv.setAdapter(loaiTaiSanAdapter);
        }
    }

    @Override
    public void _Search_Data_LoaiTaiSan_Success(List<LoaiTaiSan> loaiTaiSanList) {
        if (loaiTaiSanAdapter != null){
            loaiTaiSanAdapter.searchDataList(loaiTaiSanList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
            qlLoaiTaiSanFPresenter._Get_Data_LoaiTaiSan();
        }
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        }
    }


    private void Open_Dialog_Add() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_edit);

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

        TextView tv = dialog.findViewById(R.id.tvTenChucNangEdit);
        EditText txtinput = dialog.findViewById(R.id.txtInput);
        Button btnhuybo = dialog.findViewById(R.id.btnHuyBo);
        Button btnthemmoi = dialog.findViewById(R.id.btnEdit);

        tv.setText("Thêm mới loại tài sản");
        txtinput.setHint("Nhập tên loại tài sản");
        btnthemmoi.setText("Thêm mới");

        btnthemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlLoaiTaiSanFPresenter._Add_New_Data_LoaiTaiSan(txtinput.getText().toString());
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


    private void Open_Dialog_Edit(LoaiTaiSan lts) {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_edit);

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

        EditText txtinput = dialog.findViewById(R.id.txtInput);
        Button btnhuybo = dialog.findViewById(R.id.btnHuyBo);
        Button btnchinhsua = dialog.findViewById(R.id.btnEdit);

        txtinput.setText(lts.getTenLTS());
        txtinput.setHint("Nhập tên loại tài sản");
        btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlLoaiTaiSanFPresenter._Edit_Data_LoaiTaiSan(lts.getMaLTS(), txtinput.getText().toString());
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


    private void Open_Dialog_Delete(LoaiTaiSan loaiTaiSan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Bạn có chắc chắn muốn xóa không ?");
        builder.setMessage("Dữ liệu đã xóa không thể khôi phục ! ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                qlLoaiTaiSanFPresenter._Delete_Data_LoaiTaiSan(loaiTaiSan.getMaLTS());
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