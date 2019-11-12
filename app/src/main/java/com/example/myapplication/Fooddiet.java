package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class Fooddiet extends AppCompatActivity {
    private Toolbar diettoobar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fooddiet);

        diettoobar1 = findViewById(R.id.rec_diet_toolbar);
        setSupportActionBar(diettoobar1);
        ActionBar dtb = getSupportActionBar();
        dtb.setDisplayHomeAsUpEnabled(true);
        dtb.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String rec_diet_1 = "다이어트 추천 식단";
        dtb.setTitle(rec_diet_1);
        diettoobar1.setTitleTextColor(Color.BLACK);
    }
}
