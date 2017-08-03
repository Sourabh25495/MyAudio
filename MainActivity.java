package com.sourabhkulkarni.myaudio;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.SeekBar;
import android.util.*;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    AudioManager audioManager;

    public void playAudio(View view){



        mp.start();


    }


    public void pauseAudio(View view){

        mp.pause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp= MediaPlayer.create(this,R.raw.vandematram);

        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        final int curVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);



        SeekBar vc=(SeekBar)findViewById(R.id.seekBar);
        vc.setMax(maxVolume);
        vc.setProgress(curVolume);
        vc.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                audioManager.setStreamVolume(audioManager.STREAM_MUSIC,progress,0);


            }
        });

            final SeekBar scruber=(SeekBar)findViewById(R.id.seekBar2);
            scruber.setMax(mp.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                        scruber.setProgress(mp.getCurrentPosition());
            }
        },0,1000);

            scruber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                   mp.seekTo(progress);

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
