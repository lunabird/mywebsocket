package com.huangpeng.sys.modules.websocket;

import java.util.TimerTask;

/**
 * <pre>
 * 任务：
 * 描述：向外推送消息的定时任务
 * 作者：@author huangpeng
 * 时间：@create 2018-05-06 下午1:04
 * 类名: PushMessageDemo
 * </pre>
 **/

public class PushMessageDemo extends TimerTask {
    MyWebSocketServerNew myWebSocketServerNew;
    public PushMessageDemo(MyWebSocketServerNew s){
        myWebSocketServerNew = s;
    }
    @Override
    public void run() {
        // 一个死循环，表示只要服务不停，就一直向外推送消息
        while(true){
            // 使用广播的方法推送一条当前时间的消息
            myWebSocketServerNew.broadcast("hello3333--"+System.currentTimeMillis());
            try {
                // 推完一条，等个5s
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
