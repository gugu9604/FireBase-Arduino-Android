package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {
    MyCustomAdapter dataAdapter = null;

    String text;
    Toolbar foodlistToolbar;
    String time;
    String eat;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodlist);

        foodlistToolbar = findViewById(R.id.foodlistToolbar);
        setSupportActionBar(foodlistToolbar);
        ActionBar food = getSupportActionBar();
        food.setDisplayHomeAsUpEnabled(true);
        food.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Intent it = getIntent();
        time = it.getStringExtra("time");
        eat = it.getStringExtra("cal");
        if(it.getStringExtra("time").equals("아침")){
            String foodtitle = "아침";
            food.setTitle(foodtitle);
        }else if(it.getStringExtra("time").equals("점심")){
            String foodtitle = "점심";
            food.setTitle(foodtitle);
        }else if(it.getStringExtra("time").equals("저녁")){
            String foodtitle = "저녁";
            food.setTitle(foodtitle);
        }


        foodlistToolbar.setTitleTextColor(Color.BLACK);
        //Generate list View from ArrayList
        displayListView();

        checkButtonClick();

    }

    private void displayListView() {

        //Array list of countries
        ArrayList<Country> countryList = new ArrayList<Country>();
        Country country = new Country("싸이버거","956",false);
        countryList.add(country);
        country = new Country("치즈버거","389",false);
        countryList.add(country);
        country = new Country("프렌치 프라이","270",false);
        countryList.add(country);
        country = new Country("후라이드 치킨(100g)","289",false);
        countryList.add(country);
        country = new Country("군만두","630",false);
        countryList.add(country);
        country = new Country("짜장면","660",false);
        countryList.add(country);
        country = new Country("짬뽕","540",false);
        countryList.add(country);
        country = new Country("탕수육(1인분)","540",false);
        countryList.add(country);
        country = new Country("볶음밥","616",false);
        countryList.add(country);
        country = new Country("귤(1개)","50",false);
        countryList.add(country);
        country = new Country("딸기(50g)","50",false);
        countryList.add(country);
        country = new Country("바나나(120g)","100",false);
        countryList.add(country);
        country = new Country("사과(200g)","100",false);
        countryList.add(country);
        country = new Country("라면(1인분)","570",false);
        countryList.add(country);
        country = new Country("우동(1인분)","450",false);
        countryList.add(country);
        country = new Country("떡볶이","482",false);
        countryList.add(country);
        country = new Country("어묵","126",false);
        countryList.add(country);
        country = new Country("메로나","147",false);
        countryList.add(country);
        country = new Country("베라(230g)","220",false);
        countryList.add(country);
        country = new Country("샌드위치","468",false);
        countryList.add(country);
        country = new Country("치즈핫도그","430",false);
        countryList.add(country);
        country = new Country("스테이크","1259",false);
        countryList.add(country);
        country = new Country("돈까스","700",false);
        countryList.add(country);
        country = new Country("스파게티","600",false);
        countryList.add(country);
        country = new Country("피자(r)","1120",false);
        countryList.add(country);
        country = new Country("오믈렛","152",false);
        countryList.add(country);
        country = new Country("갈비탕","500",false);
        countryList.add(country);
        country = new Country("김치찌개","157",false);
        countryList.add(country);
        country = new Country("삼계탕","633",false);
        countryList.add(country);
        country = new Country("아메리카노","5",false);
        countryList.add(country);
        country = new Country("콜라(250ml)","110",false);
        countryList.add(country);
        country = new Country("사이다(250ml)","110",false);
        countryList.add(country);
        country = new Country("우유(100g)","66",false);
        countryList.add(country);





        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,R.layout.country_info, countryList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


    }

    private class MyCustomAdapter extends ArrayAdapter<Country> {

        private ArrayList<Country> countryList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Country> countryList) {
            super(context, textViewResourceId, countryList);
            this.countryList = new ArrayList<Country>();
            this.countryList.addAll(countryList);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.country_info, null);

                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        Country country = (Country) cb.getTag();
                        country.setSelected(cb.isChecked());
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            Country country = countryList.get(position);
            holder.name.setText(" " +  country.getCode() + "");
            holder.code.setText(country.getName());
            holder.name.setChecked(country.isSelected());
            holder.name.setTag(country);

            return convertView;

        }

    }

    private void checkButtonClick() {


        Button myButton = findViewById(R.id.findSelected);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();

                int j = 0;
                ArrayList<Country> countryList = dataAdapter.countryList;
                for(int i=0;i<countryList.size();i++){
                    Country country = countryList.get(i);
                    if(country.isSelected()){
                        responseText.append(country.getName());
                        j += Integer.parseInt(country.getName());
                    }
                }

                Intent intent = new Intent(getApplicationContext(), FoodMenu.class);
                intent.putExtra("Cal", Integer.toString(j));
                setResult(RESULT_OK,intent);
                finish();

            }
        });

    }
}
