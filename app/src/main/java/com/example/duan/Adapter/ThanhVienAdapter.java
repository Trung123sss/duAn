package com.example.duan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan.DTO.ThanhVien;
import com.example.duan.Fragment_thanh_vien;
import com.example.duan.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    Fragment_thanh_vien fragment;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV, tvNamSinh,tvTaiKhoan, tvMatKhau;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, Fragment_thanh_vien fragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_thanh_vien, null);
        }
        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên: " + item.getMaTV());

            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên: " + item.getHoTen());
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: " + item.getNamSinh());

            tvTaiKhoan = v.findViewById(R.id.tvtaiKhoan);
            tvTaiKhoan.setText("Tài Khoản: "+ item.getTaiKhoan());
            tvMatKhau = v.findViewById(R.id.tvmatKhau);
            tvMatKhau.setText("Mật Khẩu "+ item.getMatKhau());
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaTV()));

            }
        });
        return v;
    }
}
