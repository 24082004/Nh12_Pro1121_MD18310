package com.example.nh12_pro1121_md18310.Model;

public class SanPham {
    private int maSanPham;
    private int maLoaisp;
    private String tenSanPham;
    private int donGia;

    public SanPham() {
    }

    public SanPham(int maSanPham, int maLoaisp, String tenSanPham, int donGia) {
        this.maSanPham = maSanPham;
        this.maLoaisp = maLoaisp;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
    }

    public SanPham(int maLoai, String tenSanPham, int donGia) {
        this.maLoaisp = maLoai;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaLoaisp() {
        return maLoaisp;
    }

    public void setMaLoaiSp(int maLoaisp) {
        this.maLoaisp = maLoaisp;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
