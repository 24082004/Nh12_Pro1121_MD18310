package com.example.nh12_pro1121_md18310;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nh12_pro1121_md18310.Dao.AdminDao;
import com.google.android.material.textfield.TextInputLayout;

public class DangNhapActivity extends AppCompatActivity {
    Button btn_dn;
    EditText edt_tkdn, edt_mkdn;
    TextInputLayout w_usename, w_password;
    String username,password;
    CheckBox chk_rememberAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initUI();
        btnLogin();
        checkSharedPreferences();
    }
    private void initUI(){
        btn_dn = findViewById(R.id.btn_dn);
        edt_tkdn = findViewById(R.id.edt_tkdn);
        edt_mkdn = findViewById(R.id.edt_mkdn);
        w_usename = findViewById(R.id.w_username);
        w_password = findViewById(R.id.w_password);
        chk_rememberAccount = findViewById(R.id.chk_rememberAccount);
    }

    private void btnLogin(){
        btn_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gettK = edt_tkdn.getText().toString().trim();
                String getmK = edt_mkdn.getText().toString().trim();
                if(gettK.isEmpty() || getmK.isEmpty()){
                    if(gettK.isEmpty()){
                        w_usename.setError("Tài khoản không được để trống!");
                    }else{
                        w_usename.setErrorEnabled(false);
                    }

                    if(getmK.isEmpty()){
                        w_password.setError("Mật khẩu không được để trống!");
                    }else{
                        w_password.setErrorEnabled(false);
                    }
                }else{
                    AdminDao adminDao = new AdminDao(DangNhapActivity.this);
                    if(adminDao.checkLogin(gettK, getmK) > 0){
                        Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        rememberAccount(chk_rememberAccount.isChecked());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("Tài khoản", gettK);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(DangNhapActivity.this, "Tài khoản hoặc mạt khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void rememberAccount(Boolean chkRemember) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Tài khoản", edt_tkdn.getText().toString().trim());
        editor.putString("Mật khẩu", edt_mkdn.getText().toString().trim());
        editor.putBoolean("chkRemember", chkRemember);
        editor.apply();
    }
    private void checkSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        username = sharedPreferences.getString("Tài khoản", "");
        password = sharedPreferences.getString("Mật khẩu","");
        Boolean chkRemember = sharedPreferences.getBoolean("chkRemember",false);
        chk_rememberAccount.setChecked(chkRemember);
        if (chk_rememberAccount.isChecked()) {
            edt_tkdn.setText(username);
            edt_mkdn.setText(password);
        }
    }
}