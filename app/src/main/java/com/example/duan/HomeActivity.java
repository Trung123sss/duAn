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

import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DTO.ThanhVien;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    NavigationView nv;
    ThanhVienDAO thanhVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawer = findViewById(R.id.drawer_layout1);
        toolbar = findViewById(R.id.toolbar1);
        nv = findViewById(R.id.ncView);
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
        int users = i.getIntExtra("user", 0);
        thanhVienDAO = new ThanhVienDAO(this);
        ThanhVien thanhVien = thanhVienDAO.getID(users);
        if (thanhVien != null) {
            String username = thanhVien.getHoTen();
            tvUser.setText("Welcome " + username + "!");
        } else {
            // Xử lý trường hợp không tìm thấy thông tin người dùng
            tvUser.setText("User not found");
        }

        Fragment_san_pham_nv frsanpham = new Fragment_san_pham_nv();
        replaceFrg(frsanpham);


     nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


         @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.nav_SanPhamh) {
                setTitle("Quản lý sản phẩm");
                replaceFrg(frsanpham);

            } else if (id == R.id.nav_Hoadon) {
                setTitle("Quản lý hóa đơn");
                Frafment_hoa_don_nv frhoadon = new Frafment_hoa_don_nv();
                replaceFrg(frhoadon);

            } else if (id == R.id.nav_TheLoai) {
                setTitle("Quản lý Thể loại");
                Fragment_the_loai_nv frtheloainv = new Fragment_the_loai_nv();
                replaceFrg(frtheloainv);

            } else if (id == R.id.sub_In4) {
                setTitle("Thông tin cá nhân");
                Fragment_Thongtin frtthongtinnv = new Fragment_Thongtin();
                replaceFrg(frtthongtinnv);



            } else if (id == R.id.sub_Logout) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Đăng xuất");
                builder.setMessage("Bạn có muốn đăng xuất không?");
                builder.setCancelable(true);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        Toast.makeText(HomeActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(HomeActivity.this, "Không đăng xuất", Toast.LENGTH_SHORT).show();
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