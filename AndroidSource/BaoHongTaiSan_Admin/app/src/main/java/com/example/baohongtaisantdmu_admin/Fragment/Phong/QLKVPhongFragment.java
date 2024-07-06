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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Adapter.Phong.AdminKhuVucPhongAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.Phong.AdminLoaiPhongAdapter;
import com.example.baohongtaisantdmu_admin.Model.KhuVucPhong;
import com.example.baohongtaisantdmu_admin.Model.LoaiPhong;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.Phong.QLKVPhongFPresenter;
import com.example.baohongtaisantdmu_admin.Presenter.Phong.QLLoaiPhongFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.Phong.QLKVPhongFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLKVPhongBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QLKVPhongFragment extends Fragment implements QLKVPhongFView {

    private FragmentQLKVPhongBinding binding;
    private QLKVPhongFPresenter qlkvPhongFPresenter;
    private List<KhuVucPhong> khuVucPhongs;
    private AdminKhuVucPhongAdapter khuVucPhongAdapter;
    public QLKVPhongFragment() {}




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qlkvPhongFPresenter = new QLKVPhongFPresenter(this);
        khuVucPhongs = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLKVPhongBinding.inflate(inflater, container, false);
        if (getContext() != null){
            LinearLayoutManager linearLayoutManagerND = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManagerND);
            qlkvPhongFPresenter._Get_Data_KhuVucPhong();

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
                    if (khuVucPhongAdapter != null && khuVucPhongs != null)
                    {
                        qlkvPhongFPresenter._SearchData_KhuVucPhong(khuVucPhongs, newText);
                    }
                    return false;
                }
            });
        }
        return binding.getRoot();
    }

    @Override
    public void _Get_List_Data_KhuVucPhong_Success(List<KhuVucPhong> khuVucPhongList) {
        khuVucPhongs = khuVucPhongList;
        khuVucPhongAdapter = new AdminKhuVucPhongAdapter(khuVucPhongList, new RCVClickItem() {
            @Override
            public void onClickRCV(Object object, String CURD) {
                KhuVucPhong khuVucPhong = (KhuVucPhong) object;
                if (CURD.equals("EDIT")) {
                    Open_Dialog_Edit(khuVucPhong);
                } else if (CURD.equals("DELETE")) {
                    Open_Dialog_Delete(khuVucPhong);
                }
            }
        });
        binding.rcv.setAdapter(khuVucPhongAdapter);
    }

    @Override
    public void _Search_Data_KhuVucPhong_Success(List<KhuVucPhong> khuVucPhongList) {
        if (khuVucPhongAdapter != null){
            khuVucPhongAdapter.searchDataList(khuVucPhongList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        qlkvPhongFPresenter._Get_Data_KhuVucPhong();
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
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

        tv.setText("Thêm mới khu vực phòng");
        btnthemmoi.setText("Thêm mới");

        btnthemmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlkvPhongFPresenter._Add_New_Data_KhuVucPhong(txtinput.getText().toString());
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

    public void Open_Dialog_Edit(KhuVucPhong khuVucPhong) {
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
        txtinput.setText(khuVucPhong.getTenKVP());
        btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qlkvPhongFPresenter._Edit_Data_KhuVucPhong(khuVucPhong.getMaKVP(), txtinput.getText().toString());
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

    public void Open_Dialog_Delete(KhuVucPhong kv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Bạn có chắc chắn muốn xóa không ?");
        builder.setMessage("Dữ liệu đã xóa không thể khôi phục ! ");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                qlkvPhongFPresenter._Delete_Data_KhuVucPhong(kv.getMaKVP());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}