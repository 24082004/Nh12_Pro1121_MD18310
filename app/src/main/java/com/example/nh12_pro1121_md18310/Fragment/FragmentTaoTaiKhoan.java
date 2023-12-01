package com.example.nh12_pro1121_md18310.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.nh12_pro1121_md18310.Dao.AdminDao;
import com.example.nh12_pro1121_md18310.Model.Admin;
import com.example.nh12_pro1121_md18310.R;

public class FragmentTaoTaiKhoan extends Fragment {
    public FragmentTaoTaiKhoan() {
    }
    AdminDao adminDao;
    EditText edt_ttkHt, edt_ttkTenDn, edt_ttkMk;
    Button btn_ttk, btn_ttkCancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tao_tai_khoan, container, false);
        adminDao = new AdminDao(getContext());
        initUI(view);
        btnTtk();
        btnCancel();
        return view;
    }

    private void btnCancel(){
        btn_ttkCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_ttkHt.setText("");
                edt_ttkTenDn.setText("");
                edt_ttkMk.setText("");
            }
        });
    }

    private void btnTtk(){
        btn_ttk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ht = edt_ttkHt.getText().toString().trim();
                String tenDn = edt_ttkTenDn.getText().toString().trim();
                String mk = edt_ttkMk.getText().toString().trim();

                if(validate(ht, tenDn, mk) > 0){
                    AdminDao adminDao = new AdminDao(getContext());
                    if (adminDao.insert(new Admin(ht, tenDn, mk)) > 0){
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        edt_ttkHt.setText("");
                        edt_ttkTenDn.setText("");
                        edt_ttkMk.setText("");
                    }
                }
            }
        });
    }

    public int validate(String ht, String tenDn, String mk){
        int check = 1;
        if (ht.length() != 0 && ht.length() != 0 && mk.length() != 0){
            Toast.makeText(getContext(), "Tạo thành công", Toast.LENGTH_SHORT).show();
        }else {
            if (ht.length() == 0){
                edt_ttkHt.setError("Không được để trống");
                edt_ttkHt.requestFocus();
            }if (tenDn.length() == 0){
                edt_ttkTenDn.setError("Không được để trống");
                edt_ttkTenDn.requestFocus();
            }if (mk.length() == 0){
                edt_ttkMk.setError("Không được để trống");
                edt_ttkMk.requestFocus();
            }
        }
        return check;
    }

    private void initUI(View view){
        edt_ttkHt = view.findViewById(R.id.edt_ttkHt);
        edt_ttkTenDn = view.findViewById(R.id.edt_ttkTenDn);
        edt_ttkMk = view.findViewById(R.id.edt_ttkMk);
        btn_ttkCancel = view.findViewById(R.id.btn_ttkCancel);
        btn_ttk = view.findViewById(R.id.btn_ttk);
    }
}