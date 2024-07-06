package com.example.baohongtaisantdmu.Model;

public class AssetInRoom {

    private int MaTS, MaLTS, MaNTS, MaBL, MaPB, MaND, TinhTrang, TrangThai, SoLuong, NamSanXuat;
    private String TenP, TenTS, TenLTS, TenNTS, HangSanXuat, NuocSanXuat, Mota, HinhAnh, NgayCapNhat, NgayTao;

    public AssetInRoom() {}


    public AssetInRoom(int maTS, int maLTS, int maNTS, int maBL, int maPB, int maND, int tinhTrang, int trangThai, int soLuong, int namSanXuat, String tenP, String tenTS, String tenLTS, String tenNTS, String hangSanXuat, String nuocSanXuat, String mota, String hinhAnh, String ngayCapNhat, String ngayTao) {
        MaTS = maTS;
        MaLTS = maLTS;
        MaNTS = maNTS;
        MaBL = maBL;
        MaPB = maPB;
        MaND = maND;
        TinhTrang = tinhTrang;
        TrangThai = trangThai;
        SoLuong = soLuong;
        NamSanXuat = namSanXuat;
        TenP = tenP;
        TenTS = tenTS;
        TenLTS = tenLTS;
        TenNTS = tenNTS;
        HangSanXuat = hangSanXuat;
        NuocSanXuat = nuocSanXuat;
        Mota = mota;
        HinhAnh = hinhAnh;
        NgayCapNhat = ngayCapNhat;
        NgayTao = ngayTao;
    }

    public int getNamSanXuat() {
        return NamSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        NamSanXuat = namSanXuat;
    }

    public String getHangSanXuat() {
        return HangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        HangSanXuat = hangSanXuat;
    }

    public String getNuocSanXuat() {
        return NuocSanXuat;
    }

    public void setNuocSanXuat(String nuocSanXuat) {
        NuocSanXuat = nuocSanXuat;
    }



    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getTenP() {
        return TenP;
    }

    public void setTenP(String tenP) {
        TenP = tenP;
    }

    public String getTenTS() {
        return TenTS;
    }

    public void setTenTS(String tenTS) {
        TenTS = tenTS;
    }

    public String getTenLTS() {
        return TenLTS;
    }

    public void setTenLTS(String tenLTS) {
        TenLTS = tenLTS;
    }

    public String getTenNTS() {
        return TenNTS;
    }

    public void setTenNTS(String tenNTS) {
        TenNTS = tenNTS;
    }

    public int getMaTS() {
        return MaTS;
    }

    public void setMaTS(int maTS) {
        MaTS = maTS;
    }

    public int getMaLTS() {
        return MaLTS;
    }

    public void setMaLTS(int maLTS) {
        MaLTS = maLTS;
    }

    public int getMaNTS() {
        return MaNTS;
    }

    public void setMaNTS(int maNTS) {
        MaNTS = maNTS;
    }

    public int getMaBL() {
        return MaBL;
    }

    public void setMaBL(int maBL) {
        MaBL = maBL;
    }

    public int getMaPB() {
        return MaPB;
    }

    public void setMaPB(int maPB) {
        MaPB = maPB;
    }

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int maND) {
        MaND = maND;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getNgayCapNhat() {
        return NgayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        NgayCapNhat = ngayCapNhat;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }
}
