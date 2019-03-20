package com.example.dell.sensorsdemo;




import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.location.LocationManager;
import android.os.Build;
import android.os.Environment;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;



import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;


    private String TestSensor = "SENSOR";
    private SensorManager sensorManager;

    private Sensor accelerometerSensor;
    private Sensor gyroscopeSensor;
    private Sensor proximitySensor;
    private Sensor pressureSensor;
    private Sensor lightSensor;
    private Sensor magnetic_fieldSensor;
    private Sensor geomagneticrcSensor;
    private TestSensorEventListener mTestSensorEventListener;

    private float[] accfloat = new float[3];//加速度
    private float[] gyrfloat = new float[3];//陀螺仪
    private float[] magfloat = new float[3];//磁场力

    private float lightValue;
    private float pressureValue;

    private BlockingDeque<String> sensorlinkedBlockingDeque = new LinkedBlockingDeque<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager  = (LocationManager) getSystemService(LOCATION_SERVICE);

        mTestSensorEventListener = new TestSensorEventListener();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //获取到加速度传感器
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //获取陀螺仪传感器
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //获取周围空气气压(接近传感器)
//        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //获取压力传感器
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        //获取光线传感器
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //获取磁场传感器
        magnetic_fieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        //地磁旋转矢量
//        geomagneticrcSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);


        sensorManager.registerListener(mTestSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(mTestSensorEventListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, magnetic_fieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(mTestSensorEventListener, geomagneticrcSensor, SensorManager.SENSOR_DELAY_NORMAL);



        //动态的获的获取权限；
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    ){

                Toast.makeText(MainActivity.this,"已经获取权限GPS",Toast.LENGTH_LONG).show();

                writeFile();

            }else {
                //申请去获取权限；
                startRequestPermission();
            }
        }

    }

    //如果没有权限去请求权限；
    private void startRequestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
    }

    //请求权限回调；
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED&&grantResults[1] ==PackageManager.PERMISSION_GRANTED&&grantResults[2]==PackageManager.PERMISSION_GRANTED
                        && grantResults[3]==PackageManager.PERMISSION_GRANTED){

                    //在获取权限以后去打开gps
                    if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        Toast.makeText(MainActivity.this,"GPS已经被打开无需再次打开！",Toast.LENGTH_LONG).show();
                    }else {
                        Intent intent   = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }


                    for (int i = 0 ; i < grantResults.length;i++){

                        Log.i("ddddddd", "d"+String.valueOf(i)+String.valueOf(grantResults[i]));
                    }

                    writeFile();

//                   getPermission();
                }else{
                    //权限被拒绝；
//                    finish();

                }
            }else{
                //在小于23版本的情况下



            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();


/*
        sensorManager.registerListener(mTestSensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(mTestSensorEventListener, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mTestSensorEventListener, magnetic_fieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(mTestSensorEventListener, geomagneticrcSensor, SensorManager.SENSOR_DELAY_NORMAL);*/

    }


    @Override
    protected void onPause() {
        super.onPause();

//        sensorManager.unregisterListener(mTestSensorEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(mTestSensorEventListener);
    }

    private class TestSensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                accfloat[0] = event.values[0];
                accfloat[1] = event.values[1];
                accfloat[2] = event.values[2];

//                Log.i(TestSensor, String.valueOf(event.timestamp + "==" + "x= " + accfloat[0] + " y= " + accfloat[1] + " z= " + accfloat[2]));

            } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                gyrfloat[0] = event.values[0];
                gyrfloat[1] = event.values[1];
                gyrfloat[2] = event.values[2];

//                Log.i(TestSensor, String.valueOf(event.timestamp + "==" + "x1= " + gyrfloat[0] + " y1= " + gyrfloat[1] + " z1= " + gyrfloat[2]));
            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magfloat[0] = event.values[0];
                magfloat[1] = event.values[1];
                magfloat[2] = event.values[2];

//                Log.i(TestSensor, String.valueOf(event.timestamp + "==" + "x2= " + magfloat[0] + " y2= " + magfloat[1] + " z2= " + magfloat[2]));
            } else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {

                pressureValue = event.values[0];
//                Log.i(TestSensor, String.valueOf(event.timestamp + "==" + "pressureValue = " + pressureValue));

            } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {

                lightValue = event.values[0];
//                Log.i(TestSensor, String.valueOf(event.timestamp + "==" + "lightValue = " + lightValue));

            }
            String s = String.valueOf(System.currentTimeMillis()+"x="+accfloat[0] +"y="+accfloat[1]+"z="+accfloat[2] +
                    "x1="+gyrfloat[0]+"y1="+gyrfloat[1]+"z1="+gyrfloat[2]+
                    "x2="+magfloat[0]+"y2="+magfloat[1]+"z2="+magfloat[2]
                    +"pressureValue="+pressureValue
                    +"lightValue="+lightValue+"\n");

            Log.i(TestSensor, s);
            try {
                sensorlinkedBlockingDeque.put(s);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }




    //增加数据在文件中的存储；

    public void writeFile() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                    File  rootfile = Environment.getExternalStorageDirectory();

                    Log.i("FileRoot", rootfile.getPath());

                    String tmpfilepath  = rootfile.getPath()+"/Sensordatas";

                    File tmpfile    = new File(tmpfilepath);

                    if (!tmpfile.exists()) {
                        tmpfile.mkdir();
                        Log.i("tmpFile ", String.valueOf(tmpfile.mkdirs()));
                    }

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
//                    String  path    = Environment.getExternalStorageDirectory();///storage/emulated/0/Sensordata2019-03-19-20:12:04.csv
//                    File file    = new File(Environment.getExternalStorageDirectory(),"Sensordata"+ simpleDateFormat.format(System.currentTimeMillis())+".csv");
//                    File file    = new File("/storage/emulated/0","Sensordata"+ simpleDateFormat.format(System.currentTimeMillis())+".txt");
                    File file   = new File(tmpfile,simpleDateFormat.format(System.currentTimeMillis())+".txt");
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    while (true) {
                        try {
                            String take = sensorlinkedBlockingDeque.take();
                            try {
                                bufferedWriter.write(take);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try {
                            bufferedWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }

            }
        }).start();
    }

    public static boolean mkDir() {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(Environment.getExternalStorageDirectory(), "HAHAHA");
            if (!dir.exists()) {
                return dir.mkdirs();
            }
        }
        return false;
    }
}
