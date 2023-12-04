package com.example.nh12_pro1121_md18310.Model;

public class NhanVien {
    private int maNv;
    private String hoTenNv;
    private int namSinhNv;
    private String sdtNv;
    private String emailNv;

    public NhanVien() {
    }

    public NhanVien(int maNv, String hoTenNv, int namSinhNv, String sdtNv, String emailNv) {
        this.maNv = maNv;
        this.hoTenNv = hoTenNv;
        this.namSinhNv = namSinhNv;
        this.sdtNv = sdtNv;
        this.emailNv = emailNv;
    }

    public NhanVien(String hoTenNv, int namSinhNv, String sdtNv, String emailNv) {
        this.hoTenNv = hoTenNv;
        this.namSinhNv = namSinhNv;
        this.sdtNv = sdtNv;
        this.emailNv = emailNv;
    }

    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    public String getHoTenNv() {
        return hoTenNv;
    }

    public void setHoTenNv(String hoTenNv) {
        this.hoTenNv = hoTenNv;
    }

    public int getNamSinhNv() {
        return namSinhNv;
    }

    public void setNamSinhNv(int namSinhNv) {
        this.namSinhNv = namSinhNv;
    }

    public String getSdtNv() {
        return sdtNv;
    }

    public void setSdtNv(String sdtNv) {
        this.sdtNv = sdtNv;
    }

    public String getEmailNv() {
        return emailNv;
    }

    public void setEmailNv(String emailNv) {
        this.emailNv = emailNv;
    }
}
