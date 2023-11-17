package com.example.nh12_pro1121_md18310.Model;

public class KhachHang {
    private int maKh;
    private String hoTenKh;
    private int namSinhKh;
    private String sdtKhachHang;

    public KhachHang() {
    }

    public KhachHang(int maKh, String hoTenKh, int namSinhKh, String sdtKhachHang) {
        this.maKh = maKh;
        this.hoTenKh = hoTenKh;
        this.namSinhKh = namSinhKh;
        this.sdtKhachHang = sdtKhachHang;
    }

    public int getMaKh() {
        return maKh;
    }

    public void setMaKh(int maKh) {
        this.maKh = maKh;
    }

    public String getHoTenKh() {
        return hoTenKh;
    }

    public void setHoTenKh(String hoTenKh) {
        this.hoTenKh = hoTenKh;
    }

    public int getNamSinhKh() {
        return namSinhKh;
    }

    public void setNamSinhKh(int namSinhKh) {
        this.namSinhKh = namSinhKh;
    }

    public String getSdtKhachHang() {
        return sdtKhachHang;
    }

    public void setSdtKhachHang(String sdtKhachHang) {
        this.sdtKhachHang = sdtKhachHang;
    }
}
