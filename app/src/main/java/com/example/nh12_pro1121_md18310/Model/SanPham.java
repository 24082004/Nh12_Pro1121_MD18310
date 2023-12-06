package com.example.nh12_pro1121_md18310.Model;

public class SanPham {
    private int maSanPham;
    private String tenlLoaisp;
    private String tenSanPham;
    private int donGia;

    public SanPham() {
    }


    public SanPham(int maSanPham, String tenlLoaisp, String tenSanPham, int donGia) {
        this.maSanPham = maSanPham;
        this.tenlLoaisp = tenlLoaisp;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
    }

    public SanPham(String tenLoaisp, String tenSanPham, int donGia) {
        this.tenlLoaisp = tenLoaisp;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
    }


    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenlLoaisp() {
        return tenlLoaisp;
    }

    public void setTenlLoaisp(String tenlLoaisp) {
        this.tenlLoaisp = tenlLoaisp;
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
