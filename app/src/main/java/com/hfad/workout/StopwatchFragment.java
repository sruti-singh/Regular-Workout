package com.hfad.workout;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

   public class StopwatchFragment extends Fragment implements View.OnClickListener{

    private int seconds;
    private boolean running;
    private boolean wasRunning;

     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        if(wasRunning){
            running = true;
        }
    }
    public  View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         View layout =inflater.inflate(R.layout.fragment_stopwatch, container, false);
         runTimer(layout);
         Button startButton = (Button)layout.findViewById(R.id.start_button);
         startButton.setOnClickListener(this);
        Button stopButton = (Button)layout.findViewById(R.id.stop_button);
        startButton.setOnClickListener(this);
        Button resetButton = (Button)layout.findViewById(R.id.reset_button);
        startButton.setOnClickListener(this);

        return layout;
    }
     public void onClick(View v){
         switch (v.getId()) {
             case R.id.start_button:onClickStart(v);
             break;
             case R.id.stop_button: onClickStop(v);
             break;
             case R.id.reset_button: onClickReset(v);
             break;
         }
     }
    public void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }
    public void onResume(){
        super.onResume();
        if(wasRunning )
            running = true;
    }

    public  void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    public void onClickStart(View view){
        running = true;
    }
    public void onClickStop(View view){
        running = false;
    }
    public void onClickReset(View view){
        running = false;
        seconds =0;
    }
    private void runTimer(View view){
        final TextView timeView =(TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            public void run(){
                int hours = seconds/3600;
                int minutes =((seconds%3600)/60);
                int secs = seconds%60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}