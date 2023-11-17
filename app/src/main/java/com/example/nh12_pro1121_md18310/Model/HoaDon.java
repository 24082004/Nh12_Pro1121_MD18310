package com.example.nh12_pro1121_md18310.Model;

public class HoaDon {
    private int maHoaDon;
    private String tenSanPham;
    private int soLuong;
    private int tongTien;
    private String trangThaiTT;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, String tenSanPham, int soLuong, int tongTien, String trangThaiTT) {
        this.maHoaDon = maHoaDon;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.trangThaiTT = trangThaiTT;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThaiTT() {
        return trangThaiTT;
    }

    public void setTrangThaiTT(String trangThaiTT) {
        this.trangThaiTT = trangThaiTT;
    }
}
