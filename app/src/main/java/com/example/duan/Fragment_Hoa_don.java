package com.example.duan;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.HoadonAdapter;
import com.example.duan.Adapter.HoadonchitietAdapter;
import com.example.duan.Adapter.SanPhamSpinner;
import com.example.duan.DAO.HoaDonDAO;
import com.example.duan.DAO.HoadonchitietDAO;
import com.example.duan.DAO.SanPhamDAO;
import com.example.duan.DAO.ThanhVienDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.Hoadonchitiet;
import com.example.duan.DTO.SanPham;
import com.example.duan.DTO.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_Hoa_don extends Fragment {
    ListView lvHoaDon, lvSanPham, listView;
    HoaDonDAO hoaDonDAO;
    List<HoaDon> list;
    HoadonchitietDAO hoadonchitietDAO;
    HoadonAdapter adapter;
    SanPhamSpinner sanPhamSpinner;
    HoaDon item;
    SanPhamDAO sanPhamDAO;

    FloatingActionButton fab;
    Dialog dialog, dialogThem, diglogmo, dialogSL;
    EditText edMaHD, edTenTv, edngay, edSoLuong;
    CheckBox chkXuat, chkNhap;
    Button btnSave, btnThem;
    ImageView btnNgay;
    ThanhVien thanhVien;
    Spinner spSanPham;
    ArrayList<SanPham> listtheLoai;

    HoadonchitietAdapter hoadonchitietAdapter;

    ArrayList<Hoadonchitiet> listHDCT;

    Hoadonchitiet hoadonchitiet;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");


    int maSP;

    public Fragment_Hoa_don(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hoa_don, container, false);
        lvHoaDon = v.findViewById(R.id.lvHoaDon);
        hoaDonDAO = new HoaDonDAO(getActivity());
        sanPhamDAO = new SanPhamDAO();
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0, 0);

            }
        });
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private int count = 0;
            private long lastClickTime = 0;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long currentTime = System.currentTimeMillis();
                int idhd = list.get(position).getMaHD();
                if (currentTime - lastClickTime < 500) {
                    item = list.get(position);
                    openDialog(getActivity(), 1, idhd);
                    count = 0;
                } else {
                    count++;
                    if (count == 1) {
                        item = list.get(position);
                        openDialogs(getActivity(), 1, idhd);
                    }
                }
                lastClickTime = currentTime;
            }
        });

        return v;
    }


    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hoaDonDAO.delete(Id);
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

    protected void openDialogs(final Context context, final int type, int idhd) {
        diglogmo = new Dialog(context);
        diglogmo.setContentView(R.layout.dialog_hdct);
        listView = diglogmo.findViewById(R.id.lvHoaDons);
        hoadonchitietDAO = new HoadonchitietDAO(context);
        capNhatSanPham(idhd);
        diglogmo.show();
    }

    protected void openDialog(final Context context, final int type, int idhd) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_hoa_don);
        edMaHD = dialog.findViewById(R.id.tvMaHDa);
        edTenTv = dialog.findViewById(R.id.tvTenTVa);
        edngay = dialog.findViewById(R.id.edTuNgays);
        chkNhap = dialog.findViewById(R.id.checkNhap);
        chkXuat = dialog.findViewById(R.id.checkXuat);
        btnSave = dialog.findViewById(R.id.btnLuu);
        btnNgay = dialog.findViewById(R.id.btnTuNgays);
        lvSanPham = dialog.findViewById(R.id.lvListSanPham);
        edngay = dialog.findViewById(R.id.edTuNgays);
        btnThem = dialog.findViewById(R.id.btnthem);
        hoadonchitietDAO = new HoadonchitietDAO(context);

        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hoadonchitiet objhoadonchitiet = listHDCT.get(i);
                openDialogUpdateSL(context, objhoadonchitiet);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogThem(getContext());

            }
        });
        listHDCT = new ArrayList<>();
        btnNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date to the "edngay" EditText
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, month, dayOfMonth);
                                edngay.setText(sdf.format(selectedDate.getTime()));
                            }
                        }, year, month, dayOfMonth);

                datePickerDialog.show();

            }
        });
        edMaHD.setEnabled(false);
        if (type != 0) {
            edMaHD.setText(String.valueOf(item.getMaHD()));
            edngay.setText(item.getNgay());
            if (item.getLoai().equals("0")) {
                chkNhap.setChecked(true);
            } else if (item.getLoai().equals("1")) {
                chkXuat.setChecked(true);
            }
            hoadonchitietDAO = new HoadonchitietDAO(context);
            listHDCT = (ArrayList<Hoadonchitiet>) hoadonchitietDAO.getID(idhd);
            hoadonchitietAdapter = new HoadonchitietAdapter(getActivity(), listHDCT);
            lvSanPham.setAdapter(hoadonchitietAdapter);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate() > 0) {
                    item = new HoaDon();
                    ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
                    SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
                    int idUser = pref.getInt("id", 0);
                    ThanhVien loggedInUser = thanhVienDAO.getID(idUser);
                    if (loggedInUser != null) {
                        item.setMaTV(idUser);
                    }
                    item.setNgay(edngay.getText().toString());
                    if (chkNhap.isChecked()) {
                        item.setLoai("0");
                    } else if (chkXuat.isChecked()) {
                        item.setLoai("1");
                    }
                    if (type == 0) {
                        if (hoaDonDAO.insert(item) > 0) {
                            int idNew = hoaDonDAO.idNew();
                            for (Hoadonchitiet hdct : listHDCT) {
                                String maSP = String.valueOf(hdct.getMaSP());
                                SanPham sanPham = sanPhamDAO.getID(maSP);
                                if (chkNhap.isChecked()) {
                                    sanPham.setSoLuong(sanPham.getSoLuong() + hdct.getSoLuong());
                                } else if (chkXuat.isChecked()) {
                                    if (sanPham.getSoLuong() < hdct.getSoLuong()) {
                                        Toast.makeText(context, "Kho không đủ hảng", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        sanPham.setSoLuong(sanPham.getSoLuong() - hdct.getSoLuong());
                                    }
                                }
                                sanPhamDAO.updateSL(sanPham);
                                hdct.setID(idNew);
                                hoadonchitietDAO.insert(hdct);
                            }
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            String title = "Thêm thành công";
                            String message = "Bạn đã thêm hóa đơn" + item.getLoai() + " thành công !!!";
                            int id = item.getMaHD();
                            Notify createNotification = new Notify();
                            createNotification.postNotification(context, title, message, id);
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaHD(Integer.parseInt(edMaHD.getText().toString()));
                        if (hoaDonDAO.update(item) > 0) {

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

    protected void openDialogUpdateSL(final Context context, Hoadonchitiet objHoaDonChiTiet) {
        dialogSL = new Dialog(context);
        dialogSL.setContentView(R.layout.dialog_so_luong);
        EditText edSoLuong = dialogSL.findViewById(R.id.edSoLuong);
        Button btnThemMonAn = dialogSL.findViewById(R.id.btnThem);
        Button btnHuyMonAn = dialogSL.findViewById(R.id.btnHuy);
        btnHuyMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSL.dismiss();
            }
        });
        btnThemMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edSoLuong.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Bạn chưa nhập số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int gia = Integer.parseInt(edSoLuong.getText().toString());
                    if (gia < 0) {
                        Toast.makeText(context, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Số lượng phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                objHoaDonChiTiet.setSoLuong(Integer.parseInt(edSoLuong.getText().toString()));

                capNhatLvSanPham();
                dialogSL.dismiss();
            }
        });
        dialogSL.show();
    }

    protected void opendialogThem(final Context context) {
        dialogThem = new Dialog(context);
        dialogThem.setContentView(R.layout.dialog_hoadontiet);
        spSanPham = dialogThem.findViewById(R.id.spLoais);
        edSoLuong = dialogThem.findViewById(R.id.edsoLuongs);
        Button btnThem = dialogThem.findViewById(R.id.btnSaveS);
        Button btnHuy = dialogThem.findViewById(R.id.btnCancelS);
        listtheLoai = new ArrayList<>(); // Initialize the ArrayList
        sanPhamDAO = new SanPhamDAO(context);
        listtheLoai = (ArrayList<SanPham>) sanPhamDAO.getAll();
        sanPhamSpinner = new SanPhamSpinner(context, listtheLoai);
        spSanPham.setAdapter(sanPhamSpinner);
        // lay maLoai
        spSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSP = listtheLoai.get(position).getMaSP();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogThem.dismiss();

            }
        });


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maSP = ((SanPham) spSanPham.getSelectedItem()).getMaSP();
                String soLuongStr = edSoLuong.getText().toString();
                if (soLuongStr.isEmpty()) {
                    Toast.makeText(context, "Bạn chưa nhập số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int gia = Integer.parseInt(soLuongStr.toString());
                    if (gia < 0) {
                        Toast.makeText(context, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "Số lượng phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                int soLuong = Integer.parseInt(soLuongStr);
                boolean isProductExists = false;
                for (Hoadonchitiet item : listHDCT) {
                    if (item.getMaSP() == maSP) {
                        isProductExists = true;
                        item.setSoLuong(item.getSoLuong() + soLuong);
                        break;
                    }
                }
                if (!isProductExists) {
                    Hoadonchitiet objHoaDonChiTiet = new Hoadonchitiet();
                    objHoaDonChiTiet.setMaSP(maSP);
                    objHoaDonChiTiet.setSoLuong(soLuong);
                    listHDCT.add(objHoaDonChiTiet);
                }
                capNhatLvSanPham();
                dialogThem.dismiss();
            }
        });
        dialogThem.show();
    }

    public int validate() {
        int check = 1;
        if (edngay.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    void capNhatLvSanPham() {
        hoadonchitietAdapter = new HoadonchitietAdapter(getActivity(), listHDCT);
        lvSanPham.setAdapter(hoadonchitietAdapter);
    }

    void capNhatSanPham(int idhd) {
        listHDCT = (ArrayList<Hoadonchitiet>) hoadonchitietDAO.getID(idhd);
        hoadonchitietAdapter = new HoadonchitietAdapter(getActivity(), listHDCT);
        listView.setAdapter(hoadonchitietAdapter);
    }

    void capNhatLv() {
        list = hoaDonDAO.getAll();
        adapter = new HoadonAdapter(getActivity(), this, list);
        lvHoaDon.setAdapter(adapter);
    }
}