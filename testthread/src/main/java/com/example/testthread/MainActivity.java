package com.example.testthread;

import android.nfc.Tag;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static  String   TAG   = "TESTTHREAD";
    private EditText  et_text;
    private String    etText;
    private String     xpText;
    private Button     bt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 实现在activity中启动一个线程，让线程一直处于运行状态；
         * 通过在屏幕上输入文字，然后将文字发送到线程中，在子线程中
         * 对数据进行操作；
         */

        et_text  = findViewById(R.id.et_text);
        bt_text  =  findViewById(R.id.bt_text);

        final DealThread dealThread     = new DealThread();
        Log.i(TAG,"onCreate"+String.valueOf(Thread.currentThread().getId()));
        dealThread.start();

        bt_text.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 etText    = et_text.getText().toString().trim();

                 Message message   = new Message();
                 message.obj       = etText;
                 dealThread.handler.sendMessage(message);

                 et_text.setText("");

             }
         });

        String  s   = "{12kjdlaijida;nkda}";
        StringBuffer  stringBuffer   = new StringBuffer();
        stringBuffer.append(s);
        Log.i("aaaaaaa", String.valueOf(s.charAt(0)));
        Log.i("aaaaaaa", (String) s.subSequence(0,1));
        Log.i("ccccccc",s.substring(0,1));

        Log.i("bbbbbbb",stringBuffer.substring(0,1));
        Log.i("ddddddd",stringBuffer.substring(stringBuffer.length()-1,stringBuffer.length()));
        Log.i("ddddddd",stringBuffer.substring(stringBuffer.length()-2,stringBuffer.length()-1));
    }

    class   DealThread extends  Thread {

         Handler handler;


        @Override
        public void run() {
            super.run();
             Looper.prepare();
            handler   = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    if (msg != null){
                        String s    = (String) msg.obj;
                        Log.i(TAG,s);
                        xpText   = s +"abcd";

                        Log.i(TAG,xpText);

                        Thread.currentThread().getId();
                        Log.i(TAG,String.valueOf(Thread.currentThread().getId()));
                    }
                }
            };
        Looper.loop();
        }

    }
}
