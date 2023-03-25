package com.huadiao.utils;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @projectName 花凋
 * @author  flowerwine
 * @version  1.1
 * @description 该方法用于 websocket 连接时，存储 httpRequest 对象
 */
public class StoreHttpSession extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 获取客户端发送的请求 HttpSession
        Object httpSession = request.getHttpSession();
        // 将 httpSession 存储在 ServerEndpointConfig 对象的 Map 集合成员变量中，就可以在 websocket 服务器中取得 HttpSession
        sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
