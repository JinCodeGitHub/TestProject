package com.example.dell.addviewdemo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout cl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cl = findViewById(R.id.cl);

        findViewById(R.id.tv_mian);


        findViewById(R.id.tb_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });
    }


    public void addView() {

        View layoutInflater = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_demo_view, cl,false);
        TextView ss = layoutInflater.findViewById(R.id.tv_add);
        cl.addView(ss);
    }
}
