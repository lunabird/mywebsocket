package com.huangpeng.sys.modules.websocket;

import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * <pre>
 * 任务：
 * 描述：
 * 作者：@author huangpeng
 * 时间：@create 2017-12-15 上午10:33
 * 类名: MyWebSocket
 * </pre>
 **/


public class MyWebSocket extends WebSocketServer {

    int e = 0; // 异常数量
    int l = 0; // 连接数量

    public MyWebSocket(InetSocketAddress address) {
        super(address);
        System.out.println("地址" + address);
    }

    public MyWebSocket(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        System.out.println("端口" + port);
    }

    /**
     * 触发关闭事件
     */
    public void onClose(org.java_websocket.WebSocket conn, int i, String s, boolean b) {
        userLeave(conn);
    }

    /**
     * 触发异常事件
     */
    @Override
    public void onError(org.java_websocket.WebSocket conn, Exception message) {
        System.out.println("Socket异常:" + message.toString());
        e++;
    }
    /**
     * 客户端发送消息到服务器时触发事件
     */
    @Override
    public void onMessage(org.java_websocket.WebSocket conn, String message) {
        message = message.toString();
        if (message != null) { //将用户加入
            if(message.startsWith("hello")){
                this.detectJoin(message.toString(), conn);
            } else {
                this.userjoin(message.toString(), conn);
            }
        }
    }

    /**
     * 触发连接事件
     */
    @Override
    public void onOpen(org.java_websocket.WebSocket conn, ClientHandshake handshake) {
        System.out.println("有人连接Socket conn:" + conn);
        this.userjoin(conn.getRemoteSocketAddress().getHostName()+conn.toString(), conn);
        l++;
    }

    /**
     * 用户加入处理
     * @param user
     */
    public void userjoin(String user, org.java_websocket.WebSocket conn) {
        WebSocketPool.sendMessage(user);
        System.out.println("user---"+user);
        String joinMsg = "[系统]" + user + "上线了！";
        WebSocketPool.sendMessage(joinMsg); // 向所有在线用户推送当前用户上线的消息
        System.out.println("joinMsg---"+joinMsg);
        WebSocketPool.addUser(user, conn); // 向连接池添加当前的连接对象
        WebSocketPool.sendMessageToUser(conn, WebSocketPool.getOnlineUser().toString());
        System.out.println("WebSocketPool.getOnlineUser().toString()---"+WebSocketPool.getOnlineUser().toString());
    }
    public void detectJoin(String message, org.java_websocket.WebSocket conn) {
        WebSocketPool.sendMessage(message);
    }

    /**
     * 用户下线处理
     */
    public void userLeave(org.java_websocket.WebSocket conn) {
        String user = WebSocketPool.getUserByKey(conn);
        boolean b = WebSocketPool.removeUser(conn); // 在连接池中移除连接
        if (b) {
            WebSocketPool.sendMessage(user.toString()); // 把当前用户从所有在线用户列表中删除
            String joinMsg = "[系统]" + user + "下线了";
            WebSocketPool.sendMessage(joinMsg); // 向在线用户发送当前用户退出的消息
        }
    }


//    public static void main(String[] args) throws InterruptedException {
//        System.out.println("开始启动我的websocket");
//        WebSocketImpl.DEBUG = false;
//        int port = 8888; // 端口随便设置，只要不跟现有端口重复就可以
//        MyWebSocket s = null;
//        try {
//            s = new MyWebSocket(port);
//        } catch (UnknownHostException e) {
//            System.out.println("启动websocket失败！");
//            e.printStackTrace();
//        }
//        s.start();
//        System.out.println("启动websocket成功！");
//    }
}



