package com.cq.kt.vod.utils;
import java.util.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.JWT;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-08-12:02
 * Description:
 *
 * @ Author:两袖青蛇
 */
public class Token {
    public static String token(String s,int id) {
        Integer AppId = id; // 用户 appid
        String FileId = s; // 目标 FileId
        String AudioVideoType = "RawAdaptive"; // 播放的音视频类型
        Integer RawAdaptiveDefinition = 10; // 允许输出的未加密的自适应码流模板 ID
        Integer ImageSpriteDefinition = 10; // 做进度条预览的雪碧图模板 ID
        long CurrentTime = (System.currentTimeMillis()/1000);;
        long PsignExpire = CurrentTime+24*60*60; // 可任意设置过期时间
        String hex = Long.toHexString(PsignExpire);
        System.out.println(hex);
        String UrlTimeExpire = hex; // 可任意设置过期时间
        String PlayKey = "AvW2aU91JPDknjn8mRcT";
        HashMap<String, Object> urlAccessInfo = new HashMap<String, Object>();
        urlAccessInfo.put("t", UrlTimeExpire);
        HashMap<String, Object> contentInfo = new HashMap<String, Object>();
        contentInfo.put("audioVideoType", AudioVideoType);
        contentInfo.put("rawAdaptiveDefinition", RawAdaptiveDefinition);
        contentInfo.put("imageSpriteDefinition", ImageSpriteDefinition);

        String token = "";

        try {
            Algorithm algorithm = Algorithm.HMAC256(PlayKey);
            token = JWT.create().withClaim("appId", AppId).withClaim("fileId", FileId)
                    .withClaim("contentInfo", contentInfo)
                    .withClaim("currentTimeStamp", CurrentTime).withClaim("expireTimeStamp", PsignExpire)
                    .withClaim("urlAccessInfo", urlAccessInfo).sign(algorithm);

            System.out.println("token:" + token);
        } catch (JWTCreationException exception) {
            System.out.println("Token错误");
            throw new RuntimeException();
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

}
