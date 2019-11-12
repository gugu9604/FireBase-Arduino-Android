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

import java.io.IOException;
import java.util.ArrayList;

public class bluetoothlist extends AppCompatActivity {
    Toolbar bluetoothlistToolbar;
    ListView listview;
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
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetoothlist);
        bluetoothlistToolbar = findViewById(R.id.bluetoothlistToolbar);
        listview = findViewById(R.id.bluetoothlist);
        setSupportActionBar(bluetoothlistToolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "블루투스 운동";
        b.setTitle(title);
        bluetoothlistToolbar.setTitleTextColor(Color.BLACK);

        ArrayList<String> items = new ArrayList<>();
        items.add("아령 운동");
        items.add("푸쉬업");

        bluetoothlist.CustomAdapter adapter = new  bluetoothlist.CustomAdapter(this,0,items);
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
            button.setText("운동하기");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (text.equals("아령 운동")) {
                        Intent intent = new Intent(getApplicationContext(), dumbbellbluetooth.class);
                        startActivity(intent);
                    }else if(text.equals("푸쉬업")){
                        Intent intent = new Intent(getApplicationContext(), dumbbellbluetooth.class);
                        startActivity(intent);
                    }
                }

            });


            return v;
        }
    }
}
