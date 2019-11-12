package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class DietMode extends AppCompatActivity {

    Toolbar dietToolbar;
    ImageButton dietyoutube,dietset,dietbluetooth,dietroutine1,dietroutine2;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent it = new Intent(getApplicationContext(),ExerciseMenu.class);
                startActivity(it);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_mode);
        dietToolbar = findViewById(R.id.diettoolbar);
        dietyoutube = findViewById(R.id.dietyoutube);
        dietset = findViewById(R.id.dietset);
        dietbluetooth = findViewById(R.id.dietbluetooth);
        dietroutine1 = findViewById(R.id.dietroutine1);
        dietroutine2 = findViewById(R.id.dietroutine2);
        GradientDrawable drawable = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.imageshape);
        dietset.setBackground(drawable);
        dietset.setClipToOutline(true);
        dietroutine1.setBackground(drawable);
        dietroutine1.setClipToOutline(true);
        dietroutine2.setBackground(drawable);
        dietroutine2.setClipToOutline(true);

        setSupportActionBar(dietToolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "다이어트모드";
        b.setTitle(title);
        dietToolbar.setTitleTextColor(Color.BLACK);

        dietyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), dietlist.class);
                it.putExtra("routine","0");
                startActivity(it);
            }
        });
        dietroutine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), dietlist.class);
                it.putExtra("routine","1");
                startActivity(it);
            }
        });
        dietroutine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), dietlist.class);
                it.putExtra("routine","2");
                startActivity(it);
            }
        });
        dietbluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), bluetoothlist.class);
                startActivity(it);
            }
        });
    }
}
