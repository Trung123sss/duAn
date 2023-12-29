package com.example.duan;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan.Adapter.HoadonchitietAdapter;
import com.example.duan.Adapter.HoadonnvAdaper;
import com.example.duan.Adapter.TKHDAdapter;
import com.example.duan.DAO.HoaDonDAO;
import com.example.duan.DAO.HoadonchitietDAO;
import com.example.duan.DTO.HoaDon;
import com.example.duan.DTO.Hoadonchitiet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Fragment_Xuat extends Fragment {
    ImageView imageView;
    TKHDAdapter adapter;
    EditText edNgay;
    ListView listView;
    ArrayList<HoaDon> list;
    TextView tvhoadon;
    String loaia = "Nhập";
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
                tvhoadon.setText("Tổng số hóa đơn: " + String.valueOf(dao.getSoHoaDon(edNgay.getText().toString())));
                capNhatLv();
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

    void capNhatLv() {
        String loais = loaia ;
        list = (ArrayList<HoaDon>) dao.getBYXUAT(edNgay.getText().toString(), "1");
        Log.d("Dates", edNgay.getText().toString());
        adapter = new TKHDAdapter(getActivity(), this, list);
        listView.setAdapter(adapter);
    }
}