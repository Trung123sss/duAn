package com.example.duan;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.HoadonchitietAdapter;
import com.example.duan.Adapter.HoadonnvAdaper;
import com.example.duan.Adapter.SanPhamnvAdapter;
import com.example.duan.DAO.HoaDonDAO;
import com.example.duan.DAO.HoadonchitietDAO;
import com.example.duan.DAO.SanPhamDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.Hoadonchitiet;
import com.example.duan.DTO.SanPham;

import java.util.ArrayList;
import java.util.List;

public class Frafment_hoa_don_nv extends Fragment {
    ListView lvHoaDon, listView;
    HoaDonDAO hoaDonDAO;
    HoadonnvAdaper adapter;
    Dialog diglogmo;
    HoadonchitietAdapter hoadonchitietAdapter;
    ArrayList<Hoadonchitiet> listHDCT;
    HoadonchitietDAO hoadonchitietDAO;

    List<HoaDon> list;
    public Frafment_hoa_don_nv() {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hoa_don_nv, container, false);
        lvHoaDon = v.findViewById(R.id.lvHoaDonnv);
        hoaDonDAO = new HoaDonDAO(getActivity());
        list = new ArrayList<>();

        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idhd = list.get(i).getMaHD();
                diglogmo = new Dialog(getContext());
                diglogmo.setContentView(R.layout.dialog_hdct);
                listView = diglogmo.findViewById(R.id.lvHoaDons);
                hoadonchitietDAO = new HoadonchitietDAO(getContext());
                capNhatSanPham(idhd);
                diglogmo.show();
            }
        });
        capNhatLv();
        return v;
    }
    void capNhatLv() {
        list = (ArrayList<HoaDon>) hoaDonDAO.getAll();
        adapter = new HoadonnvAdaper(getActivity(), this, list);
        lvHoaDon.setAdapter(adapter);
    }
    void capNhatSanPham(int idhd) {
        listHDCT = (ArrayList<Hoadonchitiet>) hoadonchitietDAO.getID(idhd);
        hoadonchitietAdapter = new HoadonchitietAdapter(getActivity(), listHDCT);
        listView.setAdapter(hoadonchitietAdapter);
    }
}
