package com.example.duan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.HoadonnvAdaper;
import com.example.duan.Adapter.SanPhamAdapter;
import com.example.duan.Adapter.SanPhamnvAdapter;
import com.example.duan.Adapter.THHTAdapter;
import com.example.duan.DAO.SanPhamDAO;
import com.example.duan.DAO.TheLoaiDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.SanPham;
import com.example.duan.DTO.theLoai;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Ton extends Fragment {
    ListView listView;


    SanPhamDAO sanPhamDAO;
    SanPhamnvAdapter adapters;

    List<SanPham> list;


    SanPhamAdapter sanPhamAdapter;
    public Fragment_Ton() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_tontk,container,false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.lvHang);

        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();
        capNhatLv();
        return ;

    }
    void capNhatLv() {
        list = (ArrayList<SanPham>) sanPhamDAO.getAlls();
        adapters = new SanPhamnvAdapter(getActivity(), null, list, this);
        listView.setAdapter(adapters);
    }
}
