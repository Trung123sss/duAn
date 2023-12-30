package com.example.duan;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.TKHDAdapter;
import com.example.duan.DAO.HoaDonDAO;
import com.example.duan.DTO.HoaDon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Fragment_Xuat extends Fragment {
    ImageView imageView, imageView1, imageView2;
    TKHDAdapter adapter;
    EditText edNgay, eddenngay;
    ListView listView;
    ArrayList<HoaDon> list;
    String loais = "1";
    TextView tvhoadon;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    HoaDonDAO dao;

    int mYear, mMonth, mDay;
    private Calendar selectedDate = Calendar.getInstance();

    public Fragment_Xuat() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_xuattk, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.lvHoaDonv);
        imageView = view.findViewById(R.id.btnTuNgay);
        imageView1 = view.findViewById(R.id.btnTuTuan);
        imageView2 = view.findViewById(R.id.btnTuThang);
        eddenngay = view.findViewById(R.id.edNgayc);
        edNgay = view.findViewById(R.id.edNgayv);
        tvhoadon = view.findViewById(R.id.tvHoaDons);
        dao = new HoaDonDAO(getContext());
        edNgay.setEnabled(false);

        edNgay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvhoadon.setText("Tổng số hóa đơn: " + String.valueOf(dao.getSoHoaDon(edNgay.getText().toString(), loais)));
                capNhatLv();

            }
        });
        eddenngay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                capNhatLvs();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(), 0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();

            }
        });


    }


    DatePickerDialog.OnDateSetListener mDateNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edNgay.setText(sdf.format(c.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            edNgay.setText(sdf.format(c.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
            eddenngay.setText(sdf.format(c.getTime()));
        }
    };

    void capNhatLv() {
        list = (ArrayList<HoaDon>) dao.getBYXUAT(edNgay.getText().toString(), loais);
        adapter = new TKHDAdapter(getActivity(), this, list, null);
        listView.setAdapter(adapter);
    }

    void capNhatLvs() {
        list = (ArrayList<HoaDon>) dao.getBYTrangThai(edNgay.getText().toString(), eddenngay.getText().toString(), loais);
        adapter = new TKHDAdapter(getActivity(), this, list, null);
        listView.setAdapter(adapter);
    }
}