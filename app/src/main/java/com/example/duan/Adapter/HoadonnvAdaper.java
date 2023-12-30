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

import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DAO.TheLoaiDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.SanPham;
import com.example.duan.DTO.ThanhVien;
import com.example.duan.DTO.theLoai;
import com.example.duan.Frafment_hoa_don_nv;
import com.example.duan.Fragment_san_pham_nv;
import com.example.duan.R;

import java.util.List;

public class HoadonnvAdaper extends ArrayAdapter<HoaDon> {
    private Context context;
    Frafment_hoa_don_nv fragment;
    List<HoaDon> list;

    TextView tvMaHD, tvTenTV, tvLoai, tvNgay;
    ImageView imgDel;

    public HoadonnvAdaper(@NonNull Context context, Frafment_hoa_don_nv fragment, List<HoaDon> list) {
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
            v = inflater.inflate(R.layout.item_hoa_don, null);
        }
        final HoaDon item = list.get(position);
        if (item != null) {
            ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(item.getMaTV());
            tvMaHD = v.findViewById(R.id.tvMaHD);
            tvMaHD.setText("Mã hóa đơn: " + item.getMaHD());
            tvTenTV = v.findViewById(R.id.tvTenthanhvien);
            tvTenTV.setText("Tên người tạo: " + thanhVien.getHoTen());
            tvLoai = v.findViewById(R.id.tvloai);
            String loai = "";
            if (item.getLoai().equals("1")) {
                loai = "Nhập";
            } else {
                loai = "Xuất";
            }
            tvLoai.setText("Loại: " + loai);
            tvNgay = v.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày: " + item.getNgay());

        }
        return v;
    }

}
