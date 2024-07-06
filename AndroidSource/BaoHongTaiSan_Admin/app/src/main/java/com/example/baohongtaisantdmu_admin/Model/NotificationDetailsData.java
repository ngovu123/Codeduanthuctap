package com.example.baohongtaisantdmu_admin.Model;

public class NotificationDetailsData {
    private int MaND;
    private String TenTS, TenP, Token;
    private int TrangThai, TinhTrang;
    private String MoTa, LoaiNoti;


    public NotificationDetailsData() {
    }

    public NotificationDetailsData(int maND, String tenTS, String tenP, int trangThai, int tinhTrang, String moTa, String Token, String loaiNoti) {
        MaND = maND;
        TenTS = tenTS;
        TenP = tenP;
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        MoTa = moTa;
        LoaiNoti = loaiNoti;
        this.Token = Token;
    }

    public NotificationDetailsData(int maND, String tenTS, String tenP, int trangThai, int tinhTrang, String moTa, String loaiNoti) {
        MaND = maND;
        TenTS = tenTS;
        TenP = tenP;
        TrangThai = trangThai;
        TinhTrang = tinhTrang;
        MoTa = moTa;
        LoaiNoti = loaiNoti;
    }

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
    }

    public String getTenTS() {
        return TenTS;
    }

    public void setTenTS(String tenTS) {
        TenTS = tenTS;
    }

    public String getTenP() {
        return TenP;
    }

    public void setTenP(String tenP) {
        TenP = tenP;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getLoaiNoti() {
        return LoaiNoti;
    }

    public void setLoaiNoti(String loaiNoti) {
        LoaiNoti = loaiNoti;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
