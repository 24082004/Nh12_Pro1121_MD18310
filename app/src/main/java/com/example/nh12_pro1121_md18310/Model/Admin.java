package com.example.nh12_pro1121_md18310.Model;

public class Admin {
    private String tK;
    private String hoTenAd;
    private String mK;

    public Admin() {
    }


    public Admin(String tK, String hoTenAd, String mK) {
        this.tK = tK;
        this.hoTenAd = hoTenAd;
        this.mK = mK;
    }

    public String gettK() {
        return tK;
    }

    public void settK(String tK) {
        this.tK = tK;
    }

    public String getHoTenAd() {
        return hoTenAd;
    }

    public void setHoTenAd(String hoTenAd) {
        this.hoTenAd = hoTenAd;
    }

    public String getmK() {
        return mK;
    }

    public void setmK(String mK) {
        this.mK = mK;
    }
}
