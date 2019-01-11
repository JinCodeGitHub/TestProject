package com.example.dell.btnclicktest;



import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketResponsePacket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class MainActivity extends AppCompatActivity {

    private  TcpClientSend tcpClientSend  = new TcpClientSend();

    private byte[] bytes;

    private SocketClient socketClient;
    private SocketClientDelegate delegate;

   /* private  SendThread sendThread   = new SendThread();

    private Handler handler   = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg != null){

                bytes     = (byte[]) msg.obj;
                Toast.makeText(MainActivity.this,"接收到的服务的数据："+ new String(bytes),Toast.LENGTH_SHORT).show();
            }


        }
    };
*/




    /**
     * 猜测点击事件需要两次才有反应的原因可能是因为线程的堵塞造成的
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            startSocketClient("");
        } catch (IOException e) {
            e.printStackTrace();
        }


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                try {
                    socketSendMessage("GET /RTCM32_GNSS HTTP/1.0\r\nUser-Agent: NTRIP GeneralSurvey/2.90\r\nAccept: */*\r\nAuthorization: Basic "+HelperUtils.getBase64("jhb:123456")+"\r\n"+"Connection: close\r\n\r\n");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//                 sendThread.start();
/*

                try {
                    tcpClientSend.initSocket("116.213.73.211",17778);
                    tcpClientSend.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/


//                new Thread(){
//                    @Override
//                    public void run() {
////                        super.run();
//
//                         tcpClientSend.getSocket("116.213.73.211", 17778);
//
//                        try {
//                            bytes  = tcpClientSend.SendData("GET /RTCM32_GNSS HTTP/1.0\r\nUser-Agent: NTRIP GeneralSurvey/2.90\r\nAccept: */*\r\nAuthorization: Basic "+HelperUtils.getBase64("jhb:123456")+"\r\n"+"Connection: close\r\n\r\n");
//
//                            Message message  = new Message();
//                            message.obj      = bytes;
//                            handler.sendMessage(message);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

//                        try {
//                            //1.创建监听指定服务器地址以及指定服务器监听的端口号
//                            Socket socket = new Socket("116.213.73.211", 17778);//111.111.11.11为我这个本机的IP地址，端口号为12306.
//                            //2.拿到客户端的socket对象的输出流发送给服务器数据
//                            OutputStream os = socket.getOutputStream();
//                            //写入要发送给服务器的数据
//                            os.write(("GET /RTCM32_GNSS HTTP/1.0\r\nUser-Agent: NTRIP GeneralSurvey/2.90\r\nAccept: */*\r\nAuthorization: Basic "+HelperUtils.getBase64("jhb:123456")+"\r\n"+"Connection: close\r\n\r\n").getBytes());
//                            os.flush();
//                            socket.shutdownOutput();
//                            //拿到socket的输入流，这里存储的是服务器返回的数据
//                            InputStream is = socket.getInputStream();
//                            //解析服务器返回的数据
//                            InputStreamReader reader = new InputStreamReader(is);
//                            BufferedReader bufReader = new BufferedReader(reader);
//                            String s = null;
//                            final StringBuffer sb = new StringBuffer();
//                            while((s = bufReader.readLine()) != null){
//                                sb.append(s);
//                            }
//
//                            Message message  = new Message();
//                            message.obj      = sb;
//                            handler.sendMessage(message);
//
//                           /* runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(MainActivity.this,"获取的服务端的值："+String.valueOf(sb),Toast.LENGTH_SHORT).show();
//                                }
//                            });*/
//
////                            Log.i("Stringbuffer", String.valueOf(sb));
//
//                            //3、关闭IO资源（注：实际开发中需要放到finally中）
//                            bufReader.close();
//                            reader.close();
//                            is.close();
//                            os.close();
//                            socket.close();
//                        } catch (UnknownHostException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

//
//                    }
//                }.start();

            }
        });
    }
//    class  SendThread  extends  Thread{
//        @Override
//        public void run() {
//            super.run();
//
//            tcpClientSend.getSocket("116.213.73.211", 17778);
//
//            try {
//                bytes  = tcpClientSend.SendData("GET /RTCM32_GNSS HTTP/1.0\r\nUser-Agent: NTRIP GeneralSurvey/2.90\r\nAccept: */*\r\nAuthorization: Basic "+HelperUtils.getBase64("jhb:123456")+"\r\n"+"Connection: close\r\n\r\n");
//
//                Message message  = new Message();
//                message.obj      = bytes;
//                handler.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }



    //socket通信
    private void startSocketClient(String info) throws IOException {
        //String[] array = info.split("_");
        socketClient = new SocketClient();
        //socketClient.getAddress().setRemoteIP(array[0]);//设置IP,这里设置的是本地IP
        //socketClient.getAddress().setRemotePort(String.valueOf(Integer.parseInt(array[1])));//设置端口
        socketClient.getAddress().setRemoteIP("116.213.73.211");//设置IP,这里设置的是本地IP
        socketClient.getAddress().setRemotePort(17778);//设置端口
        socketClient.getAddress().setConnectionTimeout(1 * 1000);//设置超时时间


        //socketClient.setCharsetName(CharsetUtil.UTF_8);//设置编码格式，默认为UTF-8
//        socketClient.setCharsetName("GBK");//设置编码格式，默认为UTF-8
        socketClient.connect(); // 连接，异步进行

        //常用回调配置
        // 对应removeSocketClientDelegate
        socketClient.registerSocketClientDelegate(delegate = new SocketClientDelegate() {
            /**
             * 连接上远程端时的回调
             */
            @Override
            public void onConnected(SocketClient client) {
                Log.d("melog", "gamesocket连接成功");
                //launcher.callExternalInterface("gameSocketConnectSuccess", "success");
            }

            /**
             * 与远程端断开连接时的回调
             */
            @Override
            public void onDisconnected(SocketClient client) {
                Log.d("melog", "gamesocket连接断开");
                // 可在此实现自动重连
                socketClient.connect();
                //launcher.callExternalInterface("socketClose", "close");
            }

            /**
             * 接收到数据包时的回调
             */
            @Override
            public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {

                String message = responsePacket.getMessage(); // 获取按默认设置的编码转化的String，可能为null
//                Log.i("接收服务端消息：",message);

                if (message.equals("ICY 200 OK\r\n\r\n")){
                    socketSendMessage("$GPGGA,000001.00,4005.408,N,11605.901,E,1,8,1,0.0,M,-32,M,3,0*7D\r\n");
                }

                Log.i("再次请求到的数据：",String.valueOf(message.getBytes()));

                //launcher.callExternalInterface("socketDataHandler", message);
            }
        });
    }

    //发送消息
    private void socketSendMessage(String info) {
        String status = String.valueOf(socketClient.getState());
        if (socketClient != null && status == "Connected") {
            socketClient.sendData(info.getBytes()); // 发送byte[]消息
        }
    }

    //前台请求gamesocket连接状态
    private void requestGameSocketConnectFlg() {
        String status = String.valueOf(socketClient.getState());
        //launcher.callExternalInterface("gameSocketFlg", status);
    }

    //前台主动断开gamesocket
    private void closeGameSocket() {
        if (socketClient != null) {
            socketClient.removeSocketClientDelegate(delegate);
            socketClient.disconnect();
        }
    }
}



