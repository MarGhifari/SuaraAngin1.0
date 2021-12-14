package com.komc.suaraanginrev.slider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.komc.suaraanginrev.R;

public class SplashActivity extends AppCompatActivity {
    private int waktu_loading=4000;

    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(SplashActivity.this, SliderActivity.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }
}
