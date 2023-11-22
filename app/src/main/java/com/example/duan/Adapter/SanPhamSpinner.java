package com.example.duan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan.DTO.SanPham;
import com.example.duan.Fragment_San_pham;
import com.example.duan.R;

import java.util.ArrayList;


public class SanPhamSpinner extends ArrayAdapter<SanPham> {
    private Context context;
    Fragment_San_pham fragment;
    private ArrayList<SanPham> list;
    TextView tvMaSP, tvTenSP;


    public SanPhamSpinner(@NonNull Context context, ArrayList<SanPham> list) {
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
            v = inflater.inflate(R.layout.item_san_pham_spiner, null);

        }
        final SanPham item = list.get(position);
        if (item != null) {
            tvMaSP = v.findViewById(R.id.tvMaSP);
            tvMaSP.setText(item.getMaSP() + ". ");
            tvTenSP = v.findViewById(R.id.tvSp);
            tvTenSP.setText(item.getTenSp());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_san_pham_spiner, null);

        }
        final SanPham item = list.get(position);
        if (item != null) {
            tvMaSP = v.findViewById(R.id.tvMaSP);
            tvMaSP.setText(item.getMaSP() + ". ");
            tvTenSP = v.findViewById(R.id.tvSp);
            tvTenSP.setText(item.getTenSp());
        }
        return v;
    }
}
