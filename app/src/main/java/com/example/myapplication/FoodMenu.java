package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import at.grabner.circleprogress.CircleProgressView;


public class FoodMenu extends AppCompatActivity {

    Toolbar foodToolbar;
    Boolean open = false;
    ConstraintLayout slidingMenuF;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    Button foodHomebtn,foodRecordbtn,foodExercisebtn,foodFoodbtn;
    ImageButton PerCalrorie,brunch,lunch,dinner,foodbulk,fooddiet,foodnormal;
    TextView brunchtext,lunchtext,dinnertext,maincal,totalcal,recal;
    CircleProgressView progressView;
    GradientDrawable drawable1;
    int i = 1;
    float brunch1,lunch1,dinner1 = 0;
    String check,check1,check2;
    long now = System.currentTimeMillis();
    final Date date = new Date(now);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    final String Time = sdf.format(date);
    public FirebaseAuth mAuth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent it = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(it);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            final FirebaseFirestore db = FirebaseFirestore.getInstance();
            mAuth = FirebaseAuth.getInstance();
            final FirebaseUser user = mAuth.getCurrentUser();
            switch(requestCode){

                case 1:
                    GradientDrawable drawable3 = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.food2shape);
                    brunch.setBackground(drawable3);
                    brunch1 += Float.parseFloat(data.getStringExtra("Cal"));
                    DocumentReference Fooddb1 = db.collection(user.getDisplayName()).document(Time);
                    Fooddb1
                            .update("음식-아침-칼로리", Float.toString(brunch1))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("tag", "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("tag", "Error updating document", e);
                                }
                            });
                    check = data.getStringExtra("Cal");
                    brunchtext.setText(brunch1+"cal");
                    maincal.setText("섭취량:"+(brunch1+lunch1+dinner1));
                    float i = (brunch1+lunch1+dinner1)/2200*100;
                    int total =Integer.parseInt(totalcal.getText().toString())-Integer.parseInt(data.getStringExtra("Cal"));
                    if(total < 0){
                        totalcal.setText(Integer.toString(Math.abs(total)));
                        totalcal.setTextColor(Color.RED);
                        recal.setText("Cal 초과");
                        progressView.setBarColor(Color.parseColor("#f73437"));
                    }
                    totalcal.setText(Integer.toString(total));
                    progressView.setValue(i);
                    break;
                case 2:
                    GradientDrawable drawable4 = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.food2shape);
                    lunch.setBackground(drawable4);
                    lunch1 += Float.parseFloat(data.getStringExtra("Cal"));
                    DocumentReference Fooddb2 = db.collection(user.getDisplayName()).document(Time);
                    Fooddb2
                            .update("음식-점심-칼로리", Float.toString(lunch1))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("tag", "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("tag", "Error updating document", e);
                                }
                            });
                    check1= data.getStringExtra("Cal");
                    lunchtext.setText(lunch1+"cal");
                    maincal.setText("섭취량:"+(brunch1+lunch1+dinner1));
                    float j = (brunch1+lunch1+dinner1)/2200*100;
                    int total1 =Integer.parseInt(totalcal.getText().toString())-Integer.parseInt(data.getStringExtra("Cal"));
                    if(total1 < 0){
                        totalcal.setText(Integer.toString(Math.abs(total1)));
                        totalcal.setTextColor(Color.RED);
                        recal.setText("Cal 초과");
                        progressView.setBarColor(Color.parseColor("#f73437"));
                    }
                    totalcal.setText(Integer.toString(total1));
                    progressView.setValue(j);
                    break;
                case 3:
                    GradientDrawable drawable5 = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.food2shape);
                    dinner.setBackground(drawable5);
                    dinner1 += Float.parseFloat(data.getStringExtra("Cal"));
                    DocumentReference Fooddb3 = db.collection(user.getDisplayName()).document(Time);
                    Fooddb3
                            .update("음식-저녁-칼로리", Float.toString(dinner1))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("tag", "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("tag", "Error updating document", e);
                                }
                            });
                    dinnertext.setText(dinner1+"cal");
                    maincal.setText("섭취량:"+(brunch1+lunch1+dinner1));
                    float k = (brunch1+lunch1+dinner1)/2200*100;
                    int total2 =Integer.parseInt(totalcal.getText().toString())-Integer.parseInt(data.getStringExtra("Cal"));
                    if(total2 < 0){
                        totalcal.setText(Integer.toString(Math.abs(total2)));
                        totalcal.setTextColor(Color.RED);
                        recal.setText("Cal 초과");
                        progressView.setBarColor(Color.parseColor("#f73437"));
                    }
                    totalcal.setText(Integer.toString(total2));
                    progressView.setValue(k);
                    break;

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodmenu);
        foodToolbar = findViewById(R.id.toolbar4);
        foodHomebtn = findViewById(R.id.btnHomehome3);
        foodRecordbtn = findViewById(R.id.btnHomeRecord3);
        foodExercisebtn = findViewById(R.id.btnHomeExercise3);
        foodFoodbtn = findViewById(R.id.btnHomeFood3);
        PerCalrorie = findViewById(R.id.perCalrorie);
        brunch = findViewById(R.id.brunch);
        lunch = findViewById(R.id.lunch);
        dinner = findViewById(R.id.dinner);
        foodbulk = findViewById(R.id.foodbulk);
        fooddiet = findViewById(R.id.fooddiet);
        progressView = findViewById(R.id.circleProgress);
        brunchtext = findViewById(R.id.brunchtext);
        lunchtext = findViewById(R.id.lunchtext);
        dinnertext = findViewById(R.id.dinnertext);
        maincal = findViewById(R.id.txt_cal);
        totalcal = findViewById(R.id.totalcal);
        recal = findViewById(R.id.recal);

        GradientDrawable drawable = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.imageshape);
        drawable1 = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.foodshape);
        PerCalrorie.setBackground(drawable);
        PerCalrorie.setClipToOutline(true);
        foodbulk.setBackground(drawable);
        foodbulk.setClipToOutline(true);
        fooddiet.setBackground(drawable);
        fooddiet.setClipToOutline(true);
        brunch.setBackground(drawable1);
        brunch.setClipToOutline(true);
        lunch.setBackground(drawable1);
        lunch.setClipToOutline(true);
        dinner.setBackground(drawable1);
        dinner.setClipToOutline(true);
        setSupportActionBar(foodToolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_menu_26);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "음식";
        b.setTitle(title);
        foodToolbar.setTitleTextColor(Color.BLACK);


        progressView.setValue(0);
        progressView.setMaxValue(100f);

        FoodThread thread = new FoodThread();
        thread.setDaemon(true);
        thread.start();

        PerCalrorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), PerCalrorielist.class);
                startActivity(it);
            }
        });
        brunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), FoodList.class);
                it.putExtra("time","아침");
                if(brunchtext.getText() != ""){
                    it.putExtra("cal",check);
                }else{
                    it.putExtra("cal","0");
                }
                startActivityForResult(it,1);
            }
        });
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), FoodList.class);
                it.putExtra("time","점심");
                if(lunchtext.getText() != ""){
                    it.putExtra("cal",check1);
                }else{
                    it.putExtra("cal",0);
                }
                startActivityForResult(it,2);
            }
        });
        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), FoodList.class);
                it.putExtra("time","저녁");
                if(dinnertext.getText() != ""){
                    it.putExtra("cal",check2);
                }else{
                    it.putExtra("cal",0);
                }
                startActivityForResult(it,3);
            }
        });


        foodHomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuF.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(it);
            }
        });
        foodRecordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuF.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),RecordMenu.class);
                startActivity(it);
            }
        });
        foodExercisebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuF.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),ExerciseMenu.class);
                startActivity(it);
            }
        });
        foodFoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuF.startAnimation(translateRightAnim);
            }
        });
        foodFoodbtn.setBackgroundColor(Color.parseColor("#48b0b0b0"));
        fooddiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Fooddiet.class);
                startActivity(it);
            }
        });
        foodbulk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), Foodbulk.class);
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
                slidingMenuF.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    class FoodThread extends Thread{
        @Override
        public void run() {
            while(true){
                // 메인스레드에 있던 handler겍체를 사용하여
                // Runnable 객체를 보내고 (post)
                handler.post(new Runnable(){
                    @Override
                    public void run() {  // Runnable 의 Run() 메소드에서 UI 접근

                    }
                });

                try {
                    Thread.sleep(1000);  // 1초 간격으로
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    Handler handler = new Handler(); // 메인에서 생성한 핸들러
}
