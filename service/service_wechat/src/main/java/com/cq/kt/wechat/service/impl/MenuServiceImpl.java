package com.cq.kt.wechat.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cq.kt.model.wechat.Menu;
import com.cq.kt.vo.wechat.MenuVo;
import com.cq.kt.wechat.mapper.MenuMapper;
import com.cq.kt.wechat.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author 六千里
 * @since 2024-07-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private WxMpService wxMpService;


    @SneakyThrows
    @Override
    public void removeMenu() {
        wxMpService.getMenuService().menuDelete();
    }
    @Override
    public void syncMenu()  {
        List<MenuVo> menuVoList = this.findMenuInfo();
        //菜单
        JSONArray buttonList = new JSONArray();
        for(MenuVo oneMenuVo : menuVoList) {
            JSONObject one = new JSONObject();
            one.put("name", oneMenuVo.getName());
            JSONArray subButton = new JSONArray();
            for(MenuVo twoMenuVo : oneMenuVo.getChildren()) {
                JSONObject view = new JSONObject();
                view.put("type", twoMenuVo.getType());
                if(twoMenuVo.getType().equals("view")) {
                    view.put("name", twoMenuVo.getName());
                    view.put("url", "http://cq.v7.idcfengye.com/#"
                            +twoMenuVo.getUrl());
                } else {
                    view.put("name", twoMenuVo.getName());
                    view.put("key", twoMenuVo.getMeunKey());
                }
                subButton.add(view);
            }
            one.put("sub_button", subButton);
            buttonList.add(one);
        }
        //菜单
        JSONObject button = new JSONObject();
        button.put("button", buttonList);
        try {
            this.wxMpService.getMenuService().menuCreate(button.toJSONString());
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
    }

    //获取所有一级菜单
    @Override
    public List<Menu> findMenuOneInfo() {
        List<Menu> menus = new ArrayList<>();
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,0);
        menus = baseMapper.selectList(wrapper);
        return menus;
    }

    //获取全部菜单
    @Override
    public List<MenuVo> findMenuInfo() {
        List<MenuVo> list = new ArrayList<>();
        List<Menu> menuList = baseMapper.selectList(null);
        List<Menu> oneMenuList = menuList.stream().filter(menu -> menu.getParentId().longValue() == 0).collect(Collectors.toList());
        for(Menu oneMenu : oneMenuList) {
            MenuVo oneMenuVo = new MenuVo();
            BeanUtils.copyProperties(oneMenu, oneMenuVo);

            List<Menu> twoMenuList = menuList.stream()
                    .filter(menu -> menu.getParentId().longValue() == oneMenu.getId())
                    .sorted(Comparator.comparing(Menu::getSort))
                    .collect(Collectors.toList());
            List<MenuVo> children = new ArrayList<>();
            for(Menu twoMenu : twoMenuList) {
                MenuVo twoMenuVo = new MenuVo();
                BeanUtils.copyProperties(twoMenu, twoMenuVo);
                children.add(twoMenuVo);
            }
            oneMenuVo.setChildren(children);
            list.add(oneMenuVo);
        }
        return list;
    }


    //获取所有菜单，按照一级和二级菜单封装
//    @Override
//    public List<MenuVo> findMenuInfo() {
//        List<MenuVo> menuVos = new ArrayList<>();
//        List<Menu> menuOneInfo = findMenuOneInfo();
//        //一级菜单
//        for (Menu meno: menuOneInfo) {
//            MenuVo menuVo = new MenuVo();
//            BeanUtils.copyProperties(menuOneInfo,menuVo);
//            menuVos.add(menuVo);
//        }
//        for (MenuVo menoVo : menuVos) {
//            Long id = menoVo.getId();
//            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
//            wrapper.eq(Menu::getParentId,id);
//            List<Menu> menus = baseMapper.selectList(wrapper);
//
//            //二级菜单
//            List<MenuVo> menuVoTwo = new ArrayList<>();
//            for (Menu meno: menus) {
//                MenuVo menuVo = new MenuVo();
//                BeanUtils.copyProperties(menuOneInfo,menuVo);
//                menuVoTwo.add(menuVo);
//            }
//            if(!menuVoTwo.isEmpty()){
//                menoVo.setChildren(menuVoTwo);
//            }
//        }
//        return menuVos;
//    }
}
