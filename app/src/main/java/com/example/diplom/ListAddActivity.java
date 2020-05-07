package com.example.diplom;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.diplom.DBHelper.LOG_TAG;

public class ListAddActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar_save;
    DatePickerDialog datePickerDialog;
    private EditText headlineText;
    private EditText bodyText;
    private EditText dateText;
    private ImageButton сalButton;
    private CheckBox checkDeadline;
    DBHelper dbHelper;
    Calendar dateAndTime=Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);
        Initialization();
        setSupportActionBar(toolbar_save);
        сalButton.setOnClickListener(this);
        checkDeadline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    dateText.setVisibility(VISIBLE);
                    сalButton.setVisibility(VISIBLE);
                }else{
                    dateText.setVisibility(GONE);
                    сalButton.setVisibility(GONE);
                }
            }
        });



    }

    private void Initialization() {
        toolbar_save = findViewById(R.id.toolbar_save);
        headlineText = findViewById(R.id.headlineText);
        bodyText = findViewById(R.id.bodyText);
        dateText = findViewById(R.id.dateText);
        сalButton = findViewById(R.id.calendarBtn);
        checkDeadline = findViewById(R.id.checkDeadline);
        dbHelper = new DBHelper(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (item.getItemId()) {
            case R.id.action_save:
                cv.put("heading", headlineText.getText().toString());
                cv.put("body", bodyText.getText().toString());
                cv.put("date", dateText.getText().toString());
                cv.put("idList", ListActivity.simpleAdapterContent.size());
                long rowID = db.insert("mytable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                Intent intent = new Intent(ListAddActivity.this, ListActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calendarBtn:
                setDate(view);
                break;

        }

    }
    public void setDate(View v) {
        new DatePickerDialog(ListAddActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }
   /* public void setTime(View v) {
        new TimePickerDialog(ListAddActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }*/

    private void setInitialDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy  HH:mm");
        dateText.setText(dateFormat.format(dateAndTime.getTimeInMillis()));
    }
   /* TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, 0);
            dateAndTime.set(Calendar.MINUTE, 0);
            setInitialDateTime();
        }
    };*/
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateAndTime.set(Calendar.HOUR_OF_DAY, 0);
            dateAndTime.set(Calendar.MINUTE, 0);
            setInitialDateTime();
        }
    };

}
