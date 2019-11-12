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

public class NormalMode extends AppCompatActivity {

    Toolbar normalToolbar;
    ImageButton normalyoutube,normalset,normalbluetooth,normalroutine1,normalroutine2;
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
        setContentView(R.layout.normal_mode);
        normalToolbar = findViewById(R.id.nomaltoolbar);
        normalyoutube = findViewById(R.id.nomalyoutube);
        normalset = findViewById(R.id.nomalset);
        normalbluetooth = findViewById(R.id.normalbluetooth);
        normalroutine1 = findViewById(R.id.nomalroutine1);
        normalroutine2 = findViewById(R.id.nomalroutine2);
        GradientDrawable drawable = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.imageshape);
        normalset.setBackground(drawable);
        normalset.setClipToOutline(true);
        normalroutine1.setBackground(drawable);
        normalroutine1.setClipToOutline(true);
        normalroutine2.setBackground(drawable);
        normalroutine2.setClipToOutline(true);

        setSupportActionBar(normalToolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "노말모드";
        b.setTitle(title);
        normalToolbar.setTitleTextColor(Color.BLACK);

        normalyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), normallist.class);
                it.putExtra("routine","0");
                startActivity(it);
            }
        });
        normalroutine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), normallist.class);
                it.putExtra("routine","1");
                startActivity(it);
            }
        });
        normalroutine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), normallist.class);
                it.putExtra("routine","2");
                startActivity(it);
            }
        });
        normalbluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), bluetoothlist.class);
                startActivity(it);
            }
        });
    }
}
