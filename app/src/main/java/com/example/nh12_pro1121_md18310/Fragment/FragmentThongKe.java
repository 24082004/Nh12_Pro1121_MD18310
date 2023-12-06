package com.example.nh12_pro1121_md18310.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.nh12_pro1121_md18310.Dao.DoanhThuDao;
import com.example.nh12_pro1121_md18310.R;

public class FragmentThongKe extends Fragment {

    public FragmentThongKe() {
    }

    TextView tv_doanhthu;
    Button btnDoanhThu;

    DoanhThuDao doanhThuDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thong_ke, container, false);
        tv_doanhthu = v.findViewById(R.id.tv_doanhthu);
        btnDoanhThu = v.findViewById(R.id.btn_doanhthu);
        doanhThuDao = new DoanhThuDao(getContext());

        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(doanhThuDao.getDoanhThu() == 0){
                 Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_SHORT).show();
             } else {
                 tv_doanhthu.setText("Doanh thu: " + doanhThuDao.getDoanhThu() + " VNĐ");
             }
            }
        });
        return v;
    }
}