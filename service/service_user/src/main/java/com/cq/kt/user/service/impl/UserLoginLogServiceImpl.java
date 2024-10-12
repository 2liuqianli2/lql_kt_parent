package com.cq.kt.user.service.impl;

import com.cq.kt.model.user.UserLoginLog;
import com.cq.kt.user.mapper.UserLoginLogMapper;
import com.cq.kt.user.service.UserLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登陆记录表 服务实现类
 * </p>
 *
 * @author 六千里
 * @since 2024-07-02
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements UserLoginLogService {

}
