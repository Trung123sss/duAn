package com.example.duan;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.ThanhVienAdapter;
import com.example.duan.Adapter.TheLoainvAdapter;
import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DAO.TheLoaiDAO;
import com.example.duan.DTO.ThanhVien;
import com.example.duan.DTO.theLoai;

import java.util.ArrayList;

public class Fragment_Thongtin extends Fragment {
    ArrayList<ThanhVien> list;
    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    TextView edTenTV, edNamSinh,edTaiKhoan, edMatKhau;

    public Fragment_Thongtin() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thong_tin, container, false);
        edTenTV = v.findViewById(R.id.edTenTVs);
        edNamSinh = v.findViewById(R.id.edNamSinhs);
        edTaiKhoan = v.findViewById(R.id.edTaiKhoans);
        edMatKhau = v.findViewById(R.id.edMatKhaus);
        dao = new ThanhVienDAO(getActivity());
        list = new ArrayList<>();
        capNhatLv();
        return v;
    }
    void capNhatLv() {
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getActivity());
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        int idUser = pref.getInt("id", 0);
        ThanhVien loggedInUser = thanhVienDAO.getID(idUser);
        adapter = new ThanhVienAdapter(getActivity(), new Fragment_thanh_vien(), (ArrayList<ThanhVien>) list);
            edTenTV.setText("Tên thành viên: " +loggedInUser.getHoTen());
            edNamSinh.setText(String.valueOf("Năm sinh: " + loggedInUser.getNamSinh()));
            edTaiKhoan.setText("Tài Khoản: "+loggedInUser.getTaiKhoan());
            edMatKhau.setText("Mật Khẩu "+loggedInUser.getMatKhau());
        }
    }


