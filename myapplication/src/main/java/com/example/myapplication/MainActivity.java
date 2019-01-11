package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button bt_hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_hello = findViewById(R.id.bt_hello);
        bt_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bt_hello.getText().equals("Hello World!")){
                    bt_hello.setText("Welcome!");
                }else {
                    bt_hello.setText("Hello World!");
                }
            }
        });
    }
}
