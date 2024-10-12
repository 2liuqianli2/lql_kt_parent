package com.cq.kt.wechat.service;


import com.cq.kt.model.wechat.Menu;
import com.cq.kt.vo.wechat.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 服务类
 * </p>
 *
 * @author 六千里
 * @since 2024-07-03
 */
public interface MenuService extends IService<Menu> {

    //获取所有一级菜单
    List<Menu> findMenuOneInfo();


    //获取所有菜单，按照一级和二级菜单封装
    List<MenuVo> findMenuInfo();

    void syncMenu() ;

    void removeMenu();
}
