package com.huangpeng.sys.modules.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.TimerTask;

/**
 * <pre>
 * 任务：
 * 描述：用java写的一个websocket客户端
 * 作者：@author huangpeng
 * 时间：@create 2017-12-15 下午3:23
 * 类名: MyWebSocketClient
 * </pre>
 **/
public class MyWebSocketClient extends TimerTask{

    public void clientInit() {
        try {
            WebSocketClient client  = new WebSocketClient(new URI("ws://localhost:8888"),new Draft_17()){
                @Override
                public void onOpen(ServerHandshake arg0) {
                    System.out.println("打开链接");
                }

                @Override
                public void onMessage(String arg0) {
                    System.out.println("收到消息"+arg0);
                }

                @Override
                public void onError(Exception arg0) {
                    arg0.printStackTrace();
                    System.out.println("发生错误已关闭");
                }

                @Override
                public void onClose(int arg0, String arg1, boolean arg2) {
                    System.out.println("链接已关闭");
                }

                @Override
                public void onMessage(ByteBuffer bytes) {
                    try {
                        System.out.println(new String(bytes.array(),"utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            };
            client.connect();

            while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
                System.out.println("还没有打开");
            }
            System.out.println("打开了");
            while(true){
                client.send("hello"+System.currentTimeMillis());
                Thread.sleep(5000L);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        clientInit();
    }
}
