package com.example.duan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan.Adapter.MyAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CollectionFragmentPagerDemo extends Fragment {
    MyAdapter adapter;
    ViewPager2 pager2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_collection_frag_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MyAdapter(this);
        pager2 = view.findViewById(R.id.pager_demo);
        pager2.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout01);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, pager2, true, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Xuất ");
                        break;
                    case 1:
                        tab.setText("Tồn");
                        break;
                    case 2:
                        tab.setText("Nhập ");
                        break;

                }
            }
        });

        mediator.attach();

    }
}
