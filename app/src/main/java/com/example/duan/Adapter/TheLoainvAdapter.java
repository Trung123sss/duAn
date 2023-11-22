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
import com.example.duan.Fragment_the_loai_nv;
import com.example.duan.R;

import java.util.ArrayList;

public class TheLoainvAdapter extends ArrayAdapter<theLoai> {
    private Context context;
    Fragment_the_loai_nv fragment;
    private ArrayList<theLoai> list;
    TextView tvMaLoai, tvTenLoai;

    public TheLoainvAdapter(@NonNull Context context, Fragment_the_loai_nv fragment, ArrayList<theLoai> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai, null);
        }
        final theLoai item = list.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Mã Loại: " + item.getMaTT());
            tvTenLoai = v.findViewById(R.id.tvTenLoai);
            tvTenLoai.setText("Tên Loại: " + item.getTenSanPham());
        }
        return v;
    }
}
