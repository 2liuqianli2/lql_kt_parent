package com.cq.kt.wechat.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-03-14:43
 * Description:
 *
 * @ Author:两袖青蛇
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${wechat.mpAppId}")
    private String appid;

    @Value("${wechat.mpAppSecret}")
    private String appsecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = appid;
        ACCESS_KEY_SECRET = appsecret;
    }
}
