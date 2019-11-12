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

import java.util.ArrayList;

public class bulklist extends AppCompatActivity {

    Toolbar bulkToolbar2;
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
                Intent it = new Intent(getApplicationContext(),BulkMode.class);
                startActivity(it);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulklist);
        bulkToolbar2= findViewById(R.id.bulktoolbar2);
        listview = findViewById(R.id.bulklist);

        setSupportActionBar(bulkToolbar2);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        bulkToolbar2.setTitleTextColor(Color.BLACK);

        Intent it = getIntent();
        num = it.getStringExtra("routine");
        if(it.getStringExtra("routine").equals("1")){
            String title = "추천루틴";
            b.setTitle(title);
            items.add("스쿼트");
            items.add("바벨 벤치프레스");
            items.add("밀리터리 프레스");
            items.add("V업");
            items.add("벤트오버 바벨로우");
            items.add("레그프레스");
        }else if(it.getStringExtra("routine").equals("2")){
            String title = "추천루틴";
            b.setTitle(title);
            items.add("스쿼트");
            items.add("레그익스텐션");
            items.add("덤벨플라이");
            items.add("사이드레터럴레이즈");
            items.add("턱걸이");
            items.add("덤벨 벤치프레스");
        }else if(it.getStringExtra("routine").equals("0")){
            String title = "운동법";
            b.setTitle(title);
            items.add("하체 + 전신 운동");
            items.add("하체운동 - 허벅지 안쪽");
            items.add("등 + 팔 운동");
            items.add("팔운동 -4가지");
            items.add("어깨 운동");
            items.add("복근 운동");
        }



        CustomAdapter adapter = new CustomAdapter(this,0,items);
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
                    if (text.equals("하체 + 전신 운동")) {
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","GxYAM9Feavo");
                        intent.putExtra("explanation","유니크핏 상급자를 위한 하체와 전신 운동 홈트!\n운동 시작 전 하체 스트레칭 실시 후 진행 바랍니다.");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }else if(text.equals("하체운동 - 허벅지 안쪽")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","1rA4_WuwlrQ");
                        intent.putExtra("explanation","허벅지살 빼는 운동 플랜\n" +
                                "동작13가지 30초 운동 10초 휴식 x3세트!");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("등 + 팔 운동")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","48fGAJSM7LU");
                        intent.putExtra("explanation","턱걸이, 딥스, 푸쉬업 3동작이구요.\n" +
                                "딥스는 턱걸이 갯수와 똑같이 하시면 되고, 푸쉬업은 턱걸이 갯수의 두배로 하시면 됩니다.\n" +
                                "10부터 1까지 내려오시면 되구요, 휴식시간은 1분 입니다.");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("팔운동 -4가지")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","9FWYdsOtoIs");
                        intent.putExtra("explanation","덤벨 총 4가지운동으로 팔운동을 끝내는건데요!!\n2개 이두, 2개 삼두운동으로 구성되어있습니다.");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("어깨 운동")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","BtwBg1E0f08");
                        intent.putExtra("explanation","제가 생각하기에 \"덤벨프레스\" 라는 운동이 가장 어렵지 않을까 생각해서 해당 운동에 대한 영상을 만들어 보았습니다.\n" +
                                "이 운동이 되면 밀프+덤벨프레스+기구프레스 이 세개로 어깨 깡패됩시다! ");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("복근 운동")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","cIIowHQ3iBc");
                        intent.putExtra("explanation","하루 5분씩 일주일에 3~4번 꾸준한운동으로 복근을 만들어보세요. 한 싸이클 해보시고 아직 할 힘이 남이있다 싶으시면 한 싸이클 더 하셔도 됩니다. ");
                        intent.putExtra("routine","0");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }else if (text.equals("스쿼트")) {
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","thQ46oNt7nY");
                        intent.putExtra("explanation","하체를 발달시키는 데 아주 좋은 운동\" +\n" +
                                "\"더 강력한 운동 효과를 위해서는 바벨이나 덤벨을 이용.\" +\n" +
                                "\"정확한 자세로 천천히 해주시는 게 좋아요. ");
                        intent.putExtra("mode","bulk");
                        if(num.equals("1")){
                            intent.putExtra("routine","1");
                        }else if(num.equals("2")){
                            intent.putExtra("routine","2");
                        }
                        startActivity(intent);

                    }else if(text.equals("레그익스텐션")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","ew8uCXiFmqQ");
                        intent.putExtra("explanation","허벅지 전면 근육을 발달시키는 운동이다. 여성의 경우, 적당한 무게로 횟수를 늘려 실시하면 \n" +
                                "탄력 있는 허벅지를 만드는 데 효과적이다. \n" +
                                "또한 두 다리의 근력 수준 차이를 극복하기 위해 한 발씩 실시해도 좋은 운동이 될 수 있다. ");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("덤벨플라이")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","8mSWqB3Yqm4");
                        intent.putExtra("explanation","대흉근 운동의 마무리 동작으로 많이 사용된다. 남성들의 가슴 안쪽 라인을 발달시키는 데\n" +
                                "필수적인 운동으로 추천된다. 정확한 동작으로 집중적으로 실시하는 것이 중요하다. ");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("사이드레터럴레이즈")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","Zmx7o0QXNAg");
                        intent.putExtra("explanation","양 팔을 뻗은 채, 덤벨을 양 손으로 잡고 서서 어깨 높이까지 덤벨을 끌어올리는 운동. \n" +
                                "양 팔을 반드시 뻗은 채 하는 것이 일반적인 방법이며 어깨를 강화하는 운동이다. ");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("턱걸이")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","nWhS28U6bCY");
                        intent.putExtra("explanation","철봉에 매달려 팔꿈치 관절을 굴곡시켜 턱을 철봉 위에까지 끌어올렸다가 \n" +
                                "다시 팔꿈치를 펴면서 매달린 자세로 되돌아 오기를 되풀이하는 운동");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("덤벨 벤치프레스")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","eNqR5hYOJA4");
                        intent.putExtra("explanation","대흉근 전체 가동 범위에서 동작이 이루어지기 때문에 가슴을 모아주는 중앙 부위와 바깥쪽 근육의 크기를 키우는 데 효과적이다. \n" +
                                "덤벨을 들었을 때 균형을 유지하기 위한 근육들을 함께 발달시킬 수 있는 장점이 있어 \n" +
                                "강하고 안정된 상체를 만들 수 있다.");
                        intent.putExtra("routine","2");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }else if(text.equals("바벨 벤치프레스")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","iZ5jn1B2uM4");
                        intent.putExtra("explanation","가슴 운동을 하는 프리 웨이트 중에서 중급 이상의 숙련자에게 추천되는 운동법이다.\n" +
                                " 대흉근의 전체적인 크기를 키워주고 삼각근과 상완삼두근의 보조적인 참여를 유도해 \n" +
                                "강하면서도 균형 있는 상체를 만드는 데 효과적이다. ");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("밀리터리 프레스")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","ZjtrVA2uyS4");
                        intent.putExtra("explanation","바벨을 클린하여 몸을 수직으로 유지한 채, 다리나 허리의 반동을 이용하지 않고\n" +
                                " 팔과 어깨의 힘만으로 머리 위로 들어올리는 동작. ");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("V업")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","DG_Za-Tn7MQ");
                        intent.putExtra("explanation","상복부와 하복부를 동시에 빠르게 발달시키는 운동이지만 \n" +
                                "난이도가 높으니 올바른 운동 자세를 지켜 열심히 해보시기 바랍니다. ");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("벤트오버 바벨로우")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","IBG8XWAyeGQ");
                        intent.putExtra("explanation5","먼저 벤트오버 바벨로우는 등 전체의 근육을 발달 시키는 대표적안 운동\n" +
                                "상체 전면과 후면 근뮥들의 협응을 통해 운동이 이루어짐으로써 상체보다 단단하고 두껍게 만들어줍니다.");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                    else if(text.equals("레그프레스")){
                        Intent intent = new Intent(getApplicationContext(), youtubevideo.class);
                        intent.putExtra("phone", text);
                        intent.putExtra("ID","PmqkX6Te540");
                        intent.putExtra("explanation","\n" +
                                "허벅지 앞을 가장 효율적으로 단련시키는 대중적인 근력운동\n" +
                                "동작과 발디딤,등판의 각도에 따라 하체의 여러 근육부위들을 자극시킬수 있다.\n" +
                                "주의-엉덩이나 허리 하부가 등판에서 뜨면 안됩니다.\n");
                        intent.putExtra("routine","1");
                        intent.putExtra("mode","bulk");
                        startActivity(intent);
                    }
                }

            });


            return v;
        }
    }
}
