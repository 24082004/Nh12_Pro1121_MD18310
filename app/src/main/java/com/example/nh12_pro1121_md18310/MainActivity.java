package com.example.nh12_pro1121_md18310;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nh12_pro1121_md18310.Dao.AdminDao;
import com.example.nh12_pro1121_md18310.Fragment.FragmentDoiMatKhau;
import com.example.nh12_pro1121_md18310.Fragment.FragmentHoaDon;
import com.example.nh12_pro1121_md18310.Fragment.FragmentKhachHang;
import com.example.nh12_pro1121_md18310.Fragment.FragmentLoaiSanPham;
import com.example.nh12_pro1121_md18310.Fragment.FragmentNhanVien;
import com.example.nh12_pro1121_md18310.Fragment.FragmentSanPham;
import com.example.nh12_pro1121_md18310.Fragment.FragmentTaoTaiKhoan;
import com.example.nh12_pro1121_md18310.Fragment.FragmentThongKe;
import com.example.nh12_pro1121_md18310.Model.Admin;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final int FRAGMENT_SANPHAM = 0;
    private static final int FRAGMENT_LOAISANPHAM = 1;
    private static final int FRAGMENT_NHANVIEN = 2;
    private static final int FRAGMENT_KHACHHANG = 3;
    private static final int FRAGMENT_HOADON = 4;
    private static final int FRAGMENT_DOANHTHU = 5;
    private static final int FRAGMENT_TAOTAIKHOAN = 6;
    private static final int FRAGMENT_DOIMATKHAU = 7;
    private int mCurrentFragment = FRAGMENT_SANPHAM;
    Toolbar toolbar;
    DrawerLayout layoutMain;
    NavigationView navigationView_main;
    String[] title = {"Quản lý sản phẩm",
            "Quản lý Loại sản phẩm",
            "Quản lý nhân viên",
            "Quản lý khách hàng",
            "Quản lý hóa đơn",
            "Doanh thu",
            "Tạo tài khoản",
            "Đổi mật khẩu"};
    TextView tv_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        navigationView_main();
        navigationView_main.setItemTextColor(ColorStateList.valueOf(getColor(R.color.black)));
        navigationView_main.setItemIconTintList(ColorStateList.valueOf(getColor(R.color.black)));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title[0]);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this,layoutMain,toolbar,R.string.open,R.string.close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        layoutMain.addDrawerListener(drawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(getDrawable(R.drawable.ic_menu));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_main,new FragmentSanPham());
        fragmentTransaction.commit();
        layoutMain.close();

        showInf();
    }
    private void showInf() {
        AdminDao adminDao = new AdminDao(MainActivity.this);
        Intent intent = getIntent();
        String tK = intent.getStringExtra("Tài khoản");

        if (!tK.equals("admin")) {
            Admin admin = adminDao.getID(tK);
            tv_username.setText(admin.gettK());

            navigationView_main.getMenu().findItem(R.id.createAccount).setVisible(false);
        }
    }
    private void navigationView_main(){
        navigationView_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menusanpham) {
                    toolbar.setTitle(title[0]);
                    if (mCurrentFragment != FRAGMENT_SANPHAM) {
                        replaceFragment(new FragmentSanPham());
                        mCurrentFragment = FRAGMENT_SANPHAM;
                    }
                } else if(item.getItemId() == R.id.menuloaisanpham) {
                    toolbar.setTitle(title[1]);
                    if (mCurrentFragment != FRAGMENT_LOAISANPHAM) {
                        replaceFragment(new FragmentLoaiSanPham());
                        mCurrentFragment = FRAGMENT_LOAISANPHAM;
                    }
                } else if (item.getItemId() == R.id.menunhanvien) {
                    toolbar.setTitle(title[2]);
                    if (mCurrentFragment != FRAGMENT_NHANVIEN) {
                        replaceFragment(new FragmentNhanVien());
                        mCurrentFragment = FRAGMENT_NHANVIEN;
                    }
                } else if (item.getItemId() == R.id.menukhachhang) {
                    toolbar.setTitle(title[3]);
                    if (mCurrentFragment != FRAGMENT_KHACHHANG) {
                        replaceFragment(new FragmentKhachHang());
                        mCurrentFragment = FRAGMENT_KHACHHANG;
                    }
                } else if (item.getItemId() == R.id.menuhoadon) {
                    toolbar.setTitle(title[4]);
                    if (mCurrentFragment != FRAGMENT_HOADON) {
                        replaceFragment(new FragmentHoaDon());
                        mCurrentFragment = FRAGMENT_HOADON;
                    }
                } else if (item.getItemId() == R.id.menuDoanhThu) {
                    toolbar.setTitle(title[5]);
                    if (mCurrentFragment != FRAGMENT_DOANHTHU) {
                        replaceFragment(new FragmentThongKe());
                        mCurrentFragment = FRAGMENT_DOANHTHU;
                    }
                } else if (item.getItemId() == R.id.createAccount) {
                    toolbar.setTitle(title[6]);
                    if (mCurrentFragment != FRAGMENT_TAOTAIKHOAN) {
                        replaceFragment(new FragmentTaoTaiKhoan());
                        mCurrentFragment = FRAGMENT_TAOTAIKHOAN;
                    }
                } else if (item.getItemId() == R.id.changePassword) {
                    toolbar.setTitle(title[7]);
                    if (mCurrentFragment != FRAGMENT_DOIMATKHAU) {
                        replaceFragment(new FragmentDoiMatKhau());
                        mCurrentFragment = FRAGMENT_DOIMATKHAU;
                    }
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Bạn có chắc chắn muốn đăng xuất tài khoản này không ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this,DangNhapActivity.class));
                            finishAffinity();
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                return true;
            }
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout_main,fragment);
        fragmentTransaction.commit();
        layoutMain.close();
    }
    private void initUI() {
        toolbar = findViewById(R.id.toolbar_home);

        layoutMain = findViewById(R.id.layoutMain);

        navigationView_main = findViewById(R.id.navigationView_main);

        tv_username = navigationView_main.getHeaderView(0).findViewById(R.id.txt_name);
    }
}