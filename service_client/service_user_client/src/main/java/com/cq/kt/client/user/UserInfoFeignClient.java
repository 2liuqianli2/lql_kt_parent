package com.cq.kt.client.user;

import com.cq.kt.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-02-23:33
 * Description:
 *
 * @ Author:两袖青蛇
 */
@FeignClient("service-user")
public interface UserInfoFeignClient {
    @GetMapping("/user/userInfo/inner/getById/{id}")
    UserInfo getById(@PathVariable Long id);
}
