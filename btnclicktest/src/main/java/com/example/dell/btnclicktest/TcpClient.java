package com.example.dell.btnclicktest;



import java.net.InetAddress;
import java.net.Socket;

/**
 * <pre>
 *     author : dell
 *     time   : 2018/12/21
 *     desc   : use for socket connet and send data for server
 *     version: 1.0
 * </pre>
 */

public abstract class TcpClient implements Runnable {

    private int port;
    private String hostIP;
    private boolean connect = false;
    private SocketTransceiver transceiver;


/**
 * 建立连接
 * <p>
 * 连接的建立将在新线程中进行
 * <p>
 * 连接建立成功，回调{@code onConnect()}
 * <p>
 * 连接建立失败，回调{@code onConnectFailed()}
 *
 * @param hostIP
 *            服务器主机IP
 * @param port
 *            端口
 */

    public void connect(String hostIP, int port) {
        this.hostIP = hostIP;
        this.port = port;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostIP, port);
            transceiver = new SocketTransceiver(socket) {

                @Override
                public void onReceive(InetAddress addr, byte[] bytes) {

                }

                @Override
                public void onDisconnect(InetAddress addr) {
                    connect = false;
                    TcpClient.this.onDisconnect(this);
                }
            };
            transceiver.start();
            connect = true;
            this.onConnect(transceiver);
        } catch (Exception e) {
            e.printStackTrace();
            this.onConnectFailed();
        }
    }


/**
 * 断开连接
 * <p>
 * 连接断开，回调{@code onDisconnect()}
 */

    public void disconnect() {
        if (transceiver != null) {
            transceiver.stop();
            transceiver = null;
        }
    }


/**
 * 判断是否连接
 *
 * @return 当前处于连接状态，则返回true
 */

    public boolean isConnected() {
        return connect;
    }


/**
 * 获取Socket收发器
 *
 * @return 未连接则返回null
 */

    public SocketTransceiver getTransceiver() {
        return isConnected() ? transceiver : null;
    }


/**
 * 连接建立
 *
 * @param transceiver
 *            SocketTransceiver对象
 */

    public abstract void onConnect(SocketTransceiver transceiver);


/**
 * 连接建立失败
 */

    public abstract void onConnectFailed();


/**
 * 接收到数据
 * <p>
 * 注意：此回调是在新线程中执行的
 *
 * @param transceiver
 *            SocketTransceiver对象
 * @param bytes
 *            字符串
 */

    public abstract void onReceive(SocketTransceiver transceiver, byte[] bytes);


/**
 * 连接断开
 * <p>
 * 注意：此回调是在新线程中执行的
 *
 * @param transceiver
 *            SocketTransceiver对象
 */

    public abstract void onDisconnect(SocketTransceiver transceiver);
}


/*public abstract class TcpClient implements Runnable {

    private int port;
    private String hostIP;
    private boolean connect = false;
    private SocketTransceiver transceiver;


*//**
     * 建立连接
     * <p>
     * 连接的建立将在新线程中进行
     * <p>
     * 连接建立成功，回调{@code onConnect()}
     * <p>
     * 连接建立失败，回调{@code onConnectFailed()}
     *
     * @param hostIP
     *            服务器主机IP
     * @param port
     *            端口
     *//*

    public void connect(String hostIP, int port) {
        this.hostIP = hostIP;
        this.port = port;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(hostIP, port);
            transceiver = new SocketTransceiver(socket) {

                @Override
                public void onReceive(InetAddress addr, String s) {
                    TcpClient.this.onReceive(this, s);
                }

                @Override
                public void onDisconnect(InetAddress addr) {
                    connect = false;
                    TcpClient.this.onDisconnect(this);
                }
            };
            transceiver.start();
            connect = true;
            this.onConnect(transceiver);
        } catch (Exception e) {
            e.printStackTrace();
            this.onConnectFailed();
        }
    }


*//**
     * 断开连接
     * <p>
     * 连接断开，回调{@code onDisconnect()}
     *//*

    public void disconnect() {
        if (transceiver != null) {
            transceiver.stop();
            transceiver = null;
        }
    }


*//**
     * 判断是否连接
     *
     * @return 当前处于连接状态，则返回true
     *//*

    public boolean isConnected() {
        return connect;
    }


*//**
     * 获取Socket收发器
     *
     * @return 未连接则返回null
     *//*

    public SocketTransceiver getTransceiver() {
        return isConnected() ? transceiver : null;
    }


*//**
     * 连接建立
     *
     * @param transceiver
     *            SocketTransceiver对象
     *//*

    public abstract void onConnect(SocketTransceiver transceiver);


*//**
     * 连接建立失败
     *//*

    public abstract void onConnectFailed();


*//**
     * 接收到数据
     * <p>
     * 注意：此回调是在新线程中执行的
     *
     * @param transceiver
     *            SocketTransceiver对象
     * @param s
     *            字符串
     *//*

    public abstract void onReceive(SocketTransceiver transceiver, String s);


*//**
     * 连接断开
     * <p>
     * 注意：此回调是在新线程中执行的
     *
     * @param transceiver
     *            SocketTransceiver对象
     *//*

    public abstract void onDisconnect(SocketTransceiver transceiver);
}*/




/*public abstract class SocketTransceiver implements Runnable {

    protected Socket socket;
    protected InetAddress addr;
    protected DataInputStream in;
    protected DataOutputStream out;
    private boolean runFlag;

    *//**
     * 实例化
     *
     * @param socket
     *            已经建立连接的socket
     *//*
    public SocketTransceiver(Socket socket) {
        this.socket = socket;
        this.addr = socket.getInetAddress();
    }

    *//**
     * 获取连接到的Socket地址
     *
     * @return InetAddress对象
     *//*
    public InetAddress getInetAddress() {
        return addr;
    }

    *//**
     * 开启Socket收发
     * <p>
     * 如果开启失败，会断开连接并回调{@code onDisconnect()}
     *//*
    public void start() {
        runFlag = true;
        new Thread(this).start();
    }

    *//**
     * 断开连接(主动)
     * <p>
     * 连接断开后，会回调{@code onDisconnect()}
     *//*
    public void stop() {
        runFlag = false;
        try {
            socket.shutdownInput();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    *//**
     * 发送字符串
     *
     * @param s
     *            字符串
     * @return 发送成功返回true
     *//*
    public boolean send(String s) {
        if (out != null) {
            try {
                out.writeUTF(s);
                out.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    *//**
     * 监听Socket接收的数据(新线程中运行)
     *//*
    @Override
    public void run() {
        try {
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            runFlag = false;
        }
        while (runFlag) {
            try {
                final String s = in.readUTF();
                this.onReceive(addr, s);
            } catch (IOException e) {
                // 连接被断开(被动)
                runFlag = false;
            }
        }
        // 断开连接
        try {
            in.close();
            out.close();
            socket.close();
            in = null;
            out = null;
            socket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.onDisconnect(addr);
    }

    *//**
     * 接收到数据
     * <p>
     * 注意：此回调是在新线程中执行的
     *
     * @param addr
     *            连接到的Socket地址
     * @param s
     *            收到的字符串
     *//*
    public abstract void onReceive(InetAddress addr, String s);

    *//**
     * 连接断开
     * <p>
     * 注意：此回调是在新线程中执行的
     *
     * @param addr
     *            连接到的Socket地址
     *//*
    public abstract void onDisconnect(InetAddress addr);
}*/
