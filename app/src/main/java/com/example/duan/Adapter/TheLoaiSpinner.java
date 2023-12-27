package com.example.duan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan.DTO.theLoai;
import com.example.duan.R;

import java.util.ArrayList;

public class TheLoaiSpinner extends ArrayAdapter<theLoai> {
    private Context context;
    ArrayList<theLoai> list;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    public TheLoaiSpinner(@NonNull Context context, ArrayList<theLoai> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_spiner, null);

        }
        final theLoai item = list.get(position);
        if (item != null) {
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSP);
            tvMaLoaiSach.setText(item.getMaTT() + ". ");
            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSp);
            tvTenLoaiSach.setText(item.getTenSanPham());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_spiner, null);

        }
        final theLoai item = list.get(position);
        if (item != null) {
            tvMaLoaiSach = v.findViewById(R.id.tvMaLoaiSP);
            tvMaLoaiSach.setText(item.getMaTT() + ". ");

            tvTenLoaiSach = v.findViewById(R.id.tvTenLoaiSp);
            tvTenLoaiSach.setText(item.getTenSanPham());
        }
        return v;
    }
}
