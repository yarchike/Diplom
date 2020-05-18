package com.example.diplom;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
    String outputPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Initialization();
        hideAndShowBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        setTitle("Настройки");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hideAndShowBtn:
                if (newPinText.getInputType() == InputType.TYPE_CLASS_NUMBER) {
                    newPinText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    hideAndShowBtn.setImageResource(R.drawable.ic_eye);
                } else {
                    newPinText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    hideAndShowBtn.setImageResource(R.drawable.ic_no_eye);
                }
                break;
            case R.id.saveBtn:
                if (newPinText.getText().toString().length() == 4) {
                    try {
                        App.getPinCodeRepository().saveNew(newPinText.getText().toString());
                    } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), getText(R.string.not_enough_characters_for_pin_code), Toast.LENGTH_LONG);
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
