package com.share4happy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_time, btn_date;
    TextView txt_date, txt_time;

    // Lấy thời gian hiện tại
    Calendar calendar = Calendar.getInstance();
    // Format cách hiện thị ngày/ tháng/ năm
    SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");
    // Format hiện thị giờ (24h)
    SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        // Tạo sự kiện chọn ngày/tháng/năm
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo callback để quay lại
                DatePickerDialog.OnDateSetListener callback2 = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int M, int d) {
                        // Thiết lập ngày cho calender
                        calendar.set(Calendar.YEAR,y);
                        calendar.set(Calendar.MONTH,M);
                        calendar.set(Calendar.DATE,d);
                        //Hiện thị lên textView
                        txt_date.setText(sdfD.format(calendar.getTime()));
                    }
                };
                // hiện thị hộp thoại chọn ngày tháng năm
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        callback2,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                //Cho phép hiện thị dialog để người dùng thay đổi thông tin
                datePickerDialog.show();
            }
        });
        //Tạo sự kiện chọn giờ
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Biến lưu giữ sự kiện khi thời gian được thiết lập
                // Tạo callback để quay lại
                TimePickerDialog.OnTimeSetListener calback = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY,h);
                        calendar.set(Calendar.MINUTE,m);
                        //Hiện thị thời gian sau khi thiết lập
                        txt_time.setText(sdfT.format(calendar.getTime()));
                    }
                };
                // Hiện thị hộp thoại để chọn thời gian
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        calback,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),true
                );
                timePickerDialog.show();
            }
        });
    }

    private void addControls() {
        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);
        txt_date = findViewById(R.id.txt_date);
        txt_time = findViewById(R.id.txr_time);

        // Hiện thị thời gian hiện tại
        txt_date.setText(sdfD.format(calendar.getTime()));
        txt_time.setText(sdfT.format(calendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tử vi - Phong thủy");
        return super.onCreateOptionsMenu(menu);
    }
}