package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FoodDetail extends AppCompatActivity {
    Toolbar foodtoolbar1;
    private int img;
    ImageView foodimg;
    ImageView foodimg1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent it = new Intent(getApplicationContext(),PerCalrorielist.class);
                startActivity(it);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fooddetail);


        foodtoolbar1 = findViewById(R.id.foodtoolbar1);
        setSupportActionBar(foodtoolbar1);
        ActionBar food1 = getSupportActionBar();
        food1.setDisplayHomeAsUpEnabled(true);
        food1.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String foodtitle1 = "음식별 칼로리";
        food1.setTitle(foodtitle1);
        foodtoolbar1.setTitleTextColor(Color.BLACK);
        


        Intent intent  = getIntent();

        ImageView profile=(ImageView)findViewById(R.id.img_prof);
        TextView info = (TextView)findViewById(R.id.tv_info);
        TextView phone = (TextView)findViewById(R.id.tv_pn);
        foodimg = findViewById(R.id.img_prof2);
        foodimg1=findViewById(R.id.img_prof3);
        img=Integer.parseInt(intent.getStringExtra("profile"));
        profile.setImageResource(img);
        info.setText(intent.getStringExtra("info"));
        phone.setText(intent.getStringExtra("phone"));

        if(intent.getStringExtra("info").equals("싸이버거")){
            foodimg.setImageResource(R.drawable.a);
            foodimg1.setImageResource(R.drawable.a_2);
        }
        else if(intent.getStringExtra("info").equals("치즈버거")){
            foodimg.setImageResource(R.drawable.b);
            foodimg1.setImageResource(R.drawable.b_2);
        }
        else if(intent.getStringExtra("info").equals("프렌치 프라이")){
            foodimg.setImageResource(R.drawable.c);
            foodimg1.setImageResource(R.drawable.c_2);
        }
        else if(intent.getStringExtra("info").equals("후라이드 치킨(100g)")){
            foodimg.setImageResource(R.drawable.d);
            foodimg1.setImageResource(R.drawable.d_2);
        }
        else if(intent.getStringExtra("info").equals("군만두")){
            foodimg.setImageResource(R.drawable.e);
            foodimg1.setImageResource(R.drawable.e_2);
        }
        else if(intent.getStringExtra("info").equals("짜장면")){
            foodimg.setImageResource(R.drawable.f);
            foodimg1.setImageResource(R.drawable.f_2);
        }
        else if(intent.getStringExtra("info").equals("짬뽕")){
            foodimg.setImageResource(R.drawable.g);
            foodimg1.setImageResource(R.drawable.g_2);
        }
        else if(intent.getStringExtra("info").equals("탕수육(1인분)")){
            foodimg.setImageResource(R.drawable.h);
            foodimg1.setImageResource(R.drawable.h_2);
        }
        else if(intent.getStringExtra("info").equals("볶음밥")){
            foodimg.setImageResource(R.drawable.i);
            foodimg1.setImageResource(R.drawable.i_2);
        }
        else if(intent.getStringExtra("info").equals("귤(1개)")){
            foodimg.setImageResource(R.drawable.j);
            foodimg1.setImageResource(R.drawable.j_2);
        }
        else if(intent.getStringExtra("info").equals("딸기(50g)")){
            foodimg.setImageResource(R.drawable.k);
            foodimg1.setImageResource(R.drawable.k_2);
        }
        else if(intent.getStringExtra("info").equals("바나나(120g)")){
            foodimg.setImageResource(R.drawable.l);
            foodimg1.setImageResource(R.drawable.l_2);
        }
        else if(intent.getStringExtra("info").equals("사과(200g)")){
            foodimg.setImageResource(R.drawable.m);
            foodimg1.setImageResource(R.drawable.m_2);
        }
        else if(intent.getStringExtra("info").equals("라면(1인분)")){
            foodimg.setImageResource(R.drawable.n);
            foodimg1.setImageResource(R.drawable.n_2);
        }
        else if(intent.getStringExtra("info").equals("우동(1인분)")){
            foodimg.setImageResource(R.drawable.o);
            foodimg1.setImageResource(R.drawable.o_2);
        }
        else if(intent.getStringExtra("info").equals("떡볶이")){
            foodimg.setImageResource(R.drawable.p);
            foodimg1.setImageResource(R.drawable.p_2);
        }
        else if(intent.getStringExtra("info").equals("어묵")){
            foodimg.setImageResource(R.drawable.q);
            foodimg1.setImageResource(R.drawable.q_2);
        }
        else if(intent.getStringExtra("info").equals("메로나")){
            foodimg.setImageResource(R.drawable.r);
            foodimg1.setImageResource(R.drawable.r_2);
        }
        else if(intent.getStringExtra("info").equals("베라(230g)")){
            foodimg.setImageResource(R.drawable.s);
            foodimg1.setImageResource(R.drawable.s_2);
        }
        else if(intent.getStringExtra("info").equals("샌드위치")){
            foodimg.setImageResource(R.drawable.t);
            foodimg1.setImageResource(R.drawable.t_2);
        }
        else if(intent.getStringExtra("info").equals("치즈핫도그")){
            foodimg.setImageResource(R.drawable.u);
            foodimg1.setImageResource(R.drawable.u_2);
        }
        else if(intent.getStringExtra("info").equals("스테이크")){
            foodimg.setImageResource(R.drawable.v);
            foodimg1.setImageResource(R.drawable.v_2);
        }
        else if(intent.getStringExtra("info").equals("돈까스")){
            foodimg.setImageResource(R.drawable.w);
            foodimg1.setImageResource(R.drawable.w_2);
        }
        else if(intent.getStringExtra("info").equals("스파게티")){
            foodimg.setImageResource(R.drawable.y);
            foodimg1.setImageResource(R.drawable.y_2);
        }
        else if(intent.getStringExtra("info").equals("피자(r)")){
            foodimg.setImageResource(R.drawable.z);
            foodimg1.setImageResource(R.drawable.z_2);
        }
        else if(intent.getStringExtra("info").equals("오믈렛")){
            foodimg.setImageResource(R.drawable.ab);
            foodimg1.setImageResource(R.drawable.ab_2);
        }
        else if(intent.getStringExtra("info").equals("갈비탕")){
            foodimg.setImageResource(R.drawable.ac);
            foodimg1.setImageResource(R.drawable.ac_2);
        }
        else if(intent.getStringExtra("info").equals("김치찌개")){
            foodimg.setImageResource(R.drawable.ad);
            foodimg1.setImageResource(R.drawable.ad_2);
        }
        else if(intent.getStringExtra("info").equals("삼계탕")){
            foodimg.setImageResource(R.drawable.ae);
            foodimg1.setImageResource(R.drawable.ae_2);
        }
        else if(intent.getStringExtra("info").equals("아메리카노")){
            foodimg.setImageResource(R.drawable.af);
            foodimg1.setImageResource(R.drawable.af_2);
        }
        else if(intent.getStringExtra("info").equals("콜라(250ml)")){
            foodimg.setImageResource(R.drawable.ag);
            foodimg1.setImageResource(R.drawable.ag_2);
        } else if(intent.getStringExtra("info").equals("사이다(250ml)")){
            foodimg.setImageResource(R.drawable.ah);
            foodimg1.setImageResource(R.drawable.ah_2);
        }else if(intent.getStringExtra("info").equals("우유(100g)")){
            foodimg.setImageResource(R.drawable.ai);
            foodimg1.setImageResource(R.drawable.ai_2);
        }




    }
}
