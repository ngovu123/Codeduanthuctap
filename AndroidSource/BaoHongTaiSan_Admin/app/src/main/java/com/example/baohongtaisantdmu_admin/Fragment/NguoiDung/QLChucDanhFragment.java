package com.example.baohongtaisantdmu_admin.Fragment.NguoiDung;

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

import com.example.baohongtaisantdmu_admin.Adapter.NguoiDung.AdminChucDanhAdapter;
import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.NguoiDung.QLChucDanhFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.NguoiDung.QLChucDanhFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLChucDanhBinding;

import java.util.ArrayList;
import java.util.List;


public class QLChucDanhFragment extends Fragment implements QLChucDanhFView {

    private FragmentQLChucDanhBinding binding;
    private QLChucDanhFPresenter qlChucDanhFPresenter;
    private List<ChucDanh> chucDanhs;
    private AdminChucDanhAdapter adminChucDanhAdapter;
    public QLChucDanhFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qlChucDanhFPresenter = new QLChucDanhFPresenter(this);
        chucDanhs = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLChucDanhBinding.inflate(inflater, container, false);
        if (getContext() != null){
            LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManagerND);
            qlChucDanhFPresenter._Get_Data_ChucDanh();

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
                    qlChucDanhFPresenter._SearchData_ChucDanh(chucDanhs, newText);
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
    public void _Get_List_Data_ChucDanh_Success(List<ChucDanh> chucDanhList) {
        chucDanhs = chucDanhList;
        adminChucDanhAdapter = new AdminChucDanhAdapter(chucDanhs, new RCVClickItem() {
            @Override
            public void onClickRCV(Object object, String CURD) {
                ChucDanh cd = (ChucDanh) object;
                if (CURD.equals("EDIT")) {
                    Open_Dialog_Edit(cd);
                } else if (CURD.equals("DELETE")) {
                    Open_Dialog_Delete(cd);
                }
            }
        });

        binding.rcv.setAdapter(adminChucDanhAdapter);
    }

    @Override
    public void _Search_Data_ChucDanh_Success(List<ChucDanh> chucDanhList) {
        if (adminChucDanhAdapter != null)
        {
            adminChucDanhAdapter.searchDataList(chucDanhList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        qlChucDanhFPresenter._Get_Data_ChucDanh();
    }


    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        Toast.makeText(getContext(), "Error: "+ Message, Toast.LENGTH_SHORT).show();
    }



    public void Open_Dialog_Add() {
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

        tv.setText("Thêm mới chức danh");
        txtinput.setHint("Nhập tên chức danh");
        btnthemmoi.setText("Thêm mới");

        btnthemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlChucDanhFPresenter._Add_New_Data(txtinput.getText().toString(), null);
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

    public void Open_Dialog_Edit(ChucDanh cd) {
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

        EditText txtinput = dialog.findViewById(R.id.txtInput);
        Button btnhuybo = dialog.findViewById(R.id.btnHuyBo);
        Button btnchinhsua = dialog.findViewById(R.id.btnEdit);

        txtinput.setText(cd.getTenCD());
        txtinput.setHint("Nhập tên chức danh");
        btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlChucDanhFPresenter._Edit_Data(cd.getMaCD(), txtinput.getText().toString(), null);
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

    public void Open_Dialog_Delete(ChucDanh cd) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Bạn có chắc chắn muốn xóa không ?");
        builder.setMessage("Dữ liệu đã xóa không thể khôi phục ! ");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                qlChucDanhFPresenter._Delete_Data(cd.getMaCD());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }


}