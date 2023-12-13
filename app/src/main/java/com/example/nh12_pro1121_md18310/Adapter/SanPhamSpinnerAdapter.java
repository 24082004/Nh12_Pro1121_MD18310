package com.example.nh12_pro1121_md18310.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nh12_pro1121_md18310.Model.SanPham;
import com.example.nh12_pro1121_md18310.R;

import java.util.ArrayList;

public class SanPhamSpinnerAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private ArrayList<SanPham> lstSP;

    TextView tv_mahdsp,tv_tenLoaisphd;

    public SanPhamSpinnerAdapter(@NonNull Context context, int resource, ArrayList<SanPham> lstSp) {
        super(context, resource,lstSp);
        this.context = context;
        this.lstSP = lstSp;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_spinner,parent,false);
        final SanPham sach = lstSP.get(position);

        if (sach != null) {
            tv_mahdsp = convertView.findViewById(R.id.tv_mahdsp);
            tv_mahdsp.setText(String.valueOf(sach.getMaSanPham()));
            tv_tenLoaisphd = convertView.findViewById(R.id.tv_tenLoaisphd);
            tv_tenLoaisphd.setText(sach.getTenSanPham());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spinner_selected,parent,false);
        final SanPham sach = lstSP.get(position);

        if (sach != null) {
            tv_mahdsp = convertView.findViewById(R.id.tv_maSp);
            tv_mahdsp.setText(String.valueOf(sach.getMaSanPham()));
            tv_tenLoaisphd = convertView.findViewById(R.id.tv_tenSp);
            tv_tenLoaisphd.setText(sach.getTenSanPham());
        }
        return convertView;
    }
}
