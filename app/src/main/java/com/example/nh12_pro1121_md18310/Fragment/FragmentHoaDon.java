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

import com.example.nh12_pro1121_md18310.Adapter.HoaDonAdapter;
import com.example.nh12_pro1121_md18310.Dao.HoaDonDao;
import com.example.nh12_pro1121_md18310.Model.HoaDon;
import com.example.nh12_pro1121_md18310.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentHoaDon extends Fragment {
    private RecyclerView viewQlHd;
    private FloatingActionButton fltadd;
    private HoaDonDao hoaDonDao;
    HoaDon hoaDon;

    private ArrayList<HoaDon> listHd = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        viewQlHd = view.findViewById(R.id.rcv_hoadon);
        fltadd = view.findViewById(R.id.btn_addhd);

        hoaDonDao = new HoaDonDao(getContext());
        ArrayList<HoaDon> list = hoaDonDao.getDshd();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        viewQlHd.setLayoutManager(layoutManager);
        HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(getContext(),list);
        viewQlHd.setAdapter(hoaDonAdapter);

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
        View view = inflater.inflate(R.layout.add_hoadon,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtthemtenspHd = view.findViewById(R.id.edt_themtenspHd);
        EditText edtthemsoluongHd = view.findViewById(R.id.edt_themsoluongHd);
        EditText edtthemtongtienHd = view.findViewById(R.id.edt_themtongtienHd);
        EditText edtthemtrangthaiHd = view.findViewById(R.id.edt_themtrangthaiHd);
        Button btnthemsaveHd = view.findViewById(R.id.btn_themsaveHd);
        btnthemsaveHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edttenHd = edtthemtenspHd.getText().toString();
                String edtslHd = edtthemsoluongHd.getText().toString();
                String edttotHd = edtthemtongtienHd.getText().toString();
                String edtttHd = edtthemtrangthaiHd.getText().toString();


                if (edttenHd.equals("")){
                    Toast.makeText(getContext(), "Nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
                }else if (edtslHd.equals("")){
                    Toast.makeText(getContext(), "Nhập số lượng", Toast.LENGTH_SHORT).show();
                }else if (edttotHd.equals("")){
                    Toast.makeText(getContext(), "Tổng tiền", Toast.LENGTH_SHORT).show();
                }else if (edtttHd.equals("")){
                    Toast.makeText(getContext(), "Trạng thái thanh toán", Toast.LENGTH_SHORT).show();
                }else {
                    hoaDon = new HoaDon(edttenHd,Integer.parseInt(edtslHd),Integer.parseInt(edttotHd), edtttHd);
                    if(hoaDonDao.insert(hoaDon)){
                        listHd.clear();
                        listHd.addAll(hoaDonDao.getDshd());
                        dialog.dismiss();
                        ArrayList<HoaDon> list = hoaDonDao.getDshd();

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        viewQlHd.setLayoutManager(layoutManager);
                        HoaDonAdapter hoaDonAdapter = new HoaDonAdapter(getContext(),list);
                        viewQlHd.setAdapter(hoaDonAdapter);
                        Toast.makeText(getContext(),"Thêm thành công",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getContext(),"Thêm thất bại",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}