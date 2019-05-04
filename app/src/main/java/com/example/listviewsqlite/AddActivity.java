package com.example.listviewsqlite;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.listviewsqlite.Db.dbAdapter;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private EditText editTask;
    private TextView editDate, editTime;
    private SeekBar reminder;
    private Button addTask;
    private ImageView back;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    private int notificationId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTask = (EditText) findViewById(R.id.etTask);
        editDate = (TextView) findViewById(R.id.tvDate);
        editTime = (TextView) findViewById(R.id.tvTime);
        addTask = (Button) findViewById(R.id.AddBtn);
        back = (ImageView) findViewById(R.id.backBtn);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });


        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, AddActivity.this, year, month, day);
                datePickerDialog.show();

            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddActivity.this, AddActivity.this, hour, minute, DateFormat.is24HourFormat(AddActivity.this));
                timePickerDialog.show();
            }
        });




        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myTask = editTask.getText().toString();
                String myDate = editDate.getText().toString();
                String myTime = editTime.getText().toString();
                save(myTask, myDate, myTime);
                startActivity(new Intent(AddActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        editDate.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        editTime.setText(hourFinal+":"+minuteFinal);

    }

    private void save(String task, String date, String time)
    {
        dbAdapter db=new dbAdapter(this);
        db.openDB();
        boolean saved=db.add(task, date, time);
        //


        if(saved)
        {
            editTask.setText("");
        }else {
            Toast.makeText(this,"Unable To Save",Toast.LENGTH_SHORT).show();
        }
    }


}
