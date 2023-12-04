package com.example.nh12_pro1121_md18310.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nh12_pro1121_md18310.Adapter.KhachHangAdapter;
import com.example.nh12_pro1121_md18310.Adapter.NhanVienAdapter;
import com.example.nh12_pro1121_md18310.Dao.KhachHangDao;
import com.example.nh12_pro1121_md18310.Dao.NhanVienDao;
import com.example.nh12_pro1121_md18310.Model.KhachHang;
import com.example.nh12_pro1121_md18310.Model.NhanVien;
import com.example.nh12_pro1121_md18310.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentKhachHang extends Fragment {
    private RecyclerView viewQlKh;
    private FloatingActionButton fltadd;
    private KhachHangDao khachHangDao;
    private KhachHangAdapter khachHangAdapter;
    KhachHang khachHang;

    private ArrayList<KhachHang> listKh = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khach_hang, container, false);
        viewQlKh = view.findViewById(R.id.rcv_khachhang);
        fltadd = view.findViewById(R.id.btn_addkh);

        khachHangDao = new KhachHangDao(getContext());
        ArrayList<KhachHang> listKh = khachHangDao.getDs();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        viewQlKh.setLayoutManager(layoutManager);
        KhachHangAdapter khachHangAdapter1 = new KhachHangAdapter(getContext(),listKh);
        viewQlKh.setAdapter(khachHangAdapter1);

        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialogAdd();
            }
        });

        return view;
    }
    public void OpenDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.add_khachhang,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edthoTenKh = view.findViewById(R.id.edt_themhtKh);
        EditText edtnamSinhKh = view.findViewById(R.id.edt_themnsKh);
        EditText edtsdtKh = view.findViewById(R.id.edt_themsdtKh);
        Button btnthemsaveKh = view.findViewById(R.id.btn_themsaveKh);
        btnthemsaveKh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hTenKh = edthoTenKh.getText().toString();
                String nSKh = edtnamSinhKh.getText().toString();
                String sdtKh = edtsdtKh.getText().toString();


                if (hTenKh.equals("")){
                    Toast.makeText(getContext(), "Nhập họ tên", Toast.LENGTH_SHORT).show();
                }else if (nSKh.equals("")){
                    Toast.makeText(getContext(), "Nhập năm sinh", Toast.LENGTH_SHORT).show();
                }else if (sdtKh.equals("")){
                    Toast.makeText(getContext(), "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }else {
                    khachHang = new KhachHang(hTenKh,Integer.parseInt(nSKh), sdtKh);
                    if (khachHangDao.insert(khachHang)){
                        listKh.clear();
                        listKh.addAll(khachHangDao.getDs());
                        dialog.dismiss();
                        ArrayList<KhachHang> list1 = khachHangDao.getDs();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        viewQlKh.setLayoutManager(layoutManager);
                        khachHangAdapter = new KhachHangAdapter(getContext(), list1);
                        viewQlKh.setAdapter(khachHangAdapter);
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }
}