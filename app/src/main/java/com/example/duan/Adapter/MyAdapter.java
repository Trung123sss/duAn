package com.example.duan.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan.Fragment_Nhap;
import com.example.duan.Fragment_Ton;
import com.example.duan.Fragment_Xuat;


public class MyAdapter extends FragmentStateAdapter {
    int soLuongPager = 3;
    public MyAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_Xuat();

            case 1:
                return new Fragment_Ton();
            default:
                return new Fragment_Nhap();

        }
    }

    @Override
    public int getItemCount() {
        return soLuongPager;
    }
}
