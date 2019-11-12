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

public class BulkMode extends AppCompatActivity {


    Toolbar bulkToolbar;
    ImageButton bulkyoutube,bulkset,bulkroutine1,bulkroutine2,bulkbluetooth;
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
        setContentView(R.layout.bulk_mode);
        bulkyoutube = findViewById(R.id.bulkyoutube);
        bulkToolbar = findViewById(R.id.bulktoolbar);
        bulkroutine1 = findViewById(R.id.bulkroutine1);
        bulkroutine2 = findViewById(R.id.bulkroutine2);
        bulkset = findViewById(R.id.bulkset);
        bulkbluetooth = findViewById(R.id.bulkbluetooth);

        GradientDrawable drawable = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.imageshape);
        bulkset.setBackground(drawable);
        bulkset.setClipToOutline(true);
        bulkroutine1.setBackground(drawable);
        bulkroutine1.setClipToOutline(true);
        bulkroutine2.setBackground(drawable);
        bulkroutine2.setClipToOutline(true);

        setSupportActionBar(bulkToolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "벌크업모드";
        b.setTitle(title);
        bulkToolbar.setTitleTextColor(Color.BLACK);

        bulkyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), bulklist.class);
                it.putExtra("routine","0");
                startActivity(it);
            }
        });
        bulkroutine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), bulklist.class);
                it.putExtra("routine","1");
                startActivity(it);
            }
        });
        bulkroutine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), bulklist.class);
                it.putExtra("routine","2");
                startActivity(it);
            }
        });
        bulkbluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), bluetoothlist.class);
                startActivity(it);
            }
        });
    }
}
