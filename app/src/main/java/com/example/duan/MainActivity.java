package com.example.duan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan.DAO.ThuKhoDAO;
import com.example.duan.DTO.ThuKho;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    NavigationView nv;
    ThuKhoDAO thuKhoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // anh xa
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        nv = findViewById(R.id.nvView);
        // set toolbar thay actionbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);
        // set mau icon ve ban goc
        nv.setItemIconTintList(null);

        // show user trên header
        mHeaderView = nv.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        thuKhoDAO = new ThuKhoDAO(this);
        ThuKho thuKho = thuKhoDAO.getID(user);
        String username = thuKho.getHoTen();
        tvUser.setText("Welcome " + username + "!");

        // admin co quyen add user
//        if (user.equalsIgnoreCase("admin")) {
//            nv.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
//        }
        Fragment_Hoa_don frhoadon = new Fragment_Hoa_don();
        replaceFrg(frhoadon);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.nav_PhieuMuon) {
                    setTitle("Quản lý Hóa Đơn");
                    replaceFrg(frhoadon);

                } else if (id == R.id.nav_SanPhamh) {
                    setTitle("Quản lý Sản Phẩm");
                    Fragment_San_pham frloaisach = new Fragment_San_pham();
                    replaceFrg(frloaisach);

                } else if (id == R.id.nav_TheLoai) {
                    setTitle("Quản lý Thể loại");
                    Fragment_The_loai frsach = new Fragment_The_loai();
                    replaceFrg(frsach);

                } else if (id == R.id.nav_ThanhVien) {
                    setTitle("Quản lý Thành Viên");
                    Fragment_thanh_vien frthanhvien = new Fragment_thanh_vien();
                    replaceFrg(frthanhvien);

                } else if (id == R.id.sub_Top) {
                    setTitle("Thông kê hóa đơn");
                    Fragment_Top frtop = new Fragment_Top();
                    replaceFrg(frtop);

                } else if (id == R.id.sub_DoanhThu) {
                    setTitle("Doanh thu");
                    Fragment_doanh_so frdoanhthu = new Fragment_doanh_so();
                    replaceFrg(frdoanhthu);


                } else if (id == R.id.sub_Pass) {
                    setTitle("Thay đổi mật khẩu");
                    Fragment_change_pass frchangepass = new Fragment_change_pass();
                    replaceFrg(frchangepass);

                } else if (id == R.id.sub_Logout) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Đăng xuất");
                    builder.setMessage("Bạn có muốn đăng xuất không?");
                    builder.setCancelable(true);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            Toast.makeText(MainActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(MainActivity.this, "Không đăng xuất", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                drawer.closeDrawers();
                return true;
            }
        });
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, frg).commit();
    }

}