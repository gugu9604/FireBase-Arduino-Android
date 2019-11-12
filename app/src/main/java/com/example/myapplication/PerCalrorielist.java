package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class PerCalrorielist extends AppCompatActivity implements View.OnClickListener {
    Toolbar foodtoolbar;
    private ArrayList<FriendsItem> data = null;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent it = new Intent(getApplicationContext(),FoodMenu.class);
                startActivity(it);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.percalrorielist);

        foodtoolbar = findViewById(R.id.percalrorietoolbar);
        setSupportActionBar(foodtoolbar);
        ActionBar food = getSupportActionBar();
        food.setDisplayHomeAsUpEnabled(true);
        food.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String foodtitle = "음식별 칼로리";
        food.setTitle(foodtitle);
        foodtoolbar.setTitleTextColor(Color.BLACK);



        ListView listView = (ListView)findViewById(R.id.percalrorielist);

        data = new ArrayList<>();
        FriendsItem friends1 = new FriendsItem(R.drawable.burger1, "싸이버거", "956");
        FriendsItem friends2 = new FriendsItem(R.drawable.burger2, "치즈버거", "389");
        FriendsItem friends3 = new FriendsItem(R.drawable.fries, "프렌치 프라이", "270");
        FriendsItem friends5 = new FriendsItem(R.drawable.chi, "후라이드 치킨(100g)", "289");
        FriendsItem friends6 = new FriendsItem(R.drawable.mandu, "군만두", "630");
        FriendsItem friends7 = new FriendsItem(R.drawable.jjajang, "짜장면", "660");
        FriendsItem friends8 = new FriendsItem(R.drawable.jjambbong, "짬뽕", "540");
        FriendsItem friends9 = new FriendsItem(R.drawable.ttang, "탕수육(1인분)", "616");
        FriendsItem friends10 = new FriendsItem(R.drawable.rice, "볶음밥", "720");
        FriendsItem friends11 = new FriendsItem(R.drawable.ggul, "귤(1개)", "50");
        FriendsItem friends12 = new FriendsItem(R.drawable.straw, "딸기(50g)", "50");
        FriendsItem friends13 = new FriendsItem(R.drawable.banana, "바나나(120g)", "100");
        FriendsItem friends14 = new FriendsItem(R.drawable.apple, "사과(200g)", "100");
        FriendsItem friends15 = new FriendsItem(R.drawable.lamen, "라면(1인분)", "570");
        FriendsItem friends16 = new FriendsItem(R.drawable.udong, "우동(1인분)", "450");
        FriendsItem friends17 = new FriendsItem(R.drawable.bbok, "떡볶이", "482");
        FriendsItem friends18 = new FriendsItem(R.drawable.emmok, "어묵", "126");
        FriendsItem friends19 = new FriendsItem(R.drawable.melon, "메로나", "147");
        FriendsItem friends20 = new FriendsItem(R.drawable.br, "베라(230g)", "220");
        FriendsItem friends21 = new FriendsItem(R.drawable.sand, "샌드위치", "468");
        FriendsItem friends22 = new FriendsItem(R.drawable.hotdog, "치즈핫도그", "430");
        FriendsItem friends23 = new FriendsItem(R.drawable.stake, "스테이크", "1259");
        FriendsItem friends24 = new FriendsItem(R.drawable.don, "돈까스", "700");
        FriendsItem friends25 = new FriendsItem(R.drawable.spa, "스파게티", "600");
        FriendsItem friends26 = new FriendsItem(R.drawable.pizza, "피자(r)", "1120");
        FriendsItem friends27 = new FriendsItem(R.drawable.omlet, "오믈렛", "152");
        FriendsItem friends28 = new FriendsItem(R.drawable.galbiitang, "갈비탕", "500");
        FriendsItem friends29 = new FriendsItem(R.drawable.gimchi, "김치찌개", "157");
        FriendsItem friends30 = new FriendsItem(R.drawable.sam, "삼계탕", "633");
        FriendsItem friends31 = new FriendsItem(R.drawable.americano, "아메리카노", "5");
        FriendsItem friends32 = new FriendsItem(R.drawable.cola, "콜라(250ml)", "110");
        FriendsItem friends33 = new FriendsItem(R.drawable.saida, "사이다(250ml)", "110");
        FriendsItem friends34 = new FriendsItem(R.drawable.milk, "우유(100g)", "66");


        data.add(friends1);
        data.add(friends2);
        data.add(friends3);
        data.add(friends5);
        data.add(friends6);
        data.add(friends7);
        data.add(friends8);
        data.add(friends9);
        data.add(friends10);
        data.add(friends11);
        data.add(friends12);
        data.add(friends13);
        data.add(friends14);
        data.add(friends15);
        data.add(friends16);
        data.add(friends17);
        data.add(friends18);
        data.add(friends19);
        data.add(friends20);
        data.add(friends21);
        data.add(friends22);
        data.add(friends23);
        data.add(friends24);
        data.add(friends25);
        data.add(friends26);
        data.add(friends27);
        data.add(friends28);
        data.add(friends29);
        data.add(friends30);
        data.add(friends31);
        data.add(friends32);
        data.add(friends33);
        data.add(friends34);
        FriendsAdapter adapter = new FriendsAdapter(this,R.layout.friends_item,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), FoodDetail.class);
                intent.putExtra("profile",Integer.toString(data.get(position).getProfile()));
                intent.putExtra("info",data.get(position).getInfo());
                intent.putExtra("phone",data.get(position).getPhone());

                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View view) {

    }
}
