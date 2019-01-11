package com.example.timetasktest;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 *     author : dell
 *     time   : 2018/07/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class MyTimer {


    public  void  myTimer () {

        Timer timer   = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

                    String s   =  "测试用的数据！";
                    String  path   = Environment.getExternalStorageDirectory()+"/InsData"+System.currentTimeMillis()+".txt";
                    File file  = new File(path);
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    FileOutputStream fileOutputStream   = null;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    OutputStreamWriter outputStreamWriter    = new OutputStreamWriter( fileOutputStream);

                    BufferedWriter bufferedWriter   = new BufferedWriter(outputStreamWriter);

                    try {
                        bufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("TASK !", "验证是不是50秒执行一次！");
            }
        },0,50000);
    }




}
