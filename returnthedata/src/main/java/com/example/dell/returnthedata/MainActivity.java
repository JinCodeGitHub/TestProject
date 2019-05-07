package com.example.dell.returnthedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("a==1", String.valueOf(Pandata(1)));
        Log.i("a==2", String.valueOf(Pandata(2)));
        Log.i("a==0", String.valueOf(Pandata(0)));
    }

    //判断数据
    public  int  Pandata(int a){

        if (a == 1){
            return  1;
        }else if(a ==2){
            return  2;
        }
       return  0;
    }

}
