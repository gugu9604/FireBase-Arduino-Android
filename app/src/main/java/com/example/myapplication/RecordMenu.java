package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.Date;

public class RecordMenu extends AppCompatActivity {
    Toolbar recordToolbar;
    Boolean open = false;
    ConstraintLayout slidingMenuR;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    Button RecordHomebtn,RecordRecordbtn,RecordExercisebtn,RecordFoodbtn;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                if(open == false){
                    open = true;
                    slidingMenuR.setVisibility(View.VISIBLE);
                    slidingMenuR.startAnimation(translateLeftAnim);
                }
                else if(open == true){
                    open = false;
                    slidingMenuR.startAnimation(translateRightAnim);
                }
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordmenu);
        recordToolbar = findViewById(R.id.toolbar3);
        slidingMenuR = findViewById(R.id.slidingMenuF);
        RecordHomebtn = findViewById(R.id.btnHomehome4);
        RecordRecordbtn = findViewById(R.id.btnHomeRecord4);
        RecordExercisebtn = findViewById(R.id.btnHomeExercise4);
        RecordFoodbtn = findViewById(R.id.btnHomeFood4);

        setSupportActionBar(recordToolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_menu_26);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "기록";
        b.setTitle(title);
        recordToolbar.setTitleTextColor(Color.BLACK);

        RecordHomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuR.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(it);
            }
        });
        RecordRecordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuR.startAnimation(translateRightAnim);
            }
        });
        RecordRecordbtn.setBackgroundColor(Color.parseColor("#48b0b0b0"));
        RecordExercisebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuR.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),ExerciseMenu.class);
                startActivity(it);
            }
        });
        RecordFoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuR.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),FoodMenu.class);
                startActivity(it);
            }
        });

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        translateLeftAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        translateRightAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                slidingMenuR.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        MyGridViewCalendar myGridViewCalendar = new MyGridViewCalendar();
        myGridViewCalendar.setSelectedDate(new Date());
        myGridViewCalendar.show(getSupportFragmentManager(), "grid_view_calendar");
    }
}
