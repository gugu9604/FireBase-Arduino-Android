package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MainMenu extends AppCompatActivity{
    boolean isPageOpen = false;
    boolean btn = false;
    ImageButton btn_insert;
    Button btnRecordMenu, btnExerciseMenu, btnFoodMenu,btnHomeMenu;
    TextView UserName,weight_main,height_main,txtExercise,txtBMI,txtCalrorie,txt_iput,txt_inputheight;
    TextView txt0,txt1,txt2,txt3,txt4,txt5,txt6;
    ConstraintLayout slidingMenu,mainmenu;
    public String UserTable;
    public FirebaseAuth mAuth;
    Animation translateLeftAnim,translateRightAnim;
    ActionBar actionbar;
    Toolbar tb;
    float downX,upX;
    SimpleDateFormat day = new SimpleDateFormat("dd");
    long now = System.currentTimeMillis();
    final Date date = new Date(now);
    String getDay = day.format(date);
    String wei,getTime,TAG;
    String[] dayTTime = {"0","0","0","0","0","0","0"};
    String[] dayday={"0","0","0","0","0","0","0"};
    Intent it2;
    ArrayList<Entry> weightdata = new ArrayList<Entry>();
    LineChart chart;
    LineData lineData = new LineData();
    float i=0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                Log.d(TAG, "sussss");
                if(btn == false){
                    btn = true;
                    slidingMenu.setVisibility(View.VISIBLE);
                    slidingMenu.startAnimation(translateLeftAnim);
                }
                else if(btn == true){
                    btn = false;
                    slidingMenu.startAnimation(translateRightAnim);
                }
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        btnHomeMenu = findViewById(R.id.btnHomehome);
        btnRecordMenu = findViewById(R.id.btnHomeRecord);
        btnExerciseMenu = findViewById(R.id.btnHomeExercise);
        weight_main =findViewById(R.id.txt_weight);
        btnFoodMenu = findViewById(R.id.btnHomeFood);
        UserName = findViewById(R.id.UserNameMain);
        mainmenu = findViewById(R.id.MainMenu);
        btn_insert = findViewById(R.id.btn_insert);
        slidingMenu = findViewById(R.id.slidingMenu);
        chart = findViewById(R.id.weightgraph);
        txtExercise = findViewById(R.id.txtExercise);
        txtBMI = findViewById(R.id.txtBMI);
        txtCalrorie = findViewById(R.id.txtCalrorie);
        txt_iput = findViewById(R.id.txt_input);
        txt_inputheight = findViewById(R.id.txt_inputheight);
        txt0 = findViewById(R.id.txt0);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);
        MainThread thread = new MainThread();
        thread.setDaemon(true);
        thread.start();




        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.icons8_menu_26);


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "Easy Health";
        actionbar.setTitle(title);
        tb.setTitleTextColor(Color.BLACK);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        UserName.setText(user.getDisplayName());
        UserTable = user.getDisplayName();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        getTime = sdf.format(date);

        it2 = getIntent();
        if(it2.getStringExtra("weight") != null){
            weight_main.setText(it2.getStringExtra("weight")+"kg");
            Map<String, Object> user1 = new HashMap<>();
            user1.put("몸무게", it2.getStringExtra("weight"));
            db.collection(UserName.getText().toString())
                    .document(getTime).set(user1)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void avoid) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " );
                            weight_main.setText(it2.getStringExtra("weight")+"kg");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawGrape();
            }
        },3000);




        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);


        for(int i = 0;i<7;i++){
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE,-(6-i));
            dayTTime[i] = day.format(calendar.getTime());
            dayday[i] = sdf.format(calendar.getTime());
            Log.d(TAG,Integer.toString(i));
        }



        DocumentReference docRef = db.collection(UserName.getText().toString()).document(getTime);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        wei = document.getString("몸무게");
                        if(it2.getStringExtra("weight") == null){
                            weight_main.setText(wei+"kg");
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        txt_iput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainMenu.this);

                ad.setTitle("BMI 기록 체크!");       // 제목 설정
                ad.setMessage("키를 입력해주세요");   // 내용 설정

                final EditText et = new EditText(MainMenu.this);
                final TextView username = new TextView(MainMenu.this);
                ad.setView(username);
                ad.setView(et);
                mAuth = FirebaseAuth.getInstance();
                final FirebaseUser DBuser = mAuth.getCurrentUser();
                username.setText(DBuser.getDisplayName());
                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                ad.setPositiveButton("완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, "Yes Btn Click");
                        Map<String, Object> user = new HashMap<>();
                        user.put("키", et.getText().toString());
                        db.collection(username.getText().toString())
                                .document("키").set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void avoid) {
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });



                        Toast.makeText(getApplicationContext(),"키입력이 완료되었습니다",Toast.LENGTH_SHORT).show();
                        // Text 값 받아서 로그 남기기
                        String value = et.getText().toString();
                        Log.v(TAG, value);
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG,"No Btn Click");
                        Toast.makeText(getApplicationContext(),"취소됬습니다",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();     //닫기
                        // Event
                    }
                });
                final AlertDialog dialog = ad.create();
                dialog.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });
                ad.show();
            }
        });

        getDatabase(dayday[0],0);
        getDatabase(dayday[1],1);
        getDatabase(dayday[2],2);
        getDatabase(dayday[3],3);
        getDatabase(dayday[4],4);
        getDatabase(dayday[5],5);
        getDatabase(dayday[6],6);


        btnHomeMenu.setBackgroundColor(Color.parseColor("#48b0b0b0"));
        btnHomeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = false;
                slidingMenu.startAnimation(translateRightAnim);
            }
        });
        btnRecordMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = false;
                slidingMenu.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(), RecordMenu.class);
                startActivity(it);
            }
        });
        btnExerciseMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = false;
                slidingMenu.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(), ExerciseMenu.class);
                startActivity(it);
            }
        });
        btnFoodMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = false;
                slidingMenu.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(), FoodMenu.class);
                startActivity(it);
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn = false;
                slidingMenu.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),bluetooth.class);
                startActivity(it);
            }
        });

        translateLeftAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //button1.setText("Close");
                isPageOpen = true;
                actionbar.setHomeAsUpIndicator(R.drawable.icons8_back_24);
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
                slidingMenu.setVisibility(View.INVISIBLE);
                //button1.setText("Open");
                isPageOpen = false;
                actionbar.setHomeAsUpIndicator(R.drawable.icons8_menu_26);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
    public void drawGrape(){
        weightdata.add(new Entry(0,Float.parseFloat(txt0.getText().toString())));
        weightdata.add(new Entry(1,Float.parseFloat(txt1.getText().toString())));
        weightdata.add(new Entry(2,Float.parseFloat(txt2.getText().toString())));
        weightdata.add(new Entry(3,Float.parseFloat(txt3.getText().toString())));
        weightdata.add(new Entry(4,Float.parseFloat(txt4.getText().toString())));
        weightdata.add(new Entry(5,Float.parseFloat(txt5.getText().toString())));
        weightdata.add(new Entry(6,Float.parseFloat(txt6.getText().toString())));
        LineDataSet setData = new LineDataSet(weightdata,"weight");
        setData.setDrawFilled(true);
        setData.setDrawCircles(false);
        setData.setLineWidth(2);
        setData.setColor(Color.parseColor("#8ab3e6"));
        setData.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineData.addDataSet(setData);
        chart.setData(lineData);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new GraphAxisValueFormatter(dayTTime));
        xAxis.setAxisLineColor(Color.parseColor("#f5f5f5"));
        xAxis.setGridColor(Color.parseColor("#f5f5f5"));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(7,true);
        xAxis.setTextColor(Color.parseColor("#636161"));
        chart.setDescription(null);
        chart.setVerticalScrollBarEnabled(false);
        chart.setScaleXEnabled(false);
        chart.setScaleYEnabled(false);
        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        YAxis yAxis1 = chart.getAxisRight();
        yAxis1.setDrawLabels(false);
        yAxis1.setDrawAxisLine(false);
        yAxis1.setDrawGridLines(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisLineColor(Color.parseColor("#f5f5f5"));
        yAxis.setGridColor(Color.parseColor("#f5f5f5"));
        yAxis.setTextColor(Color.parseColor("#636161"));
    }

    class MainThread extends Thread{
        @Override
        public void run() {
            while(true){

                // 메인스레드에 있던 handler겍체를 사용하여
                // Runnable 객체를 보내고 (post)
                handler.post(new Runnable(){
                    @Override
                    public void run() {  // Runnable 의 Run() 메소드에서 UI 접근

                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        DocumentReference docRef = db.collection(UserName.getText().toString()).document(getTime);
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                                        String cal = document.getString("음식-아침-칼로리");
                                        String cal1 = document.getString("음식-점심-칼로리");
                                        String cal2 = document.getString("음식-저녁-칼로리");
                                        if(cal != null){
                                            i += Float.parseFloat(cal);
                                        }
                                        if(cal1 != null){
                                            i += Float.parseFloat(cal1);
                                        }
                                        if(cal2 != null){
                                            i += Float.parseFloat(cal2);
                                        }
                                        txtCalrorie.setText(i+"Cal");

                                    } else {
                                        Log.d(TAG, "No such document");
                                    }
                                } else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                        if(txt_inputheight.getText().toString() != "" && weight_main.getText().toString() != ""){

                            float bmi =Float.parseFloat(weight_main.getText().toString().substring(0,2)) / ((Float.parseFloat(txt_inputheight.getText().toString().substring(0,3))/100) * (Float.parseFloat(txt_inputheight.getText().toString().substring(0,3))/100));
                            String bminum = String.format("%.2f",bmi);
                            if(bmi <= 18.5f){
                                txtBMI.setTextColor(Color.BLUE);
                                txtBMI.setText(bminum+ " 저체중");
                            }else if(bmi <=23.0f){
                                txtBMI.setTextColor(Color.GREEN);
                                txtBMI.setText(bminum+ " 정상");
                            }else if(bmi <= 25f){
                                txtBMI.setTextColor(Color.MAGENTA);
                                txtBMI.setText(bminum + " 과체중");
                            }else{
                                txtBMI.setTextColor(Color.RED);
                                txtBMI.setText(bminum+ " 비만");
                            }

                        }
                        String k = "키";
                        DocumentReference docRef2 = db.collection(UserName.getText().toString()).document(k);
                        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                        String height = document.getString("키");
                                        if(height != null){
                                            txt_inputheight.setText(height+"cm");
                                        }

                                    } else {
                                        Log.d(TAG, "No such document");
                                    }
                                }else{
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });

                    }
                });
                try {
                    Thread.sleep(30000);  // 1초 간격으로
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    Handler handler = new Handler(); // 메인에서 생성한 핸들러


    public void getDatabase(String time, final int i){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(UserName.getText().toString()).document(time);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        wei = document.getString("몸무게");
                        if(i == 0){
                            txt0.setText(wei);
                        }else if(i ==1){
                            txt1.setText(wei);
                        }else if(i ==2){
                            txt2.setText(wei);
                        }else if(i ==3){
                            txt3.setText(wei);
                        }else if(i ==4){
                            txt4.setText(wei);
                        }else if(i ==5){
                            txt5.setText(wei);
                        }
                        Log.d(TAG, String.valueOf(weightdata));
                        if(i == 6){
                            txt6.setText(wei);
                        }

                    } else {
                        Log.d(TAG, "No such document");
                        float k = 0f;
                        if(i == 0){
                            txt0.setText("0");
                        }else if(i ==1){
                            txt1.setText("0");
                        }else if(i ==2){
                            txt2.setText("0");
                        }else if(i ==3){
                            txt3.setText("0");
                        }else if(i ==4){
                            txt4.setText("0");
                        }else if(i ==5){
                            txt5.setText("0");
                        }
                        if(i == 6){
                            txt6.setText("0");
                        }
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public class GraphAxisValueFormatter extends ValueFormatter {
        private String[] mValues;
        // 생성자 초기화
        GraphAxisValueFormatter(String[] values){
            this.mValues = values;

        }
        @Override
        public String getFormattedValue(float value){
            if (mValues.length > (int) value && (int) value >= 0){
                return mValues[(int) value];
            }
            return "";
        }
    }


}


