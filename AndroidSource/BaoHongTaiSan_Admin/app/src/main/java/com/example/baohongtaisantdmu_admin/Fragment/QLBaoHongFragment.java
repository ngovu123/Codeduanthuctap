package com.example.baohongtaisantdmu_admin.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baohongtaisantdmu_admin.Adapter.AdminBaoHongAdapter;
import com.example.baohongtaisantdmu_admin.Adapter.PhanBoTaiSan.AssetInRoomAdapter;
import com.example.baohongtaisantdmu_admin.Model.BaoHong;
import com.example.baohongtaisantdmu_admin.Model.NhomTaiSan;
import com.example.baohongtaisantdmu_admin.Model.NotificationData;
import com.example.baohongtaisantdmu_admin.Model.NotificationDetailsData;
import com.example.baohongtaisantdmu_admin.Model.RCVClickItem;
import com.example.baohongtaisantdmu_admin.Presenter.QLBaoHongFPresenter;
import com.example.baohongtaisantdmu_admin.R;
import com.example.baohongtaisantdmu_admin.View.QLBaoHongFView;
import com.example.baohongtaisantdmu_admin.databinding.FragmentHomePageBinding;
import com.example.baohongtaisantdmu_admin.databinding.FragmentQLBaoHongBinding;

import java.util.List;

public class QLBaoHongFragment extends Fragment implements QLBaoHongFView {


    private FragmentQLBaoHongBinding binding;
    private QLBaoHongFPresenter qlBaoHongFPresenter;
    private List<BaoHong> baoHongs;
    private AdminBaoHongAdapter adminBaoHongAdapter;
    private int ID_TrangThai = -1, ID_TrangThai_Push_Noti = -1;
    private String searchText = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qlBaoHongFPresenter = new QLBaoHongFPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQLBaoHongBinding.inflate(inflater, container, false);
        if (getContext() != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
            binding.rcv.setLayoutManager(linearLayoutManager);
            qlBaoHongFPresenter._Get_List_Data_BaoHong();

            binding.sv.clearFocus();

            binding.sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if ( adminBaoHongAdapter != null && getContext() != null)
                    {
                        searchText = newText;
                        qlBaoHongFPresenter._Search_Data_BaoHong(baoHongs, searchText, ID_TrangThai);
                    }
                    return false;
                }
            });

            binding.btnTatCa.setOnClickListener(view -> {
                _SetColor_BtnSearch(-1);
                ID_TrangThai = -1;
                qlBaoHongFPresenter._Search_Data_BaoHong(baoHongs, searchText, ID_TrangThai);
            });

            binding.btnDaTiepNhan.setOnClickListener(view -> {
                _SetColor_BtnSearch(2);
                ID_TrangThai = 2;
                qlBaoHongFPresenter._Search_Data_BaoHong(baoHongs, searchText, ID_TrangThai);
            });

            binding.btnDangSuaChua.setOnClickListener(view -> {
                _SetColor_BtnSearch(3);
                ID_TrangThai = 3;
                qlBaoHongFPresenter._Search_Data_BaoHong(baoHongs, searchText, ID_TrangThai);
            });

            binding.btnSuaThanhCong.setOnClickListener(view -> {
                _SetColor_BtnSearch(4);
                ID_TrangThai = 4;
                qlBaoHongFPresenter._Search_Data_BaoHong(baoHongs, searchText, ID_TrangThai);
            });

            binding.btnSuaKhongThanhCong.setOnClickListener(view -> {
                _SetColor_BtnSearch(5);
                ID_TrangThai = 5;
                qlBaoHongFPresenter._Search_Data_BaoHong(baoHongs, searchText, ID_TrangThai);
            });


        }
        return binding.getRoot();
    }

    @Override
    public void _Get_List_Data_BaoHong_Success(List<BaoHong> baoHongList) {
        if (getContext() != null && baoHongList != null)
        {
            baoHongs = baoHongList;
            adminBaoHongAdapter = new AdminBaoHongAdapter(baoHongList, new RCVClickItem() {
                @Override
                public void onClickRCV(Object object, String CURD) {
                    BaoHong baoHong = (BaoHong) object;
                    switch (CURD) {
                        case "Set_TT_2":
                            ID_TrangThai_Push_Noti = 2;
                            break;
                        case "Set_TT_3":
                            ID_TrangThai_Push_Noti = 3;
                            break;
                        case "Set_TT_4":
                            ID_TrangThai_Push_Noti = 4;
                            break;
                        case "Set_TT_5":
                            ID_TrangThai_Push_Noti = 5;
                            break;
                    }
                    if (ID_TrangThai_Push_Noti != -1){
                        qlBaoHongFPresenter._Set_TrangThai(baoHong, ID_TrangThai_Push_Noti);
                    }
                }
            });
            binding.rcv.setAdapter(adminBaoHongAdapter);
        }
    }

    @Override
    public void _Set_TrangThai_BaoHong_Success(BaoHong baoHong) {
        NotificationDetailsData notificationDetailsData = new NotificationDetailsData(baoHong.getMaND(), baoHong.getTenTS(), baoHong.getTenP(), ID_TrangThai_Push_Noti, baoHong.getTinhTrang(), baoHong.getMota(), baoHong.getToken(), "AdminToUser");
        qlBaoHongFPresenter._Push_Notification(new NotificationData(notificationDetailsData, baoHong.getToken()));

    }

    @Override
    public void _Push_Notification_Success() {
        Toast.makeText(getContext(), "Cập nhật trạng thái thành công.", Toast.LENGTH_SHORT).show();
        qlBaoHongFPresenter._Get_List_Data_BaoHong();
    }

    @Override
    public void _Push_Notification_Error(String Message) {
        Toast.makeText(getContext(), "Có lỗi khi thông báo với ADMIN !!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void _Search_Data_TaiSan_Success(List<BaoHong> baoHongList) {
        if (adminBaoHongAdapter != null && getContext() != null)
        {
            adminBaoHongAdapter.searchDataList(baoHongList);
        }
    }

    @Override
    public void _Show_Success(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
            qlBaoHongFPresenter._Get_List_Data_BaoHong();
        }
    }

    @Override
    public void _Show_Error(String LoaiShow, String Message) {
        if (getContext() != null)
        {
            Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        }
    }

    public void _SetColor_BtnSearch(int i) {
        if (i == -1){
            binding.btnTatCa.setBackgroundColor(Color.parseColor("#F72C00"));
            binding.btnDaTiepNhan.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDangSuaChua.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaKhongThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
        }else if (i == 2){
            binding.btnTatCa.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDaTiepNhan.setBackgroundColor(Color.parseColor("#F72C00"));
            binding.btnDangSuaChua.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaKhongThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
        }else if (i == 3){
            binding.btnTatCa.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDaTiepNhan.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDangSuaChua.setBackgroundColor(Color.parseColor("#F72C00"));
            binding.btnSuaThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaKhongThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
        }else if (i == 4){
            binding.btnTatCa.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDaTiepNhan.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDangSuaChua.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaThanhCong.setBackgroundColor(Color.parseColor("#F72C00"));
            binding.btnSuaKhongThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
        }else if (i == 5){
            binding.btnTatCa.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDaTiepNhan.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnDangSuaChua.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaThanhCong.setBackgroundColor(Color.parseColor("#E4E4E4"));
            binding.btnSuaKhongThanhCong.setBackgroundColor(Color.parseColor("#F72C00"));
        }

    }
}