package com.example.midexam_networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    Thread th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);



        th = new Thread() {
            @Override
            public void run() {
             //   super.run();

                try {
                    Thread.sleep(1000);
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }


            }

        };

        th.start();
    }
}