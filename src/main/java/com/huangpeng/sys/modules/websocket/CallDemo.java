package com.huangpeng.sys.modules.websocket;

import java.util.TimerTask;

/**
 * <pre>
 * 任务：
 * 描述：每隔5秒钟向前台推送一条消息
 * 作者：@author huangpeng
 * 时间：@create 2017-12-15 下午4:34
 * 类名: CallDemo
 * </pre>
 **/

public class CallDemo  extends TimerTask {

    @Override
    public void run() {
        while(true){
            WebSocketPool.sendMessage("hello3333--"+System.currentTimeMillis());
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
