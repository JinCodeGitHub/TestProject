package com.example.dell.teststring;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double d    = 1.5561342629997234E9;
        double D    = 1.557998525999649E9;
        double D1 = 1.557998776009999E9;

        double d1   = d+0.5;
        long dd   = 1556134262;
        long  ddd = (long) d;
        long  d2  = (long) d1;
        long  d3  = Math.round(d);
        double dddddd = getdownSec(D1);


        double sssss = getSec(D1);
        Log.i("DDDD", String.valueOf(d));
        Log.i("DDDD", String.valueOf(dd));
        Log.i("DDDD", String.valueOf(ddd));
        Log.i("DDDD", String.valueOf(d2));
        Log.i("DDDD", String.valueOf(sssss));
        Log.i("DDDD", String.valueOf(d3));
        Log.i("DDDD", String.valueOf(dddddd));


        HashMap<String, Integer> stringIntegerHashMap01 = new HashMap<>();
        HashMap<String, Integer> stringIntegerHashMap02 = new HashMap<>();
        Set<String> keySet = stringIntegerHashMap01.keySet();

        for (String key: keySet ){
            if(stringIntegerHashMap02.containsKey(key)){

            }
        }


//        String  ss   = "Raw,1654656976,1108000000,,,-1240470277702998975,0.0,3.000000306E9,,,0,7,0.0,17,692855,22,31.01066780090332,683.2000185578217,5.748507411190534,22,130.4627304527967,3.4028234663852886E38,1.57542003E9,,,,0,,1,52.76594161987305,1.57542003E9";

       /* String ss  = "Nav,31,257,1,-1,2,34,-64,4,62,14,-95,-22,-52,17,-61,-102,-112,11,78,-102,-99,43,66,-125,65,3,20,-127,62,45,79,-106,-11,6,38,-24,101,3,58,83,107,10,-4,-111,91";

        String [] str   = ss.split(",");

        byte [] bytes   = new byte[40];
        for (int i = 6 ; i <str.length; i++){

            bytes[i-6]  = Byte.parseByte(str[i]);
            Log.i("bytes = ", String.valueOf(bytes[i-6]));
          *//*  for (int j = 0 ; j < bytes.length; j++){

                bytes[j] = Byte.parseByte(str[i]);

                Log.i("bytes = ", String.valueOf(bytes[j]));
            }*//*

        }
*/

        }

    public  double  getSec(double data){

        BigDecimal bigDecimal    = new BigDecimal(Double.toString(data));
        String  s  = String.valueOf(bigDecimal);
        String [] ss  = s.split("\\.");
        double  sss  = Double.parseDouble(ss[0]);
        double  ssss = data - sss;

         String res  = String.format("%.6f0",ssss);
         double sssss = Double.parseDouble(res);
        return  sssss;
    }


    public  double getdownSec(double data){

        double d  = Math.floor(data/1000);
        double dd = data/1000 -d;

        return  dd;
    }
}
