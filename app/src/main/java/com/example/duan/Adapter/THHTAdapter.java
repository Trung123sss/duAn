package com.example.duan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan.DAO.SanPhamDAO;
import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.SanPham;
import com.example.duan.DTO.ThanhVien;
import com.example.duan.DTO.theLoai;
import com.example.duan.Fragment_Nhap;
import com.example.duan.Fragment_Ton;
import com.example.duan.Fragment_Xuat;
import com.example.duan.R;

import java.util.ArrayList;

public class THHTAdapter extends ArrayAdapter<theLoai> {
    private Context context;
    private ArrayList<theLoai> list;
    TextView tvTen, tvSL;
    Fragment_Ton fragment;
    public THHTAdapter(@NonNull Context context, Fragment_Ton fragment, ArrayList<theLoai> list ) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;

    }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = convertView;
            if(v == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.item_ton,null);
            }
            final theLoai item = list.get(position);
            if (item != null) {
                SanPhamDAO  sanPhamDAO = new SanPhamDAO(context);
                SanPham sanPham = sanPhamDAO.getID(String.valueOf(item.getMaTT()));
                tvTen = v.findViewById(R.id.tvTenLoaiTon);
                tvTen.setText("Tên :"+ item.getTenSanPham());
                tvSL= v.findViewById(R.id.tvsoLuongTon);
                tvSL.setText("Số lượng"+ sanPham.getSoLuong());
            }
            return v;
        }
    }

