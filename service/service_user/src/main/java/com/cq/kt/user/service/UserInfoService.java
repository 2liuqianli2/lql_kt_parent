package com.cq.kt.user.service;


import com.cq.kt.model.user.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-07-02
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfo getByOpenid(String openId);
}
