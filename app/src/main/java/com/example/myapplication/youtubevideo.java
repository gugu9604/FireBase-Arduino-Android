package com.example.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class youtubevideo extends AppCompatActivity implements YouTubePlayer.OnInitializedListener{
    public static final String API_KEY = "AIzaSyBbeBWJpI7M-dq6KdBnfCo3GXP-KRkzAts";
    TextView tv;
    Toolbar youtubetoolbar;
    //http://youtu.be/<VIDEO_ID>
    String VIDEO_ID ;
    String num,mode;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                if(mode.equals("bulk")){
                    Intent it = new Intent(getApplicationContext(), bulklist.class);
                    if(num.equals("0")){
                        it.putExtra("routine","0");
                    }else if(num.equals("1")){
                        it.putExtra("routine","1");
                    }else if(num.equals("2")){
                        it.putExtra("routine","2");
                    }
                    startActivity(it);
                }else if(mode.equals("diet")){
                    Intent it = new Intent(getApplicationContext(), dietlist.class);
                    if(num.equals("0")){
                        it.putExtra("routine","0");
                    }else if(num.equals("1")){
                        it.putExtra("routine","1");
                    }else if(num.equals("2")){
                        it.putExtra("routine","2");
                    }
                    startActivity(it);
                }else if(mode.equals("normal")){
                    Intent it = new Intent(getApplicationContext(), normallist.class);
                    if(num.equals("0")){
                        it.putExtra("routine","0");
                    }else if(num.equals("1")){
                        it.putExtra("routine","1");
                    }else if(num.equals("2")){
                        it.putExtra("routine","2");
                    }
                    startActivity(it);
                }

            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubevideo);
        tv=findViewById(R.id.tv);
        youtubetoolbar = findViewById(R.id.youtubetoolbar);

        setSupportActionBar(youtubetoolbar);
        ActionBar b = getSupportActionBar();
        b.setDisplayHomeAsUpEnabled(true);
        b.setHomeAsUpIndicator(R.drawable.icons8_back_24);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String title = "영상";
        b.setTitle(title);
        youtubetoolbar.setTitleTextColor(Color.BLACK);

        Intent intent=getIntent();
        String text =intent.getStringExtra("explanation");
        num = intent.getStringExtra("routine");
        mode = intent.getStringExtra("mode");
        VIDEO_ID=intent.getStringExtra("ID");
        tv.setText(text);

        /** Initializing YouTube player view **/
        YouTubePlayerFragment youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.youtube_player);
        youTubePlayerFragment.initialize(API_KEY,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();

    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onBuffering(boolean arg0) {
        }

        @Override
        public void onPaused() {
        }

        @Override
        public void onPlaying() {
        }

        @Override
        public void onSeekTo(int arg0) {
        }

        @Override
        public void onStopped() {
        }

    };

    private PlayerStateChangeListener playerStateChangeListener = new PlayerStateChangeListener() {

        @Override
        public void onAdStarted() {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
        }

        @Override
        public void onLoaded(String arg0) {
        }

        @Override
        public void onLoading() {
        }

        @Override
        public void onVideoEnded() {
        }

        @Override
        public void onVideoStarted() {
        }
    };

}
