package com.example.nh12_pro1121_md18310.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nh12_pro1121_md18310.Adapter.HoaDonAdapter;
import com.example.nh12_pro1121_md18310.Adapter.SanPhamSpinnerAdapter;
import com.example.nh12_pro1121_md18310.Dao.HoaDonDao;
import com.example.nh12_pro1121_md18310.Model.HoaDon;
import com.example.nh12_pro1121_md18310.Model.SanPham;
import com.example.nh12_pro1121_md18310.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentHoaDon extends Fragment {
    private RecyclerView viewQlHd;
    private FloatingActionButton fltadd;
    private HoaDonDao hoaDonDao;
    HoaDon hoaDon;
    ArrayList<SanPham> lst;
    SanPhamSpinnerAdapter sanPhamSpinnerAdapter;
    private ArrayList<HoaDon> listHd = new ArrayList<>();
    int maSp = 0, dongia;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        viewQlHd = view.findViewById(R.id.rcv_hoadon);
        fltadd = view.findViewById(R.id.btn_addhd);
        lst = new ArrayList<>();
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
        lst.add(new SanPham(1,"Bàn", "Bàn gỗ", 100));
        lst.add(new SanPham(2,"Ghế", "Ghế gỗ", 200));
        lst.add(new SanPham(3,"Tủ", "Tủ gỗ", 200));
        Spinner edtthemtenspHd = view.findViewById(R.id.edt_themtenspHd);
        EditText edtthemsoluongHd = view.findViewById(R.id.edt_themsoluongHd);
        EditText edtthemtongtienHd = view.findViewById(R.id.edt_themtongtienHd);
        EditText edtthemtrangthaiHd = view.findViewById(R.id.edt_themtrangthaiHd);
        Button btnthemsaveHd = view.findViewById(R.id.btn_themsaveHd);
        btnthemsaveHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtslHd = edtthemsoluongHd.getText().toString();
                String edttotHd = edtthemtongtienHd.getText().toString();
                String edtttHd = edtthemtrangthaiHd.getText().toString();


                if (edtslHd.equals("")){
                    Toast.makeText(getContext(), "Nhập số lượng", Toast.LENGTH_SHORT).show();
                }else if (edttotHd.equals("")){
                    Toast.makeText(getContext(), "Tổng tiền", Toast.LENGTH_SHORT).show();
                }else if (edtttHd.equals("")){
                    Toast.makeText(getContext(), "Trạng thái thanh toán", Toast.LENGTH_SHORT).show();
                }else {
                    hoaDon = new HoaDon(Integer.parseInt(edtslHd),Integer.parseInt(edttotHd), edtttHd);
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
        sanPhamSpinnerAdapter = new SanPhamSpinnerAdapter(getContext(),R.layout.item_view_spinner,lst);
        edtthemtenspHd.setAdapter(sanPhamSpinnerAdapter);
        edtthemtenspHd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSp = lst.get(position).getMaSanPham();
                dongia = lst.get(position).getDonGia();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}