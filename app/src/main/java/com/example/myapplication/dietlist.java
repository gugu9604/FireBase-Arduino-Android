package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dietlist extends AppCompatActivity {
    Toolbar dietlistToolbar;
    ListView listview;
    ArrayList<String> items = new ArrayList<>();
    String num;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                Intent it = new Intent(getApplicationContext(),DietMode.class);
                startActivity(it);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dietlist);
        dietlistToolbar = findViewById(R.id.dietlisttoolbar);
        listview = findViewById(R.id.dietlist);
        setSupportActionBar(dietlistToolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        dietlistToolbar.setTitleTextColor(Color.BLACK);

        Intent it = getIntent();
        num = it.getStringExtra("routine");
        if(it.getStringExtra("routine").equals("1")){
            String title = "추천루틴";
            b.setTitle(title);
            items.add("푸쉬업");
            items.add("니업 - 시티드");
            items.add("스쿼트 ");
            items.add("V업 ");
            items.add("러시안 트위스트");
            items.add("플랭크");
        }else if(it.getStringExtra("routine").equals("2")){
            String title = "추천루틴";
            b.setTitle(title);
            items.add("arm walking shoulder tap");
            items.add("down dog push up");
            items.add("superman lat pull");
            items.add("Bench dips");
            items.add("side leg raise ");
            items.add("mountain climbers");
        }else if(it.getStringExtra("routine").equals("0")){
            String title = "운동법";
            b.setTitle(title);
            items.add("복근운동-크러치");
            items.add("팔운동-덤벨");
            items.add("하체운동-에어사이클");
            items.add("하체운동-런지");
            items.add("전신운동-줄넘기");
            items.add("전신운동-버피");
        }

        dietlist.CustomAdapter adapter = new dietlist.CustomAdapter(this,0,items);
        listview.setAdapter(adapter);
    }
    private class CustomAdapter extends ArrayAdapter<String> {
        private ArrayList<String> items;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<String> objects){
            super(context,textViewResourceId,objects);
            this.items=objects;
        }
        public View getView(int position,View convertView, ViewGroup parent){
            View v = convertView;
            if(v==null){
                LayoutInflater vi=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.listview_item1,null);
            }

            TextView textView = (TextView)v.findViewById(R.id.textView);
            textView.setText(items.get(position));

            final String text = items.get(position);
            Button button = (Button)v.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (text.equals("복근운동-크러치")) {
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","083jyzCaBMw");
                        intent.putExtra("explanation","복직근 중 상부를 강화하는 운동이다. 허리 부분이 바닥에서 떨어지지 않는다는 것이 싯업과 다른 점이다. \n" +
                                "근육을 수축시킬 때뿐만 아니라 이완될 때도 복부에 긴장을 유지하는 것이 중요하다.\n" +
                                "1.바닥에 누워 무릎을 구부리고 발이 바닥과 떨어지지 않도록 한다.\n" +
                                "2.양손을 귀에 대고 복부에 힘을 주면서 고개를 살짝 든다.\n" +
                                "3.어깨가 바닥에서 약 10cm 떨어지도록 등을 둥글게 구부리면서 상복부를 수축한다.\n" +
                                "4.상복부의 긴장을 느끼면서 천천히 몸통을 바닥으로 눕힌다.이때 머리가 완전히 바닥에 닿지 않도록 한다.");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }else if(text.equals("팔운동-덤벨")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","hgqSlNH_NYo");
                        intent.putExtra("explanation","초보자들은 물병들고 하시거나 1.5kg로 하시며 됩니다.\n" +
                                "맨몸으로 하셔도 되지만 유산소 운동 후 작은 물병이라도 가지고 하는거 추천해요\n" +
                                "유산소 운동과 함께 하면 효과가 더 빠릅니다.\n" +
                                "살 정리되고 라인이 잡혀요");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("하체운동-에어사이클")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","cyc8sNGnB8A");
                        intent.putExtra("explanation","에어사이클 운동 시 허리가 뜨면 안됩니다.\n" +
                                "배에 힘줘서 허리가 바닥에 딱 붙어있어야 합니다.\n" +
                                "다리 뒤에 스트레칭 안하면 쭉 안펴져요!\n" +
                                "평소에 다리 스트레칭도 꾸준히 해주세요!");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("하체운동-런지")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","zkO48VAJ1Sc");
                        intent.putExtra("explanation","힙업과 다리운동을 위한 런지\n" +
                                "자세를 교정해줄수 있는 영상입니다.\n" +
                                "4분36초 햄스트링 스트레칭\n" +
                                "5분17초 둔근 풀기\n" +
                                "6분50초 런지 자세 가르침 \n" +
                                "설명이 자세히 나와있습니다.");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("전신운동-줄넘기")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","RjMCLiw8yRQ");
                        intent.putExtra("explanation","줄넘기는 대표적인 유산소 운동입니다.\n" +
                                "알고보면 유산소 운동 뿐만 아니라  \n" +
                                "코어에도 효과적인 운동입니다.\n" +
                                "영상 내 상세한 설명이 이해를 돕습니다.");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("전신운동-버피")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","Uukm_JpUcRg");
                        intent.putExtra("explanation","짧은 시간안에 운동 효과를 극대화 할수 있는 유산소성 근력 운동.\n" +
                                "전신 집중 운동으로 새롭게 주목 받고있다.\n" +
                                "체력 향상,근력 향상 동시에 할수있으며\n" +
                                "다이어트에 효과적인 운동");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }else if (text.equals("arm walking shoulder tap")) {
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","Z7HKZoqKU3c");
                        intent.putExtra("explanation","전신의 모든 근력을 사용하게 되면서\n" +
                                "동시에 유산소 운동 효과를 얻을 수 있습니다.");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }else if(text.equals("down dog push up")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","1XmRVOjC0Bs");
                        intent.putExtra("explanation","무릎을 세워서 하체를 고정하기 때문에\n" +
                                "힙과 허벅지 후면이 강하게 당겨지고\n" +
                                "상체 고정으로서 고립운동 효과를 이끌어냄");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("superman lat pull")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","zKEH8CqkmOw");
                        intent.putExtra("explanation","등운동으로써\n" +
                                "슈퍼맨과 광배근을 자극하는 랫풀다운이\n" +
                                "합쳐진 동작!\n");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("Bench dips")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","0326dy_-CzM");
                        intent.putExtra("explanation","주된 수축은 삼두\n" +
                                "팔뚝살 부분 해결하는데 최고\n");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("side leg raise ")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","I3a9I2GGvx8");
                        intent.putExtra("explanation","안쪽 허벅지에 좋은 운동\n" +
                                "옆라인을 굵게 보이게 하는 승마살 제거 수행");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("mountain climbers ")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","fBZHkGT0W5Y ");
                        intent.putExtra("explanation","홈트레이닝 결정판\n" +
                                "전신 체지방 골고루 뺼수 있으며\n" +
                                "특히 복부에 지방 제거 효과 굳\n");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }else if (text.equals("푸쉬업")) {
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","-_DUjHxgmWk");
                        intent.putExtra("explanation","대흉근 운동으로 대표적인 운동 \n" +
                                "가슴을 발달시키는 데 정말 이만한 운동이 없습니다. ");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","diet");
                        startActivity(intent);

                    }else if(text.equals("니업 - 시티드")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","IJDNZIOtjb0");
                        intent.putExtra("explanation","의자에 팔로 상체를 지지한 후 다리를 끌어 올려\n" +
                                " 복부에 자극을 주기 때문에 복부 운동으로 효과가 높죠. ");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("스쿼트")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","IfJcq4LDXKE");
                        intent.putExtra("explanation","하체를 발달시키는 데 아주 좋은 운동\n" +
                                "더 강력한 운동 효과를 위해서는 바벨이나 덤벨을 이용.\n" +
                                "정확한 자세로 천천히 해주시는 게 좋아요. ");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("V업")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","DG_Za-Tn7MQ");
                        intent.putExtra("explanation","상복부와 하복부를 동시에 빠르게 발달시키는 운동이지만 \n" +
                                "난이도가 높으니 올바른 운동 자세를 지켜 열심히 해보시기 바랍니다. ");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("러시안 트위스트")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","2jS1iRu58uM");
                        intent.putExtra("explanation","상체와 하체를 띄우고 코어와 엉덩이의 힘으로 실시하는 운동\n" +
                                "복근 단련에 아주 좋으며 그 중에서도 복사근을 발달하는 데 큰 효과를 보입니다");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                    else if(text.equals("플랭크")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","Zq8nRY9P_cM ");
                        intent.putExtra("explanation","복근-허리-엉덩이를 동시에 발달할 수 있습니다");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","diet");
                        startActivity(intent);
                    }
                }

            });


            return v;
        }
    }
}
