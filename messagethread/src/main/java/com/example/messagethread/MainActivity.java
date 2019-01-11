package com.example.messagethread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  TextView  tv_text;
    private  String    subsendString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //主线程向子线程发送消息：
        final  SubThread subThread   = new SubThread();
        subThread.start();
        final EditText et_send  = findViewById(R.id.et_send);
              Button   bt_send  = findViewById(R.id.bt_send);
                       tv_text = findViewById(R.id.tv_text);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message    = new Message();
                message.obj        = et_send.getText().toString().trim();
                subThread.shandler.sendMessage(message);
            }
        });

        }

    Handler  mhandler   = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg != null){

                subsendString    = (String) msg.obj;

                tv_text.setText(subsendString);
            }

        }
    };



    class  SubThread extends Thread{
        public Handler  shandler;
         String  recestring;

        @Override
        public void run() {
            super.run();

            Looper.prepare();

            shandler   = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    if (msg  != null){
                         recestring   = (String) msg.obj;

                        Log.i("主线程发过来的数据：",recestring);
                    }
                     //子线程向主线程发送数据；
                    Message  receiversend   = new Message();
                    receiversend.obj        = recestring;
                    mhandler.sendMessage(receiversend);
                }


            };


            Looper.loop();


        }
    }
}
