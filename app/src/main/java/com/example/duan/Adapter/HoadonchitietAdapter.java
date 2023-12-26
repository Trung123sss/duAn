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
import com.example.duan.DTO.Hoadonchitiet;
import com.example.duan.DTO.SanPham;
import com.example.duan.R;

import java.util.ArrayList;

public class HoadonchitietAdapter extends ArrayAdapter<Hoadonchitiet> {
    private Context context;
    private ArrayList<Hoadonchitiet> list;
    TextView tvSanPham,tvSoLuong, tvGia;

    public HoadonchitietAdapter(@NonNull Context context, ArrayList<Hoadonchitiet> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_hoa_don_chi_tiet,null);
        }
        final Hoadonchitiet item = list.get(position);
        if (item != null){
            SanPhamDAO sp = new SanPhamDAO(context);
            SanPham sanPham = sp.getID(String.valueOf(item.getMaSP()));
            tvSanPham = v.findViewById(R.id.tvSanPham);
            tvSoLuong = v.findViewById(R.id.tvSoLuong);
            tvGia = v.findViewById(R.id.tvGia);
            tvSanPham.setText(sanPham.getTenSp());
            tvSoLuong.setText("Số lượng: " +item.getSoLuong());
            tvGia.setText(String.valueOf(item.getSoLuong() * sanPham.getGia()));
        }
        return v;
    }
}
