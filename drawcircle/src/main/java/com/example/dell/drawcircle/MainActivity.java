package com.example.dell.drawcircle;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private  int a ;
    private  int b ;
    private Timer timer  = new Timer() ;
    private SetData setData;
    private GetData getData = new GetData();
    private InitData initData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.draw).setBackgroundColor(Color.DKGRAY);

        timer.schedule(timerTask,0,100);

//        Log.i("LOG",String.valueOf(a )+"+"+String.valueOf(b));


         initData  = new InitData(MainActivity.this,getData);


         }



    TimerTask timerTask   = new TimerTask() {
        @Override
        public void run() {

            a = (int)(Math.random()*(10-1)+1);
            b = (int)(Math.random()*(10-1)+1);

            initData.getsData(a,b);

//            Log.i("LOG",String.valueOf(a )+"+"+String.valueOf(b));
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        timer.cancel();
    }




}
