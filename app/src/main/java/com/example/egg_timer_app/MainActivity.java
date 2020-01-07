package com.example.egg_timer_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTetView;

    public void updateTimer(int secondLeft){
        int minutes = (int)secondLeft / 60;
        int seconds = secondLeft - minutes * 60;

        String secondString = Integer.toString(seconds);
        if(secondString == "0"){
            secondString = "00";

        }else if(seconds <=9){
          secondString = "0" + secondString;
        }

        timerTetView.setText( Integer.toString(minutes) + ":" + secondString);
    }

    public void controlTimer(View view){

        new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                MediaPlayer  mplayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mplayer.start();
            }
        }.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeekBar =  findViewById(R.id.timerSeekBar);
        timerTetView = findViewById(R.id.timerTextView);



        timerSeekBar.setMax(600);

        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
