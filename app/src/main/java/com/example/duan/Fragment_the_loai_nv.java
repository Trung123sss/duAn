package com.example.duan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.TheLoaiAdapter;
import com.example.duan.Adapter.TheLoainvAdapter;
import com.example.duan.DAO.TheLoaiDAO;
import com.example.duan.DTO.theLoai;

import java.util.ArrayList;
import java.util.List;

public class Fragment_the_loai_nv extends Fragment {

    ListView lvLoai;
    List<theLoai> list;
    TheLoaiDAO dao;
    TheLoainvAdapter adapter;



    public Fragment_the_loai_nv() {
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
        View v = inflater.inflate(R.layout.fragment_the_loai_nv, container, false);
        lvLoai = v.findViewById(R.id.lvLoainv);
        dao = new TheLoaiDAO(getActivity());
        list = new ArrayList<>();
        capNhatLv();
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<theLoai>) dao.getAll();
        adapter = new TheLoainvAdapter(getActivity(), this, (ArrayList<theLoai>) list);
        lvLoai.setAdapter(adapter);
    }
}
