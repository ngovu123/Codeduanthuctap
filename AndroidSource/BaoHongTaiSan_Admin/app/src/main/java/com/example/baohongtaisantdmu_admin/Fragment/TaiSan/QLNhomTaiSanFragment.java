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
import com.example.baohongtaisantdmu_admin.Adapter.TaiSan.AdminNhomTaiSanAdapter;
import com.example.baohongtaisantdmu_admin.Model.LoaiTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.TaiSan.QLLoaiTaiSanFPresenter;
import com.example.baohongtaisantdmu_admin.Presenter.TaiSan.QLNhomTaiSanFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.TaiSan.QLNhomTaiSanFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLLoaiTaiSanBinding;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLNhomTaiSanBinding;

import java.util.ArrayList;
import java.util.List;


public class QLNhomTaiSanFragment extends Fragment implements QLNhomTaiSanFView {

    private FragmentQLNhomTaiSanBinding binding;
    private List<NhomTaiSan> nhomTaiSans;
    private QLNhomTaiSanFPresenter qlNhomTaiSanFPresenter;
    private AdminNhomTaiSanAdapter nhomTaiSanAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nhomTaiSans = new ArrayList<>();
        qlNhomTaiSanFPresenter = new QLNhomTaiSanFPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLNhomTaiSanBinding.inflate(inflater, container, false);
        if (getContext() != null)
        {
            LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManagerND);
            qlNhomTaiSanFPresenter._Get_Data_NhomTaiSan();
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
                    qlNhomTaiSanFPresenter._SearchData_NhomTaiSan(nhomTaiSans, newText);
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
    public void _Get_List_Data_NhomTaiSan_Success(List<NhomTaiSan> nhomTaiSanList) {
        if (nhomTaiSanList != null){
            nhomTaiSans = nhomTaiSanList;
            nhomTaiSanAdapter = new AdminNhomTaiSanAdapter(nhomTaiSanList, new RCVClickItem() {
                @Override
                public void onClickRCV(Object object, String CURD) {
                    NhomTaiSan nhomTaiSan = (NhomTaiSan) object;
                    if (CURD.equals("EDIT")) {
                        Open_Dialog_Edit(nhomTaiSan);
                    } else if (CURD.equals("DELETE")) {
                        Open_Dialog_Delete(nhomTaiSan);
                    }
                }
            });
            binding.rcv.setAdapter(nhomTaiSanAdapter);
        }
    }

    @Override
    public void _Search_Data_NhomTaiSan_Success(List<NhomTaiSan> nhomTaiSanList) {
        if (nhomTaiSanAdapter != null){
            nhomTaiSanAdapter.searchDataList(nhomTaiSanList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
            qlNhomTaiSanFPresenter._Get_Data_NhomTaiSan();
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

        tv.setText("Thêm mới nhóm tài sản");
        txtinput.setHint("Nhập tên nhóm tài sản");
        btnthemmoi.setText("Thêm mới");

        btnthemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlNhomTaiSanFPresenter._Add_New_Data_NhomTaiSan(txtinput.getText().toString());
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


    private void Open_Dialog_Edit(NhomTaiSan nhomTaiSan) {
        Dialog dialog = new Dialog(requireContext());
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

        txtinput.setText(nhomTaiSan.getTenNTS());
        txtinput.setHint("Nhập tên nhóm tài sản");
        btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlNhomTaiSanFPresenter._Edit_Data_NhomTaiSan(nhomTaiSan.getMaNTS(), txtinput.getText().toString());
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


    private void Open_Dialog_Delete(NhomTaiSan nhomTaiSan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Bạn có chắc chắn muốn xóa không ?");
        builder.setMessage("Dữ liệu đã xóa không thể khôi phục ! ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                qlNhomTaiSanFPresenter._Delete_Data_NhomTaiSan(nhomTaiSan.getMaNTS());
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