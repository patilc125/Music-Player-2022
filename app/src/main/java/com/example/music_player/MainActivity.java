package com.example.music_player;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SeekBar s;
    ImageButton forward,backward,stop,play;
    TextView names;
    ImageView bii;
    MediaPlayer mediaPlayer;
    Handler handler=new Handler();
    double starttime=0,finltime=0;
    int forwardtime=10000,backwardtime=10000;
    static int onetimeonly=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        s=findViewById(R.id.pb);
        forward=findViewById(R.id.ff);
        backward=findViewById(R.id.bb);
        stop=findViewById(R.id.stop);
        play=findViewById(R.id.play);
        names=findViewById(R.id.songn);
        mediaPlayer=MediaPlayer.create( this,R.raw.omahi);
        bii=findViewById(R.id.bi);
       // bii.setImageDrawable(getResources().getIdentifier("omahi","raw",getPackageName()));
        s.setClickable(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayMusic();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });
       forward.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               starttime=starttime+10;
               if (starttime>finltime){
                   starttime=finltime;
               }
               mediaPlayer.seekTo((int) starttime);
           }
       });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttime=starttime-10;
                if(starttime<0){
                    starttime=0;
                }
                mediaPlayer.seekTo((int) starttime);
            }
        });
    }
    private void PlayMusic() {
        mediaPlayer.start();
        finltime=mediaPlayer.getDuration();
        starttime=mediaPlayer.getCurrentPosition();
        if(onetimeonly==0){
            s.setMax((int) finltime);
            onetimeonly=1;
        }
        s.setProgress((int) starttime);
       // handler.postDelayed((Runnable) this,1000);
    }
}