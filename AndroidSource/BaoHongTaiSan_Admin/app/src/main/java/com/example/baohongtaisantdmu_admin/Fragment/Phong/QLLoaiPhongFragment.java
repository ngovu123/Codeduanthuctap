package com.example.baohongtaisantdmu_admin.Fragment.Phong;

import android.annotation.SuppressLint;
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
import com.example.baohongtaisantdmu_admin.Adapter.Phong.AdminLoaiPhongAdapter;
import com.example.baohongtaisantdmu_admin.Model.ChucDanh;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.NguoiDung.QLChucDanhFPresenter;
import com.example.baohongtaisantdmu_admin.Presenter.Phong.QLLoaiPhongFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.Phong.QLLoaiPhongFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLChucDanhBinding;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLLoaiPhongBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QLLoaiPhongFragment extends Fragment implements QLLoaiPhongFView {


    private FragmentQLLoaiPhongBinding binding;
    private QLLoaiPhongFPresenter qlLoaiPhongFPresenter;
    private List<LoaiPhong> loaiPhongs;
    private AdminLoaiPhongAdapter loaiPhongAdapter;

    public QLLoaiPhongFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qlLoaiPhongFPresenter = new QLLoaiPhongFPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLLoaiPhongBinding.inflate(inflater, container, false);
        if (getContext() != null){
            LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManagerND);
            qlLoaiPhongFPresenter._Get_Data_LoaiPhong();

            binding.rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (dy > 0) binding.btnADD.hide();
                    else binding.btnADD.show();
                    super.onScrolled(recyclerView, dx, dy);
                }
            });

            binding.btnADD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Open_Dialog_Add();
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
                    if (loaiPhongAdapter != null && loaiPhongs != null)
                    {
                        qlLoaiPhongFPresenter._SearchData_LoaiPhong(loaiPhongs, newText);
                    }
                    return false;
                }
            });


        }
        return binding.getRoot();
    }

    @Override
    public void _Get_List_Data_LoaiPhong_Success(List<LoaiPhong> loaiPhongList) {
        loaiPhongs = loaiPhongList;
        loaiPhongAdapter = new AdminLoaiPhongAdapter(loaiPhongList, new RCVClickItem() {
            @Override
            public void onClickRCV(Object object, String CURD) {
                LoaiPhong loaiPhong = (LoaiPhong) object;
                if (CURD.equals("EDIT")) {
                    Open_Dialog_Edit(loaiPhong);
                } else if (CURD.equals("DELETE")) {
                    Open_Dialog_Delete(loaiPhong);
                }
            }
        });
        binding.rcv.setAdapter(loaiPhongAdapter);
    }

    @Override
    public void _Search_Data_LoaiPhong_Success(List<LoaiPhong> loaiPhongList) {
        if (loaiPhongAdapter != null)
        {
            loaiPhongAdapter.searchDataList(loaiPhongList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        qlLoaiPhongFPresenter._Get_Data_LoaiPhong();
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("SetTextI18n")
    public void Open_Dialog_Add() {
        Dialog dialog = new Dialog(getContext());
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

        tv.setText("Thêm mới loại phòng");
        btnthemmoi.setText("Thêm mới");

        btnthemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlLoaiPhongFPresenter._Add_New_Data_LoaiPhong(txtinput.getText().toString());
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

    public void Open_Dialog_Edit(LoaiPhong loaiPhong) {
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
        txtinput.setText(loaiPhong.getTenLP());
        btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlLoaiPhongFPresenter._Edit_Data_LoaiPhong(loaiPhong.getMaLP(), txtinput.getText().toString());
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

    public void Open_Dialog_Delete(LoaiPhong lp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Bạn có chắc chắn muốn xóa không ?");
        builder.setMessage("Dữ liệu đã xóa không thể khôi phục ! ");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                qlLoaiPhongFPresenter._Delete_Data_LoaiPhong(lp.getMaLP());
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