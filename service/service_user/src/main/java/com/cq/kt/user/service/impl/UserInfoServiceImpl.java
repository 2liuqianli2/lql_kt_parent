package com.cq.kt.user.service.impl;

import com.cq.kt.model.user.UserInfo;
import com.cq.kt.user.mapper.UserInfoMapper;
import com.cq.kt.user.service.UserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 六千里
 * @since 2024-07-02
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public UserInfo getByOpenid(String openId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openId);
        UserInfo userInfo = baseMapper.selectOne(wrapper);
        return userInfo;
    }

}
