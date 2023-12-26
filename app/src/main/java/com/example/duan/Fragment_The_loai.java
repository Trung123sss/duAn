package com.example.duan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.TheLoaiAdapter;
import com.example.duan.DAO.TheLoaiDAO;
import com.example.duan.DTO.theLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_The_loai extends Fragment {
    ListView lvLoai;
    ArrayList<theLoai> list;
    static TheLoaiDAO dao;
    TheLoaiAdapter adapter;
    theLoai item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoai, edTenLoai;
    Button btnSave, btnCancel;
    SearchView searchView;

    public Fragment_The_loai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_the_loai, container, false);
        lvLoai = v.findViewById(R.id.lvLoai);
        fab = v.findViewById(R.id.fab);
        searchView = v.findViewById(R.id.search_View);
        dao = new TheLoaiDAO(getActivity());
        capNhatLv();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                seach(newText);
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);

            }
        });

        lvLoai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = (ArrayList<theLoai>) dao.getAll();
        adapter = new TheLoaiAdapter(getActivity(), this, list);
        lvLoai.setAdapter(adapter);
    }

    void seach(String str) {
        list = (ArrayList<theLoai>) dao.getAllName(str);
        adapter = new TheLoaiAdapter(getActivity(), this, list);
        lvLoai.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
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
        dialog.setContentView(R.layout.dialog_theloai);
        edMaLoai = dialog.findViewById(R.id.edMaLoai);
        edTenLoai = dialog.findViewById(R.id.edTenLoai);
        btnCancel = dialog.findViewById(R.id.btnCancelLS);
        btnSave = dialog.findViewById(R.id.btnSaveLS);

        edMaLoai.setEnabled(false);
        if (type != 0) {
            edMaLoai.setText(String.valueOf(item.getMaTT()));
            edTenLoai.setText(item.getTenSanPham());

        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new theLoai();
                item.setTenSanPham(edTenLoai.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaTT(Integer.parseInt(edMaLoai.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
        if (edTenLoai.getText().length() == 0 ) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;

    }
}
