package com.example.adityaprakash.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
      TextView textView;
      SeekBar seekBar;
      boolean counterisActive =false;
      Button button;
      CountDownTimer countDownTimer;
      public void resetTimer(){
          button.setText("00:00");
          seekBar.setEnabled(true);
          seekBar.setProgress(0);
          countDownTimer.cancel();
          button.setText("GO!");
          counterisActive = false;
      }

      public void buttonClicked(View view){
          if(counterisActive){
              resetTimer();
          }
          else {
              counterisActive = true;
              seekBar.setEnabled(false);
              button.setText("STOP");

               countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                  @Override
                  public void onTick(long l) {
                      updateTimer((int) (l / 1000));
                  }

                  @Override
                  public void onFinish() {
                      MediaPlayer mpr = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                      mpr.start();
                      resetTimer();
                  }
              }.start();
          }
      }
      public void updateTimer(int secondsLeft){
          int min = secondsLeft/60;
          int sec = secondsLeft-min*60;
          String s2;
          if(sec>=0&&sec<10){
              s2 = Integer.toString(sec);
              s2 = "0"+s2;
          }
          else{
              s2 = Integer.toString(sec);
          }
          String s1 = Integer.toString(min);

          if(s1.equals("0")){s1 = "00";}
          textView.setText("0"+Integer.toString(min)+":"+s2);


      }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
         textView = findViewById(R.id.textView);
        seekBar.setMax(599);
        seekBar.setProgress(0);
        button = findViewById(R.id.button);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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