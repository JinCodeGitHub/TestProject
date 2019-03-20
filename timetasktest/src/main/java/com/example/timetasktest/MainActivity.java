package com.example.timetasktest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                MyTimer myTimer = new MyTimer();
                myTimer.myTimer();
            }
        }).start();
*/
//        ScheduledExecutorService scheduledExecutorService    = Executors.newSingleThreadScheduledExecutor();
//        add(1,3);
        Log.d("ADDTest=1",String.valueOf(add(1,3)));
        AddThread  addThread   = new AddThread();
        addThread.start();
    }

    public int add (int a, int b ){
         int ab   = a + b;
        return ab;
    }


    public class AddThread extends Thread{
        @Override
        public void run() {
            super.run();
//         add(2,3);
         Log.d("ADDTest=2",String.valueOf(add(2,3)));
        }
    }
}
