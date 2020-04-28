package com.example.diplom;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageViewPin0;
    ImageView imageViewPin1;
    ImageView imageViewPin2;
    ImageView imageViewPin3;
    Button one_btn;
    Button two_btn;
    Button three_btn;
    Button four_btn;
    Button five_btn;
    Button six_btn;
    Button seven_btn;
    Button eight_btn;
    Button nine_btn;
    Button zero_btn;
    Button del_btn;



    int keyImgPin = 0;
    ImageView[] imgkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        switch (v.getId()) {
            case R.id.one_btn:
                fill();
                break;
            case R.id.two_btn:
                fill();
                break;
            case R.id.three_btn:
                fill();
                break;
            case R.id.four_btn:
                fill();
                break;
            case R.id.five_btn:
                fill();
                break;
            case R.id.six_btn:
                fill();
                break;
            case R.id.seven_btn:
                fill();
                break;
            case R.id.eight_btn:
                fill();
                break;
            case R.id.nine_btn:
                fill();
                break;
            case R.id.zero_btn:
                fill();
                break;
            case R.id.del_btn:
                clearFill();
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
    private void Initialization(){
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
}
