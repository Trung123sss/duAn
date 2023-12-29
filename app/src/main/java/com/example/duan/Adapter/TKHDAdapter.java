package com.example.duan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan.DAO.HoadonchitietDAO;
import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.Hoadonchitiet;
import com.example.duan.DTO.ThanhVien;
import com.example.duan.Fragment_Xuat;
import com.example.duan.R;

import java.util.ArrayList;

public class TKHDAdapter extends ArrayAdapter<HoaDon> {
    private Context context;
    Fragment_Xuat fragment;
    private ArrayList<HoaDon> list;
    TextView tvMaHD,tvTenTV, tvNgay,tvLoai;

    public TKHDAdapter(@NonNull Context context, Fragment_Xuat fragment, ArrayList<HoaDon>  list) {
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
            v = inflater.inflate(R.layout.item_hoa_don,null);
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
            tvLoai.setText("Loại: " + item.getLoai());
            tvNgay = v.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày: " + item.getNgay());
        }
        return v;
    }
}
