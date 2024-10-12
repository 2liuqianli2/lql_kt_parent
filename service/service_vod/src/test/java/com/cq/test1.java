package com.cq;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-08-13:43
 * Description:
 *
 * @ Author:两袖青蛇
 */
public class test1 {
    public static void main(String[] args) {
        long CurrentTime = (System.currentTimeMillis());
        System.out.println(CurrentTime);
        long PsignExpire = CurrentTime+24*60*60*1000; // 可任意设置过期时间
        String hex = Long.toHexString(PsignExpire);

        System.out.println(hex);
    }
}
