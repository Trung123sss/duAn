package com.example.duan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.SanPhamAdapter;
import com.example.duan.Adapter.TheLoaiSpinner;
import com.example.duan.DAO.SanPhamDAO;
import com.example.duan.DAO.TheLoaiDAO;
import com.example.duan.DTO.SanPham;
import com.example.duan.DTO.theLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment_San_pham extends Fragment {
    ListView lvSanPham;
    SanPhamDAO sanPhamDAO;
    SanPhamAdapter adapter;
    SanPham item;
    List<SanPham> list;


    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSP, edTenSanPham, edGiaThue, edSoLuong, edMota;
    Spinner spinner;
    Button btnSave, btnCancel;

    TheLoaiSpinner spinnerAdapter;
    ArrayList<theLoai> listtheLoai;
    TheLoaiDAO theLoaiDAO;
    theLoai theLoai;
    int maTT, position;

    private SearchView searchView;


    public Fragment_San_pham() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
//tìm kiếm
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.drawer_view2, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handleSearch(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_san_pham, container, false);
        lvSanPham = v.findViewById(R.id.lvSanPham);

        sanPhamDAO = new SanPhamDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    void capNhatLv() {
        list = (ArrayList<SanPham>) sanPhamDAO.getAll();
        adapter = new SanPhamAdapter(getActivity(), this, list);
        lvSanPham.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sanPhamDAO.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xóa", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_san_pham);
        edMaSP = dialog.findViewById(R.id.edMaSanPham);
        edTenSanPham = dialog.findViewById(R.id.edTenSanPham);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoai);
        edSoLuong = dialog.findViewById(R.id.edsoLuong);
        edMota = dialog.findViewById(R.id.edmoTa);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        theLoaiDAO = new TheLoaiDAO(context);
        listtheLoai = (ArrayList<theLoai>) theLoaiDAO.getAll();
        spinnerAdapter = new TheLoaiSpinner(context, listtheLoai);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maTT = listtheLoai.get(position).getMaTT();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // kiem tra tupe insert hay update
        edMaSP.setEnabled(false);
        if (type != 0) {
            edMaSP.setText(String.valueOf(item.getMaSP()));
            edTenSanPham.setText(item.getTenSp());
            edGiaThue.setText(String.valueOf(item.getGia()));
            edSoLuong.setText(String.valueOf(item.getSoLuong()));
            edMota.setText(String.valueOf(item.getMoTa()));
            for (int i = 0; i < listtheLoai.size(); i++)
                if (item.getMaTT() == (listtheLoai.get(i).getMaTT())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);

            spinner.setSelection(position);        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new SanPham();
                item.setTenSp(edTenSanPham.getText().toString());
                item.setGia(parseInt(edGiaThue.getText().toString(), 0));
                item.setMaTT(maTT);
                item.setSoLuong(parseInt(edSoLuong.getText().toString(), 0));
                item.setMoTa(edMota.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (sanPhamDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaSP(Integer.parseInt(edMaSP.getText().toString()));
                        if (sanPhamDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenSanPham.getText().length() == 0 || edGiaThue.getText().length() == 0 || edSoLuong.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

//tìm kiến
    private void handleSearch(String query) {
        List<SanPham> listSearch = new ArrayList<>();
        for (SanPham sach : list) {
            if (sach.getTenSp().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(sach);
            }
        }
        adapter = new SanPhamAdapter(getActivity(), this, listSearch);
        lvSanPham.setAdapter(adapter);

    }

    // Sắp xếp sách theo tên tăng dần
    private void sortBooksByNameAscending() {
        Collections.sort(list, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sach1, SanPham sach2) {
                return sach1.getTenSp().compareTo(sach2.getTenSp());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sach1, SanPham sach2) {
                return sach2.getTenSp().compareTo(sach1.getTenSp());
//                return sach2.getGiaThue() - sach1.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.asc){
            sortBooksByNameAscending();
            return true;
        }else if(id == R.id.desc){
            sortBooksByNameDescending();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
