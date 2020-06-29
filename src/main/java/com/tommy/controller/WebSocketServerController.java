package com.tommy.controller;

import com.tommy.entity.MsgVO;
import com.tommy.entity.User;
import com.tommy.mapper.UserMapper;
import com.tommy.service.UserService;
import com.tommy.units.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tommy on 2020/5/10 17:03
 */
@Component
@ServerEndpoint("/groupChat/{sid}/{userId}")
public class WebSocketServerController {
    @Autowired
    protected UserService userService;
    private static WebSocketServerController  webSocketServerController ;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        webSocketServerController = this;
        webSocketServerController.userService = this.userService;
        // 初使化时将已静态化的testService实例化
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 房间号 -> 组成员信息
     */
    private static ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();
    /**
     * 房间号 -> 在线人数
     */
    private static ConcurrentHashMap<String, Set<Integer>> onlineUserMap = new ConcurrentHashMap<>();

    /**
     * 收到消息调用的方法，群成员发送消息
     *
     * @param sid:房间号
     * @param userId：用户id
     * @param message：发送消息
     */
    @OnMessage
    public void onMessage(@PathParam("sid") String sid, @PathParam("userId") Integer userId, String message) {
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        Set<Integer> onlineUserList = onlineUserMap.get(sid);
        // 先一个群组内的成员发送消息
        sessionList.forEach(item -> {
            try {
                // json字符串转对象
                MsgVO msg = com.alibaba.fastjson.JSONObject.parseObject(message, MsgVO.class);
                msg.setCount(onlineUserList.size());
                // json对象转字符串
                String text = com.alibaba.fastjson.JSONObject.toJSONString(msg);
                item.getBasicRemote().sendText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 建立连接调用的方法，群成员加入
     *
     * @param session
     * @param sid
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid, @PathParam("userId") Integer userId) {
        List<Session> sessionList = groupMemberInfoMap.computeIfAbsent(sid, k -> new ArrayList<>());
        Set<Integer> onlineUserList = onlineUserMap.computeIfAbsent(sid, k -> new HashSet<>());
        onlineUserList.add(userId);
        sessionList.add(session);
        // 发送上线通知
        sendInfo(sid, userId, onlineUserList.size(), "上线了~");
    }


    public void sendInfo(String sid, Integer userId, Integer onlineSum, String info) {
        // 获取该连接用户信息
        User currentUser = webSocketServerController.userService.selectByUid(userId);


        // 发送通知
        MsgVO msg = new MsgVO();
        msg.setCount(onlineSum);
        msg.setUserId(userId);
        msg.setAvatar(currentUser.getAvatar());
        msg.setMsg(currentUser.getUname() + info);
        // json对象转字符串
        String text = com.alibaba.fastjson.JSONObject.toJSONString(msg);
        onMessage(sid, userId, text);

    }

    /**
     * 关闭连接调用的方法，群成员退出
     *
     * @param session
     * @param sid
     */
    @OnClose
    public void onClose(Session session, @PathParam("sid") String sid, @PathParam("userId") Integer userId) {
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        sessionList.remove(session);
        Set<Integer> onlineUserList = onlineUserMap.get(sid);
        onlineUserList.remove(userId);
        // 发送离线通知
        sendInfo(sid, userId, onlineUserList.size(), "下线了~");
    }

    /**
     * 传输消息错误调用的方法
     *
     * @param error
     */
    @OnError
    public void OnError(Throwable error) {
        logger.info("Connection error");
    }
}
