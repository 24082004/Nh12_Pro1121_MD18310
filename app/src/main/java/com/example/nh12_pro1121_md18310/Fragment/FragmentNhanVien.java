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

import com.example.nh12_pro1121_md18310.Adapter.NhanVienAdapter;
import com.example.nh12_pro1121_md18310.Dao.NhanVienDao;
import com.example.nh12_pro1121_md18310.Model.NhanVien;
import com.example.nh12_pro1121_md18310.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentNhanVien extends Fragment {
    private RecyclerView viewQlNv;
    private FloatingActionButton fltadd;
    private NhanVienDao nhanVienDao;
    private NhanVienAdapter nhanVienAdapter;
    NhanVien nhanVien;

    private ArrayList<NhanVien> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        viewQlNv = view.findViewById(R.id.rcv_nhanvien);
        fltadd = view.findViewById(R.id.btn_addnv);
        nhanVienDao = new NhanVienDao(getContext());
        ArrayList<NhanVien> list = nhanVienDao.getDs();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        viewQlNv.setLayoutManager(layoutManager);
        NhanVienAdapter nhanVienAdapter = new NhanVienAdapter(getContext(),list);
        viewQlNv.setAdapter(nhanVienAdapter);

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
        View view = inflater.inflate(R.layout.add_nhanvien,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edthoTenNv = view.findViewById(R.id.edt_themhtNv);
        EditText edtnamSinhNv = view.findViewById(R.id.edt_themnsNv);
        EditText edtsdtNv = view.findViewById(R.id.edt_themsdtNv);
        EditText edtEmailNv = view.findViewById(R.id.edt_thememailNv);
        Button btnthemsaveNv = view.findViewById(R.id.btn_themsaveNv);
        btnthemsaveNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hTenNv = edthoTenNv.getText().toString();
                String nSNv = edtnamSinhNv.getText().toString();
                String sdtNv = edtsdtNv.getText().toString();
                String emailNv = edtEmailNv.getText().toString();

                if (hTenNv.equals("")){
                    Toast.makeText(getContext(), "Nhập họ tên", Toast.LENGTH_SHORT).show();
                }else if (nSNv.equals("")){
                    Toast.makeText(getContext(), "Nhập năm sinh", Toast.LENGTH_SHORT).show();
                }else if (sdtNv.equals("")){
                    Toast.makeText(getContext(), "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }else if (emailNv.equals("")){
                    Toast.makeText(getContext(), "Nhập email", Toast.LENGTH_SHORT).show();
                }else {
                    nhanVien = new NhanVien(hTenNv,Integer.parseInt(nSNv), sdtNv, emailNv);
                    if(nhanVienDao.insert(nhanVien)){
                        list.clear();
                        list.addAll(nhanVienDao.getDs());
                        dialog.dismiss();
                        ArrayList<NhanVien> list = nhanVienDao.getDs();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        viewQlNv.setLayoutManager(layoutManager);
                        NhanVienAdapter nhanVienAdapter1 = new NhanVienAdapter(getContext(),list);
                        viewQlNv.setAdapter(nhanVienAdapter1);
                        Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}