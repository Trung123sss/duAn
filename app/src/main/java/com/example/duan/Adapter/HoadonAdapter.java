package com.example.duan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.duan.Adapter.HoadonAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.SanPham;
import com.example.duan.DTO.ThanhVien;
import com.example.duan.Frafment_hoa_don_nv;
import com.example.duan.Fragment_Hoa_don;
import com.example.duan.Fragment_San_pham;
import com.example.duan.R;

import java.util.List;

public class HoadonAdapter extends ArrayAdapter<HoaDon> {
    private Context context;
    Fragment_Hoa_don fragment;
    List<HoaDon> list;
    TextView tvMaHD, tvTenTV, tvLoai, tvNgay;
    ImageView imgDel;

    public HoadonAdapter(@NonNull Context context, Fragment_Hoa_don fragment, List<HoaDon> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_hoa_don, parent, false);
        }

        final HoaDon item = list.get(position);
        if (item != null) {
            ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getAD(item.getMaTV());
            tvMaHD = v.findViewById(R.id.tvMaHD);
            tvMaHD.setText("Mã hóa đơn: " + item.getMaHD());
            tvTenTV = v.findViewById(R.id.tvTenthanhvien);
            if (thanhVien != null) {
                tvTenTV.setText("Tên người tạo: " + thanhVien.getHoTen());
            } else {
                // Xử lý khi thanhVien là null
            }
            tvLoai = v.findViewById(R.id.tvloai);
            tvLoai.setText("Loại: " + item.getLoai());
            tvNgay = v.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày: " + item.getNgay());
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform delete operation
                fragment.xoa(String.valueOf(item.getMaHD()));
            }
        });


        return v;
    }
}