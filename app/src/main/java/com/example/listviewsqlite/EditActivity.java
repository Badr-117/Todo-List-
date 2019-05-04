package com.example.listviewsqlite;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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

public class EditActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private EditText editTask2;
    private TextView editDate2, editTime2;
    private Button editMyTask;
    private ImageView back2;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        editTask2 = (EditText) findViewById(R.id.etTask2);
        editDate2 = (TextView) findViewById(R.id.tvDate2);
        editTime2 = (TextView) findViewById(R.id.tvTime2);
        editMyTask = (Button) findViewById(R.id.AddBtn2);
        back2 = (ImageView) findViewById(R.id.backBtn2);


        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });


        editDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this, EditActivity.this, year, month, day);
                datePickerDialog.show();

            }
        });

        editTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditActivity.this, EditActivity.this, hour, minute, DateFormat.is24HourFormat(EditActivity.this));
                timePickerDialog.show();
            }
        });


        editMyTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myTask = editTask2.getText().toString();
                String myDate = editDate2.getText().toString();
                String myTime = editTime2.getText().toString();
                update(myTask, myDate, myTime);
                startActivity(new Intent(EditActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month + 1;
        dayFinal = dayOfMonth;

        editDate2.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        editTime2.setText(hourFinal+":"+minuteFinal);

    }

    private void update(String newTask, String newDate, String newTime ) {

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int id = extras.getInt("itemID");

        dbAdapter db = new dbAdapter(this);
        db.openDB();
        boolean updated = db.update(newTask, newDate, newTime, id);
        db.closeDB();

        if (updated) {
            editTask2.setText(newTask);

        } else {
            Toast.makeText(this, "Unable To Update", Toast.LENGTH_SHORT).show();
        }
    }

}
