package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageViewPin0;
    private ImageView imageViewPin1;
    private ImageView imageViewPin2;
    private ImageView imageViewPin3;
    private Button one_btn;
    private Button two_btn;
    private Button three_btn;
    private Button four_btn;
    private Button five_btn;
    private Button six_btn;
    private Button seven_btn;
    private Button eight_btn;
    private Button nine_btn;
    private Button zero_btn;
    private Button del_btn;

    String testPin;

    final static String PIN_KEY = "PIN";
    private String pinCode;
    private String inputPin = "";

    private int keyImgPin = 0;
    private ImageView[] imgkey;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("Pin", MODE_PRIVATE);
        Bundle arguments = getIntent().getExtras();
        if(arguments != null){

            sharedPref.edit().putString(PIN_KEY, arguments.get(PIN_KEY).toString()).commit();
            Log.d("Mylog", arguments.get(PIN_KEY) + "получение");
        }





        pinCode = sharedPref.getString(PIN_KEY, "");
        Log.d("Mylog",  pinCode + "получение пин");
        checkForAvailability();
        Initialization();
        one_btn.setOnClickListener(this);
        two_btn.setOnClickListener(this);
        three_btn.setOnClickListener(this);
        four_btn.setOnClickListener(this);
        five_btn.setOnClickListener(this);
        six_btn.setOnClickListener(this);
        seven_btn.setOnClickListener(this);
        eight_btn.setOnClickListener(this);
        nine_btn.setOnClickListener(this);
        zero_btn.setOnClickListener(this);

        del_btn.setOnClickListener(this);

        setDrawable();
    }

    private void setDrawable() {
        imageViewPin0.setImageResource(R.drawable.shape);
        imageViewPin1.setImageResource(R.drawable.shape);
        imageViewPin2.setImageResource(R.drawable.shape);
        imageViewPin3.setImageResource(R.drawable.shape);


    }


    @Override
    public void onClick(View v) {
        if (inputPin.length() < 4) {
            switch (v.getId()) {

                case R.id.one_btn:
                    fill();
                    inputPin += "1";
                    break;
                case R.id.two_btn:
                    fill();
                    inputPin += "2";
                    break;
                case R.id.three_btn:
                    inputPin += "3";
                    fill();
                    break;
                case R.id.four_btn:
                    inputPin += "4";
                    fill();
                    break;
                case R.id.five_btn:
                    inputPin += "5";
                    fill();
                    break;
                case R.id.six_btn:
                    inputPin += "6";
                    fill();
                    break;
                case R.id.seven_btn:
                    inputPin += "7";
                    fill();
                    break;
                case R.id.eight_btn:
                    inputPin += "8";
                    fill();
                    break;
                case R.id.nine_btn:
                    inputPin += "9";
                    fill();
                    break;
                case R.id.zero_btn:
                    inputPin += "0";
                    fill();
                    break;
            }
            if (inputPin.length() == 4) {
                try {
                    if (pinCode.equals(getHash(inputPin))) {
                        Intent intent = new Intent(MainActivity.this, ListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Неверный пин", Toast.LENGTH_LONG);
                        toast.show();
                        inputPin = "";
                        for (int i = 0; i < 4; i++) {
                            clearFill();
                        }
                    }

                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Вы уже ввели четры цыфры", Toast.LENGTH_LONG);
            toast.show();
        }
        switch (v.getId()) {
            case R.id.del_btn:
                clearFill();
                deleteLastCharacter();
                Toast toast = Toast.makeText(getApplicationContext(), inputPin, Toast.LENGTH_LONG);
                toast.show();

                break;

        }

    }

    private void fill() {
        if (keyImgPin < 4) {
            imgkey[keyImgPin].setColorFilter(Color.argb(255, 32, 47, 255));
            keyImgPin++;
        }
    }

    private void clearFill() {
        keyImgPin--;
        if (keyImgPin < 0) {
            keyImgPin = 0;
        }
        imgkey[keyImgPin].setColorFilter(null);
        imgkey[keyImgPin].setImageResource(R.drawable.shape);
    }

    private void Initialization() {
        imageViewPin0 = findViewById(R.id.imageViewPin0);
        imageViewPin1 = findViewById(R.id.imageViewPin1);
        imageViewPin2 = findViewById(R.id.imageViewPin2);
        imageViewPin3 = findViewById(R.id.imageViewPin3);
        imgkey = new ImageView[]{imageViewPin0, imageViewPin1, imageViewPin2, imageViewPin3};
        one_btn = findViewById(R.id.one_btn);
        two_btn = findViewById(R.id.two_btn);
        three_btn = findViewById(R.id.three_btn);
        four_btn = findViewById(R.id.four_btn);
        five_btn = findViewById(R.id.five_btn);
        six_btn = findViewById(R.id.six_btn);
        seven_btn = findViewById(R.id.seven_btn);
        eight_btn = findViewById(R.id.eight_btn);
        nine_btn = findViewById(R.id.nine_btn);
        zero_btn = findViewById(R.id.zero_btn);
        del_btn = findViewById(R.id.del_btn);
    }

    private void checkForAvailability() {

        if (pinCode.equals("")) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

    }

    public static String getHash(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(str.getBytes("utf-8"));
        StringBuilder s2 = new StringBuilder(new BigInteger(1, m.digest()).toString(16));
        while (s2.length() < 32) {
            s2.insert(0, "0");
        }
        return s2.toString();
    }

    private void deleteLastCharacter() {
        if (inputPin.length() > 0) {
            inputPin = inputPin.substring(0, (inputPin.length() - 1));
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Нету введеных цифр", Toast.LENGTH_LONG);
            toast.show();
        }
    }


}
