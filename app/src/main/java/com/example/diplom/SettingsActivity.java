package com.example.diplom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText newPinText;
    private ImageButton hideAndShowBtn;
    private Button saveBtn;
    SharedPreferences sharedPref;
    String outputPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPref = getSharedPreferences("Pin", MODE_PRIVATE);
        Initialization();
        hideAndShowBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hideAndShowBtn:
                if (newPinText.getInputType() == InputType.TYPE_CLASS_NUMBER) {
                    newPinText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                } else {
                    newPinText.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                break;
            case R.id.saveBtn:
                if(newPinText.getText().toString().length() == 4){
                    try {
                        outputPin = MainActivity.getHash(newPinText.getText().toString());
                    } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    intent.putExtra(MainActivity.PIN_KEY, outputPin);
                    startActivity(intent);
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Не достаточно символов для пин кода", Toast.LENGTH_LONG);
                    toast.show();
                }




        }
    }

    private void Initialization() {
        newPinText = findViewById(R.id.newPinText);
        hideAndShowBtn = findViewById(R.id.hideAndShowBtn);
        saveBtn = findViewById(R.id.saveBtn);
    }
}
