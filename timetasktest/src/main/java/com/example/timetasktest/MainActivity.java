package com.example.timetasktest;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        test();



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
        /*Log.d("ADDTest=1",String.valueOf(add(1,3)));
        AddThread  addThread   = new AddThread();
        addThread.start();*/
    }

    /**
     * https://stackoverflow.com/questions/54011168/is-it-sufficient-to-convert-a-double-to-a-bigdecimal-just-before-addition-to-ret
     * */
    @RequiresApi(api = Build.VERSION_CODES.N)
    void test(){
        long fullbiasnanos = -1238984306205187173L;
        double NS_TO_S = 1E-9;
        long GPS_WEEKSECS =  604800;

        long timenanos = 212795000000L;

        double gpsweek = Math.floor(-fullbiasnanos * NS_TO_S / GPS_WEEKSECS);

        long local_est_GPS_time = timenanos - fullbiasnanos;

        double xxx = local_est_GPS_time * NS_TO_S;

        MathContext mc =  new MathContext(128, RoundingMode.HALF_EVEN);

        MathContext mathContext = new MathContext(128, RoundingMode.FLOOR);


        BigDecimal.valueOf(Math.round(local_est_GPS_time * 1000000000),9);
        BigDecimal bigDecimal_   = BigDecimal.valueOf(local_est_GPS_time).multiply(BigDecimal.valueOf(NS_TO_S));

        BigDecimal bigDecimal_week  = BigDecimal.valueOf(gpsweek).multiply(BigDecimal.valueOf(GPS_WEEKSECS));

        BigDecimal  gpssow       = bigDecimal_.subtract(bigDecimal_week);




        BigDecimal bigDecimal1 = new BigDecimal(local_est_GPS_time,mathContext);
//        bigDecimal1.valueOf(Math.round(doubleVal * 100000))
        BigDecimal bigDecimal2 = new BigDecimal(NS_TO_S, mc);
        BigDecimal multiply = bigDecimal1.multiply(bigDecimal2, mc);

        multiply.setScale(50,BigDecimal.ROUND_HALF_EVEN);
        double result = multiply.doubleValue();
//        Double gpssow = local_est_GPS_time * NS_TO_S - gpsweek * GPS_WEEKSECS;
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
