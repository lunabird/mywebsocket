简单的一个websocket例子，当在浏览器打开一个窗口时建立websocket连接，关闭时删除websocket连接，后台有一个死循环，可以实现主动向前端推送消息。
需要注意的是如果使用tomcat启动需要自己添加servlet-api.jar。

websocket开启的端口是8888，项目启动以后过30s会向前台推消息，之后每隔5s推送一次，
消息内容是当前的系统时间。

项目启动以后访问http://localhost:port/mywebsocket/TestServlet可以看到推送的消息，
或者使用http://www.blue-zero.com/WebSocket/提供的websocket在线测试工具，输入
ws://localhost:8888也可以查看推送的消息。
