package com.cq.kt.wechat.service;

import lombok.SneakyThrows;

import java.util.Map;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-03-20:55
 * Description:
 *
 * @ Author:两袖青蛇
 */
public interface MessageService {
    //TODO 暂时写成固定值测试，后续完善
    @SneakyThrows
    void pushPayMessage(long orderId);

    //接收消息
    String receiveMessage(Map<String, String> param);

}
