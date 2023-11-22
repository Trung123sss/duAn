package com.example.duan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import com.example.duan.Adapter.SanPhamnvAdapter;
import com.example.duan.DAO.SanPhamDAO;
import com.example.duan.DTO.SanPham;

import java.util.ArrayList;
import java.util.List;

public class Fragment_san_pham_nv extends Fragment {
    ListView lvSanPham;
    SanPhamDAO sanPhamDAO;
    SanPhamnvAdapter adapter;

    List<SanPham> list;

    public Fragment_san_pham_nv() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_san_pham_nv, container, false);
        lvSanPham = v.findViewById(R.id.lvSanPhamnv);

        sanPhamDAO = new SanPhamDAO(getActivity());
        list = new ArrayList<>();
        capNhatLv();
        return v;
    }
    void capNhatLv() {
        list = (ArrayList<SanPham>) sanPhamDAO.getAll();
        adapter = new SanPhamnvAdapter(getActivity(), this, list);
        lvSanPham.setAdapter(adapter);
    }
}
