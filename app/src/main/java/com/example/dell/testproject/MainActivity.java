package com.example.dell.testproject;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rece.BrRe;

public class MainActivity extends AppCompatActivity {

    BrRe brRe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et_send   = findViewById(R.id.et_send);
        Button   bt_send   = findViewById(R.id.bt_send);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent("com.example.broadcastsender.MY_BROADCAST");
                intent.putExtra("data",et_send.getText().toString().trim());
                sendBroadcast(intent);

//                Toast.makeText(MainActivity.this,"发送的广播是："+et_send.getText().toString().trim(),Toast.LENGTH_SHORT).show();
            }
        });
        /*IntentFilter intentFilter  = new IntentFilter();
        intentFilter.addAction("com.example.broadcastsender.MY_BROADCAST");
        brRe  = new BrRe();
        registerReceiver(brRe,intentFilter);*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        unregisterReceiver(brRe);
    }
}
