package com.example.diplom;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Date;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ListAddActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar_save;
    private EditText headlineText;
    private EditText bodyText;
    private EditText dateText;
    private ImageButton сalButton;
    private CheckBox checkDeadline;
    DBHelper dbHelper;
    Calendar dateAndTime = Calendar.getInstance();
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_add);
        Initialization();
        setSupportActionBar(toolbar_save);

        position = -1;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Bundle extras = this.getIntent().getExtras();

        if (extras != null) {
            checkDeadline.setChecked(true);
            dateText.setVisibility(VISIBLE);
            сalButton.setVisibility(VISIBLE);
            position = extras.getInt("position");
            headlineText.setText(App.getNotesRepository().getNotes().get(position).getHeading());
            bodyText.setText(App.getNotesRepository().getNotes().get(position).getBody());
            dateText.setText(DateUtil.DateToString(App.getNotesRepository().getNotes().get(position).getDate()));
            setTitle(getText(R.string.editing_note));
        } else {
            setTitle(getText(R.string.new_note));
        }
        сalButton.setOnClickListener(this);
        checkDeadline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    dateText.setVisibility(VISIBLE);
                    сalButton.setVisibility(VISIBLE);
                } else {
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
        switch (item.getItemId()) {
            case R.id.action_save:
                String headline = headlineText.getText().toString();
                String body = bodyText.getText().toString();
                Date date = DateUtil.StringToDate(dateText.getText().toString());
                if (position == -1) {
                    App.getNotesRepository().setNotes(new Note(headline, body, date, App.getNotesRepository().getNotes().size()));
                } else {
                    App.getNotesRepository().removeNotes(position);
                    App.getNotesRepository().setNotes(new Note(headline, body, date, App.getNotesRepository().getNotes().size()));
                }

                Intent intent = new Intent(ListAddActivity.this, ListActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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


    private void setInitialDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy  HH:mm");
        dateText.setText(dateFormat.format(dateAndTime.getTimeInMillis()));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            dateAndTime.set(Calendar.HOUR_OF_DAY, 23);
            dateAndTime.set(Calendar.MINUTE, 59);
            setInitialDateTime();
        }
    };

}
