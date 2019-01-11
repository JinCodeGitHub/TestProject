package com.example.dell.btnclicktest;




import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;

import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * <pre>
 *     author : dell
 *     time   : 2018/12/18
 *     desc   : for tcp connect(change from Internet)
 *     version: 1.0
 * </pre>
 */

public class TCPClientHelper {

    private Socket socket;
    private BufferedWriter  bufferedWriter ;
    private DataInputStream inputStream ;
    private InetSocketAddress SerAddr;
    public  String ErrMsg;

    private OnReceiveEvent receiveEvent;     //此事件用于当接收到数据时向主线程通知接收到的数据

    private byte[] recdatas;



    public  interface OnReceiveEvent{

        void ReceiveBytes(byte[] byteData);

          }

    public  void SetOnReceiveData(OnReceiveEvent receiveEvent){

         this.receiveEvent   = receiveEvent;
    }


    public TCPClientHelper(String HostIp, int HostPort) throws IOException {
        try {

            SerAddr =  new InetSocketAddress(HostIp,HostPort);
            socket = new Socket();
            bufferedWriter   =new BufferedWriter(new OutputStreamWriter( socket.getOutputStream(),"utf-8"));

            inputStream = new DataInputStream(socket.getInputStream());

        }catch (Exception e)
        {
            ErrMsg=e.getMessage();
        }
    }


    public void SendString(String str){       //发送字符串
        toSend(str);
    }

    //发送数据线程
    private void toSend(final String str){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {

                            bufferedWriter.write(str);
                            bufferedWriter.flush();
                            if(inputStream.available()>0){
                                    byte[] recData = new byte[inputStream.available()];
                                    inputStream.read(recData);

                                }

                        } catch (Exception e) {
                        }

                    }
                }
        ).start();
    }
    public boolean isConnected(){
        return socket.isConnected();
    }


}
