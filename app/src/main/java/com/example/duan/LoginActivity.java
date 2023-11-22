package com.example.duan;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DAO.ThuKhoDAO;
import com.example.duan.DTO.ThanhVien;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin;
    CheckBox chkRememberPass;
    ThuKhoDAO ttdao;
    ThanhVienDAO tvdao;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Đăng nhập");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        ttdao = new ThuKhoDAO(this);
        tvdao = new ThanhVienDAO(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);
        edUserName.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void rememberUser(int id,String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            // xoa trang thai luu truoc do
            edit.clear();
        } else {
            edit.putInt("id",id);
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        // luu lai toan bo du lieu
        edit.commit();
    }
    public void rememberUsers(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            // xoa trang thai luu truoc do
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        // luu lai toan bo du lieu
        edit.commit();
    }


    public void checkLogin() {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.trim().isEmpty() || strPass.trim().isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (ttdao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUsers(strUser, strPass, chkRememberPass.isChecked());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                ThanhVien tvlogin = tvdao.checkLogin(strUser, strPass);
                if (tvlogin != null) {
                    Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(tvlogin.getMaTV(),strUser, strPass, chkRememberPass.isChecked());
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("user", tvdao.checkLogin(strUser, strPass).getMaTV());
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Login thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}