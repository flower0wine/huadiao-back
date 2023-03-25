package com.huadiao.web.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huadiao.pojo.MessagePackage;
import com.huadiao.utils.StoreHttpSession;
import com.huadiao.web.mysqlProcess;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 12 23:55
 */
@ServerEndpoint(value = "/webSocket", configurator = StoreHttpSession.class)  // 这里不加上配置 setHttpSession 类不生效
public class messageWebsocket {

    // 所有会话
    private static final Map<String, messageWebsocket> userList = new ConcurrentHashMap<>();

    // 当前会话的 session
    private Session session;

    // 客户端的 请求 HttpSession
    private HttpSession httpSession;

    // 设置唯一标识
    private String userId;
    private String uid;

    @OnMessage
    public void onmessage(Session session, String obj) {
        JSONObject jsonObject = JSON.parseObject(obj);
        String uid;
        String sendDate = jsonObject.getString("sendDate");
        String message = jsonObject.getString("message");
        String sessionId = jsonObject.getString("sessionId");
        messageWebsocket sendObj = userList.get((uid = jsonObject.getString("targetUid")));
        try {
            if (sendObj != null) {
                sendObj.session.getBasicRemote().sendText(JSON.toJSONString(new MessagePackage(this.uid, uid, message, sendDate)));
            }
            mysqlProcess.insertSessionMessage(this.uid, uid, sessionId, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onopen(Session session, EndpointConfig config) {
        // 获取之前存储的 HttpRequest 对象
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        // 将其保存
        this.httpSession = httpSession;
        this.userId = (String) httpSession.getAttribute("userId");
        this.uid = (String) httpSession.getAttribute("uid");

        System.out.println(this.userId + " 与服务器建立连接");

        // 保存当前会话的 session
        this.session = session;

        // 将每一个会话都保存在 map 集合中，以 uid 作为唯一标识
        userList.put(uid, this);

    }

    @OnClose
    public void onclose(Session session) {
        System.out.println(this.userId + " 关闭会话");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onerror(Session session, Throwable throwable) {
        System.out.println(this.userId + " 会话发生错误");
        System.out.println(throwable.getMessage());
    }

    // 广播
    private void broadcast(String message) {
        try {
            for (messageWebsocket ws : userList.values()) {
                ws.session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
