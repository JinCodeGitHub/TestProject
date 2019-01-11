package com.example.timetasktest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyTimer myTimer = new MyTimer();
                myTimer.myTimer();
            }
        }).start();

//        ScheduledExecutorService scheduledExecutorService    = Executors.newSingleThreadScheduledExecutor();

    }
}
