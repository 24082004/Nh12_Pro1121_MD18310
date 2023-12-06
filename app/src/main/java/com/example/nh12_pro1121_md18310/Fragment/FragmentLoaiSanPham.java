package com.example.nh12_pro1121_md18310.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nh12_pro1121_md18310.Adapter.LoaiSanPhamAdapter;
import com.example.nh12_pro1121_md18310.Adapter.SanPhamAdapter;
import com.example.nh12_pro1121_md18310.Dao.LoaiSanPhamDao;
import com.example.nh12_pro1121_md18310.Dao.SanPhamDao;
import com.example.nh12_pro1121_md18310.IClickItemRCV;
import com.example.nh12_pro1121_md18310.Model.LoaiSanPham;
import com.example.nh12_pro1121_md18310.Model.SanPham;
import com.example.nh12_pro1121_md18310.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentLoaiSanPham extends Fragment {
    private RecyclerView viewQlSp;
    private FloatingActionButton fltadd;
    private LoaiSanPhamDao loaiSanPhamDao;
    private LoaiSanPhamAdapter loaiSanPhamAdapter;
    LoaiSanPham loaiSanPham;

    private ArrayList<LoaiSanPham> listlSP = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        viewQlSp = view.findViewById(R.id.rcv_loaisanpham);
        fltadd = view.findViewById(R.id.btn_addlsp);
        loaiSanPhamDao = new LoaiSanPhamDao(getContext());
        ArrayList<LoaiSanPham> listlSp = (ArrayList<LoaiSanPham>) loaiSanPhamDao.getDs();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        viewQlSp.setLayoutManager(layoutManager);
        LoaiSanPhamAdapter loaiSanPhamAdapter1 = new LoaiSanPhamAdapter(getContext(),listlSp);
        viewQlSp.setAdapter(loaiSanPhamAdapter1);

        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialogAdd();
            }
        });

        return view;
    }
    public void OpenDialogAdd(){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        View view = inflater.inflate(R.layout.add_loaisanpham,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtthemtenLsp = view.findViewById(R.id.edt_themtenLsp);
        Button btnthemsaveLsp = view.findViewById(R.id.btn_themsaveLsp);
        btnthemsaveLsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenlLsp = edtthemtenLsp.getText().toString();

                if (tenlLsp.equals("")){
                    Toast.makeText(getContext(), "Nhập Tên loại", Toast.LENGTH_SHORT).show();
                }else {
                    loaiSanPham = new LoaiSanPham(tenlLsp);
                    if(loaiSanPhamDao.insert(loaiSanPham)){
                        listlSP.clear();
                        listlSP.addAll(loaiSanPhamDao.getDs());
                        dialog.dismiss();
                        ArrayList<LoaiSanPham> listSp = (ArrayList<LoaiSanPham>) loaiSanPhamDao.getDs();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        viewQlSp.setLayoutManager(layoutManager);
                        LoaiSanPhamAdapter loaiSanPhamAdapter1 = new LoaiSanPhamAdapter(getContext(),listSp);
                        viewQlSp.setAdapter(loaiSanPhamAdapter1);
                        Toast.makeText(getContext(),"Thêm thành công ",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}