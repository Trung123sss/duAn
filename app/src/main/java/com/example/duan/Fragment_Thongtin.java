package com.example.duan;

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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_thong_tin, container, false);
        edTenTV = v.findViewById(R.id.edTenTVs);
        edNamSinh = v.findViewById(R.id.edNamSinhs);
        edTaiKhoan = v.findViewById(R.id.edTaiKhoans);
        edMatKhau = v.findViewById(R.id.edMatKhaus);
        dao = new ThanhVienDAO(getActivity());
        list = new ArrayList<>();
        // Lấy đối tượng ThanhVien từ danh sách list hoặc từ nguồn dữ liệu khác

        capNhatLv();
        return v;
    }
    void capNhatLv() {
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(), new Fragment_thanh_vien(), (ArrayList<ThanhVien>) list);
        // Sử dụng vòng lặp for để lấy từng đối tượng ThanhVien từ danh sách list
        for (int i = 0; i < list.size(); i++) {
            ThanhVien thanhVien = list.get(i);

            // Gán dữ liệu từ đối tượng ThanhVien vào các EditText tương ứng
            edTenTV.setText("Tên thành viên: " +thanhVien.getHoTen());
            edNamSinh.setText(String.valueOf("Năm sinh: " + thanhVien.getNamSinh()));
            edTaiKhoan.setText("Tài Khoản: "+thanhVien.getTaiKhoan());
            edMatKhau.setText("Mật Khẩu "+thanhVien.getMatKhau());
        }
    }

}
