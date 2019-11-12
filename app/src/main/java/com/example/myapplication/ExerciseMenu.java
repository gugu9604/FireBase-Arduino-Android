package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class ExerciseMenu extends AppCompatActivity implements SensorEventListener {
    Boolean open = false;
    Toolbar toolbar;
    TextView walk,userN,test;
    ConstraintLayout slidingMenuE;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    ImageButton bulkupbtn,dietbtn,normalbtn;
    Button ExerciseHomebtn,ExerciseRecordbtn,ExerciseExercisebtn,ExerciseFoodbtn;
    public static int cnt = 0;
    public  static  float a;
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;
    long now = System.currentTimeMillis();
    private static final int SHAKE_THRESHOLD = 850;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;
    private SensorManager sensorManager;
    private Sensor WalkSensor;
    public FirebaseAuth mAuth;
    public static float calroriecnt = 0;
    public  static  float kmcnt =0;
    float b =0;
    int p =0;


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
                    slidingMenuE.setVisibility(View.VISIBLE);
                    slidingMenuE.startAnimation(translateLeftAnim);
                }
                else if(open == true){
                    open = false;
                    slidingMenuE.startAnimation(translateRightAnim);
                }
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercisemenu);
        slidingMenuE = findViewById(R.id.slidingMenuF);
        toolbar = findViewById(R.id.toolbar2);
        bulkupbtn = findViewById(R.id.imageBulkUpBtn);
        dietbtn = findViewById(R.id.imageDietBtn);
        normalbtn = findViewById(R.id.imageNormalBtn);
        walk = findViewById(R.id.textWalk);
        ExerciseHomebtn = findViewById(R.id.btnHomehome2);
        ExerciseExercisebtn = findViewById(R.id.btnHomeExercise2);
        ExerciseRecordbtn = findViewById(R.id.btnHomeRecord2);
        ExerciseFoodbtn = findViewById(R.id.btnHomeFood2);
        userN = findViewById(R.id.userName);
        test = findViewById(R.id.test);
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        final Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String Time = sdf.format(date);
        Intent it = getIntent();


        GradientDrawable drawable = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.imageshape);
        bulkupbtn.setBackground(drawable);
        bulkupbtn.setClipToOutline(true);
        dietbtn.setBackground(drawable);
        dietbtn.setClipToOutline(true);
        normalbtn.setBackground(drawable);
        normalbtn.setClipToOutline(true);
        BackThread thread = new BackThread();

        thread.setDaemon(true);
        thread.start();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        WalkSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        a = (float) (3.7103 + (0.2678*70) + (0.0359*(70*60*0.0006)*2)*70);


        double dumbel=0;

        if(it.getStringExtra("set") != null){

            String a = it.getStringExtra("set");
            int aa = Integer.parseInt(a.substring(1));
           // test.setText(a.substring(1));

            b = (float) (aa*0.2);


            DocumentReference docRef2 = db.collection(user.getDisplayName()).document(Time);
            docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            String h = document.getString("운동-덤벨-칼로리");
                            b += Float.parseFloat(h);
                            final String k = String.format("%.2f",b);
                            DocumentReference healthdb = db.collection(user.getDisplayName()).document(Time);
                            healthdb
                                    .update("운동-덤벨-칼로리", k)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d(TAG, "DocumentSnapshot successfully updated!");
                                            test.setText(k+"Cal");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error updating document", e);
                                        }
                                    });
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    }else{
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });



        }






        setSupportActionBar(toolbar);

        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_menu_26);

        String title = "운동";
        b.setTitle(title);
        toolbar.setTitleTextColor(Color.BLACK);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        DocumentReference docRef1 = db.collection(user.getDisplayName()).document(Time);
        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String wa = document.getString("건강-발걸음");
                        walk.setText(wa);
                        p = Integer.parseInt(wa);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                }else{
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        DocumentReference docRef2 = db.collection(user.getDisplayName()).document(Time);
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        String dumbel = document.getString("운동-덤벨-칼로리");
                        if( dumbel != null){
                            test.setText(dumbel+"cal");
                        }
                        String km = document.getString("건강-이동거리");
                        if(km != null){
                            kmcnt += Float.parseFloat(km);
                        }
                        String lk = document.getString("건강-칼로리");
                        if(lk != null){
                            calroriecnt += Float.parseFloat(lk);
                        }

                    } else {
                        Log.d(TAG, "No such document");
                    }
                }else{
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        bulkupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplication(),BulkMode.class);
                startActivity(it);
            }
        });
        dietbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(),DietMode.class);
                startActivity(it);
            }
        });
        normalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(),NormalMode.class);
                startActivity(it);
            }
        });

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        ExerciseHomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuE.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),MainMenu.class);
                startActivity(it);
            }
        });
        ExerciseRecordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuE.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(),RecordMenu.class);
                startActivity(it);
            }
        });
        ExerciseExercisebtn.setBackgroundColor(Color.parseColor("#48b0b0b0"));
        ExerciseExercisebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuE.startAnimation(translateRightAnim);
            }
        });
        ExerciseFoodbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open = false;
                slidingMenuE.startAnimation(translateRightAnim);
                Intent it = new Intent(getApplicationContext(), FoodMenu.class);
                startActivity(it);
            }
        });



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
                slidingMenuE.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void startService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", cnt);
        ContextCompat.startForegroundService(this,serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }

    class BackThread extends Thread{
        @Override
        public void run() {
            while(true){
                // 메인스레드에 있던 handler겍체를 사용하여
                // Runnable 객체를 보내고 (post)
                handler.post(new Runnable(){
                    @Override
                    public void run() {  // Runnable 의 Run() 메소드에서 UI 접근
                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                        mAuth = FirebaseAuth.getInstance();
                        final FirebaseUser user = mAuth.getCurrentUser();
                        final Date date = new Date(now);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String Time = sdf.format(date);
                        kmcnt += (70*cnt)/100;
                        calroriecnt += (float) (kmcnt*a*0.0006);
                    }
                });
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                        mAuth = FirebaseAuth.getInstance();
                        final FirebaseUser user = mAuth.getCurrentUser();
                        final Date date = new Date(now);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        final String Time = sdf.format(date);
                        DocumentReference healthdb = db.collection(user.getDisplayName()).document(Time);
                        healthdb
                                .update("건강-발걸음",walk.getText().toString())
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
                                    }
                                });
                        healthdb
                                .update("건강-칼로리", String.format("%.2f",Math.floor(calroriecnt)))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
                                    }
                                });

                        healthdb
                                .update("건강-이동거리",String.format("%.1f",Math.floor(kmcnt)))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
                                    }
                                });
                    }
                },5000);
                try {
                    Thread.sleep(5000);  // 1초 간격으로
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    Handler handler = new Handler(); // 메인에서 생성한 핸들러

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 200) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;
                if (speed > SHAKE_THRESHOLD) {
                    ++cnt;
                    int pp = p+cnt;
                    walk.setText(String.format("%d",pp));
                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    public void onStart() {
        super.onStart();
        if (WalkSensor != null)
            sensorManager.registerListener(this, WalkSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }
}
