package com.example.nh12_pro1121_md18310.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

public class FragmentDoiMatKhau extends Fragment {

    public FragmentDoiMatKhau() {

    }
    EditText edt_oldPassword, edt_newPassword, edt_enterthePassword;
    Button btn_saveDkm, btn_cancelDkm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);

         initUI(view);
         btnSaveDkm();
         btnCancelDkm();
         return view;
    }

    private void btnSaveDkm(){
        AdminDao adminDao = new AdminDao(getContext());
        btn_saveDkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn có chắc chắn muốn đổi mật khẩu không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String oldPass = edt_oldPassword.getText().toString().trim();
                        String newPass = edt_newPassword.getText().toString().trim();
                        String enPass =  edt_enterthePassword.getText().toString().trim();

                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
                        String tenDn = sharedPreferences.getString("Tài khoản","");
                        if(validate(oldPass, newPass, enPass) > 0){
                            Admin admin = adminDao.getID(tenDn);
                            admin.setmK(newPass);
                            if(adminDao.update(admin) > 0){
                                Toast.makeText(getContext(),"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                                edt_oldPassword.setText("");
                                edt_newPassword.setText("");
                                edt_enterthePassword.setText("");
                            }else {
                                Toast.makeText(getContext(),"Thay đổi thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });
    }

    public int validate(String oldPass, String newPass, String enPass){
        int check = 1;
        if (oldPass.length() != 0 && newPass.length() != 0 && enPass.length() != 0) {
            SharedPreferences preferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
            String getPassOld = preferences.getString("Mật khẩu","");
            if (!getPassOld.equals(oldPass)) {
                Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                edt_oldPassword.requestFocus();
                check = -1;
            }else {
                if (!newPass.equals(newPass)){
                    Toast.makeText(getContext(), "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                    edt_enterthePassword.setError("Mật khẩu nhập lại không đúng");
                    edt_enterthePassword.requestFocus();
                    check = -1;
                }
            }
        }else {
            if (oldPass.length() == 0){
                Toast.makeText(getContext(),"Không được để trống", Toast.LENGTH_SHORT).show();
                edt_oldPassword.requestFocus();
            }
            if (newPass.length() == 0){
                Toast.makeText(getContext(),"Không được để trống", Toast.LENGTH_SHORT).show();
                edt_newPassword.requestFocus();
            }
            if (enPass.length() == 0){
                Toast.makeText(getContext(),"Không được để trống", Toast.LENGTH_SHORT).show();
                edt_enterthePassword.requestFocus();
            }
            check = -1;
        }
        return check;
    }

    private void btnCancelDkm(){
        btn_cancelDkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_oldPassword.setText("");
                edt_newPassword.setText("");
                edt_enterthePassword.setText("");
            }
        });
    }
    private void initUI (View view){
        edt_oldPassword = view.findViewById(R.id.edt_oldPassword);
        edt_newPassword = view.findViewById(R.id.edt_newPassword);
        edt_enterthePassword = view.findViewById(R.id.edt_enterthePassword);
        btn_saveDkm = view.findViewById(R.id.btn_saveDkm);
        btn_cancelDkm = view.findViewById(R.id.btn_cancelDkm);
    }
}