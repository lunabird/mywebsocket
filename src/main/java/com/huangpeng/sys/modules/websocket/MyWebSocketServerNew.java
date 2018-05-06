package com.huangpeng.sys.modules.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/**
 * <pre>
 * 任务：
 * 描述：websocketServer
 * 作者：@author huangpeng
 * 时间：@create 2017-12-15 上午10:33
 * 类名: MyWebSocket
 * </pre>
 **/


public class MyWebSocketServerNew extends WebSocketServer {

    int e = 0; // 异常数量
    int l = 0; // 连接数量

    public MyWebSocketServerNew(InetSocketAddress address) {
        super(address);
        System.out.println("地址" + address);
    }

    public MyWebSocketServerNew(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        System.out.println("端口" + port);
    }
    /**
     * 触发连接事件
     */
    @Override
    public void onOpen(org.java_websocket.WebSocket conn, ClientHandshake handshake) {
//        conn.send("Welcome to the server!"); //This method sends a message to the new client
//        broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" );
        l++;
        System.out.println("[连接事件]当前ws连接数量="+l);
    }

    /**
     * 触发关闭事件
     */
    public void onClose(org.java_websocket.WebSocket conn, int i, String s, boolean b) {
//        broadcast( conn + " has left!" );
        System.out.println( conn + " has left!" );
        l--;
        System.out.println("[关闭事件]当前ws连接数量="+l);
    }
    /**
     * 客户端发送消息到服务器时触发事件
     */
    @Override
    public void onMessage(org.java_websocket.WebSocket conn, String message) {
        broadcast( message );
        System.out.println( conn + ": " + message );
    }
    @Override
    public void onMessage(WebSocket conn, ByteBuffer message ) {
        broadcast( message.array() );
        System.out.println( conn + ": " + message );
    }
    @Override
    public void onError(WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            // some errors like port binding failed may not be assignable to a specific websocket
        }
    }
    @Override
    public void onStart() {
        System.out.println("Server started!");
    }

//    public static void main(String[] args) throws InterruptedException {
//        System.out.println("开始启动我的websocket");
//        WebSocketImpl.DEBUG = true;
//        int port = 8888; // 端口随便设置，只要不跟现有端口重复就可以
//        MyWebSocketServerNew s = null;
//        try {
//            s = new MyWebSocketServerNew(port);
//        } catch (UnknownHostException e) {
//            System.out.println("启动websocket失败！");
//            e.printStackTrace();
//        }
//        s.start();
//        System.out.println("启动websocket成功！");
//    }
}



