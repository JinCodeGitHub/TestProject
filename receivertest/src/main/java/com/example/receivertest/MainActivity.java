package com.example.receivertest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.receivertest.rece.BrReceiver;

public class MainActivity extends AppCompatActivity {

    private   BrReceiver brReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_receivertext  = findViewById(R.id.tv_receivertext);

        IntentFilter intentFilter   = new IntentFilter();

        intentFilter.addAction("com.example.broadcastsender.MY_BROADCAST");
         brReceiver   = new BrReceiver();
        registerReceiver(brReceiver,intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(brReceiver);
    }
}
