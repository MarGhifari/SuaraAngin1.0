package com.komc.suaraanginrev.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.komc.suaraanginrev.R;
import com.komc.suaraanginrev.fragment.BusinessActivity;
import com.komc.suaraanginrev.fragment.EntertainActivity;
import com.komc.suaraanginrev.fragment.HealthActivity;
import com.komc.suaraanginrev.fragment.ScienceActivity;
import com.komc.suaraanginrev.fragment.SportActivity;
import com.komc.suaraanginrev.fragment.TechActivity;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        backButton();

        mainNews();
        businessNews();
        entertainNews();
        healthNews();
        scienceNews();
        sportNews();
        techNews();
    }

    private void businessNews() {
        TextView businessNews = (TextView) findViewById(R.id.business);
        businessNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, BusinessActivity.class);
                startActivity(intent);
            }
        });
    }

    private void techNews() {
        TextView techNews = (TextView) findViewById(R.id.tech);
        techNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, TechActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sportNews() {
        TextView sportNews = (TextView) findViewById(R.id.sport);
        sportNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, SportActivity.class);
                startActivity(intent);
            }
        });
    }

    private void scienceNews() {
        TextView scienceNews = (TextView) findViewById(R.id.science);
        scienceNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, ScienceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void healthNews() {
        TextView healthNews = (TextView) findViewById(R.id.health);
        healthNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, HealthActivity.class);
                startActivity(intent);
            }
        });
    }

    private void entertainNews() {
        TextView entertainNews = (TextView) findViewById(R.id.entertain);
        entertainNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, EntertainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mainNews() {
        TextView mainNews = (TextView) findViewById(R.id.main);
        mainNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FragmentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void backButton() {
        ImageButton fragmentBack = (ImageButton) findViewById(R.id.fragmentBack);
        fragmentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
