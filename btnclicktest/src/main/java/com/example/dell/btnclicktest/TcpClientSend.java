package com.example.dell.btnclicktest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * <pre>
 *     author : dell
 *     time   : 2018/12/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class TcpClientSend {


    private  Socket socket ;
    private  OutputStream os;
    private  InputStream is;
    private  InputStreamReader reader;
    private  BufferedReader bufReader;
    private   String s ;
    private  String host;
    private  int    port;



    public  void   getSocket(String host ,int port){
        try {
            socket  = new Socket(host,port);
            os      = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  byte[] SendData(String str) throws  IOException{

        if (os != null){
            os.write(str.getBytes());
            os.flush();
            socket.shutdownOutput();
            is   = socket.getInputStream();
            reader  = new InputStreamReader(is);
            bufReader  = new BufferedReader(reader);

            final StringBuffer sb = new StringBuffer();
            while((s = bufReader.readLine()) != null){
                sb.append(s);
            }


            return new String(sb).getBytes();
        }


         return  "1".getBytes();
    }

 /*   public  int initSocket(String host ,int port) throws  IOException
    {
        this.host  = host;
        this.port  = port;

        return  1;
    }
*/

  /*  public  String  SendStr(String str) throws  IOException{

        os   =  socket.getOutputStream();
        os.write(str.getBytes());
        is   = socket.getInputStream();
        reader   = new InputStreamReader(is);
        bufReader  = new BufferedReader(reader);

        final StringBuffer sb = new StringBuffer();
        while((s = bufReader.readLine()) != null){
            sb.append(s);
        }

         return String.valueOf(sb);

    }*/
//    @Override
//    protected String doInBackground(Void... voids) {
//
//        try {
//            socket   = new Socket(host,port);
//            String  s   = SendStr("GET /RTCM32_GNSS HTTP/1.0\r\nUser-Agent: NTRIP GeneralSurvey/2.90\r\nAccept: */*\r\nAuthorization: Basic "+HelperUtils.getBase64("jhb:123456")+"\r\n"+"Connection: close\r\n\r\n");
//            Log.i("Doin",s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//    }
}
