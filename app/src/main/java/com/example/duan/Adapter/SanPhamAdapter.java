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

import com.example.duan.DAO.SanPhamDAO;
import com.example.duan.DAO.TheLoaiDAO;
import com.example.duan.DTO.SanPham;
import com.example.duan.DTO.theLoai;
import com.example.duan.Fragment_San_pham;
import com.example.duan.R;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    Fragment_San_pham fragment;
    List<SanPham> list;

    TextView tvMaSP, tvTenSP, tvGiaThue, tvLoai, tvsoLuong, tvmoTa;
    ImageView imgDel;

    public SanPhamAdapter(@NonNull Context context, Fragment_San_pham fragment, List<SanPham> list) {
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
            v = inflater.inflate(R.layout.item_san_pham, null);
        }
        final SanPham item = list.get(position);
        if (item != null) {
            TheLoaiDAO loaiDAO = new TheLoaiDAO(context);
            theLoai loai = loaiDAO.getID(String.valueOf(item.getMaTT()));
            tvMaSP = v.findViewById(R.id.tvMaSP);
            tvMaSP.setText("Mã Sản Phẩm: " + item.getMaSP());
            tvTenSP = v.findViewById(R.id.tvTenSanPham);
            tvTenSP.setText("Tên sản phẩm: " + item.getTenSp());
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá : " + item.getGia());
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại sản phẩm: " + loai.getTenSanPham());
            tvsoLuong = v.findViewById(R.id.tvsoLuong);
            tvsoLuong.setText("Số lượng: "+item.getSoLuong());
            tvmoTa = v.findViewById(R.id.tvmoTa);
            tvmoTa.setText("Mô Tả: "+item.getMoTa());
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
                fragment.xoa(String.valueOf(item.getMaSP()));


            }
        });
        return v;
    }


}
