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
import com.example.nh12_pro1121_md18310.Adapter.SanPhamAdapter;
import com.example.nh12_pro1121_md18310.Dao.NhanVienDao;
import com.example.nh12_pro1121_md18310.Dao.SanPhamDao;
import com.example.nh12_pro1121_md18310.Model.NhanVien;
import com.example.nh12_pro1121_md18310.Model.SanPham;
import com.example.nh12_pro1121_md18310.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentSanPham extends Fragment {
    private RecyclerView viewQlSp;
    private FloatingActionButton fltadd;
    private SanPhamDao sanPhamDao;
    private SanPhamAdapter sanPhamAdapter;
    SanPham sanPham;

    private ArrayList<SanPham> listSP = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
        viewQlSp = view.findViewById(R.id.rcv_sanpham);
        fltadd = view.findViewById(R.id.btn_addsp);
        sanPhamDao = new SanPhamDao(getContext());
        ArrayList<SanPham> listSp = sanPhamDao.getDs();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        viewQlSp.setLayoutManager(layoutManager);
        SanPhamAdapter sanPhamAdapter1 = new SanPhamAdapter(getContext(),listSp);
        viewQlSp.setAdapter(sanPhamAdapter1);

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
        View view = inflater.inflate(R.layout.add_sanpham,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtthemlLsp = view.findViewById(R.id.edt_themlLsp);
        EditText edtthemtenSp = view.findViewById(R.id.edt_themtenSp);
        EditText edtthemdgSp = view.findViewById(R.id.edt_themdgSp);
        Button btnthemsaveSp = view.findViewById(R.id.btn_themsaveSp);
        btnthemsaveSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lLsp = edtthemlLsp.getText().toString();
                String tenSp = edtthemtenSp.getText().toString();
                String dgSp = edtthemdgSp.getText().toString();

                if (lLsp.equals("")){
                    Toast.makeText(getContext(), "Nhập mã loại", Toast.LENGTH_SHORT).show();
                }else if (tenSp.equals("")){
                    Toast.makeText(getContext(), "Nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
                }else if (dgSp.equals("")){
                    Toast.makeText(getContext(), "Nhập đơn giá", Toast.LENGTH_SHORT).show();
                }else {
                    sanPham = new SanPham(Integer.parseInt(lLsp),tenSp, Integer.parseInt(dgSp));
                    if(sanPhamDao.insert(sanPham)){
                        listSP.clear();
                        listSP.addAll(sanPhamDao.getDs());
                        dialog.dismiss();
                        ArrayList<SanPham> listS = sanPhamDao.getDs();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        viewQlSp.setLayoutManager(layoutManager);
                        SanPhamAdapter sanPhamAdapter1 = new SanPhamAdapter(getContext(),listS);
                        viewQlSp.setAdapter(sanPhamAdapter1);
                        Toast.makeText(getContext(),"Thêm thành công ",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}